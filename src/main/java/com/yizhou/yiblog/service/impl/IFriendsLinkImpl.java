package com.yizhou.yiblog.service.impl;

import com.yizhou.yiblog.dao.FriendsLinkDAO;
import com.yizhou.yiblog.pojo.Friends;
import com.yizhou.yiblog.response.ResponseResult;
import com.yizhou.yiblog.service.IFriendsLinkService;
import com.yizhou.yiblog.util.Constrants;
import com.yizhou.yiblog.util.SnowflakeIdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Service
@Transactional
public class IFriendsLinkImpl implements IFriendsLinkService {

    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;
    @Autowired
    private FriendsLinkDAO friendsLinkDAO;

    @Override
    public ResponseResult addFriendLink(Friends friends) {

        String url = friends.getUrl();
        if (url == null) {
            return ResponseResult.FAIL("Url is null");
        }
        String logo = friends.getLogo();
        if (logo == null) {
            return ResponseResult.FAIL("Log is null");
        }
        String name = friends.getName();
        if (name == null) {
            return ResponseResult.FAIL("Name is null");
        }
        friends.setId(snowflakeIdWorker.nextId() + "");
        friends.setCreateTime(new Date());
        friends.setUpdateTime(new Date());
        friends.setState("1");
        friends.setFOrder(1);
        friendsLinkDAO.save(friends);
        int i = 0;

        return ResponseResult.SUCCESS("Save the friend");
    }

    @Override
    public ResponseResult getFriend(String friendsId) {
        Friends oneById = friendsLinkDAO.findOneById(friendsId);
        if (oneById == null) {
            return ResponseResult.FAIL("Friend is null");
        }


        return ResponseResult.SUCCESS("Success").setData(oneById);
    }

    @Override
    public ResponseResult ListAllFriends(int page, int size) {
        if (page < Constrants.Page.DEFAULT_PAGE) {
            page = Constrants.Page.DEFAULT_PAGE;
        }
        if (size < Constrants.Page.MIN_SIZE) {
            size = Constrants.Page.MIN_SIZE;
        }
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by("createTime"));
        Page<Friends> list = friendsLinkDAO.findAll(pageable);
        return ResponseResult.SUCCESS("Success").setData(list);
    }

    @Override
    public ResponseResult deleteFriend(String friendsId) {
        int result = friendsLinkDAO.deleteAllById(friendsId);
        if (result == 0) {
            ResponseResult.FAIL("Delete Fail");
        }
        return ResponseResult.SUCCESS("Success");
    }

    @Override
    public ResponseResult updateFriendInfo(String friendsId, Friends friends) {
        Friends oneById = friendsLinkDAO.findOneById(friendsId);
        if (oneById == null) {
            return ResponseResult.FAIL("Update fail");
        }
        String logo = friends.getLogo();
        if (logo != null) {
            oneById.setLogo(logo);
        }
        String name = friends.getName();
        if (name != null) {
            oneById.setName(name);
        }
        String url = friends.getUrl();
        if (url != null) {
            oneById.setUrl(url);
        }
        oneById.setUpdateTime(new Date());
        friendsLinkDAO.save(oneById);
        return ResponseResult.SUCCESS("Success");
    }
}
