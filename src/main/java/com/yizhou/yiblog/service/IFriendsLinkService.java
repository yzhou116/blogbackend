package com.yizhou.yiblog.service;

import com.yizhou.yiblog.pojo.Friends;
import com.yizhou.yiblog.response.ResponseResult;

public interface IFriendsLinkService {
    ResponseResult addFriendLink(Friends friends);

    ResponseResult getFriend(String friendsId);

    ResponseResult ListAllFriends(int page, int size);

    ResponseResult deleteFriend(String friendsId);

    ResponseResult updateFriendInfo(String friendsId, Friends friends);
}
