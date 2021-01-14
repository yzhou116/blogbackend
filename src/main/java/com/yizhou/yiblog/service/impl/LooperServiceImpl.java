package com.yizhou.yiblog.service.impl;

import com.yizhou.yiblog.dao.LooperDAO;
import com.yizhou.yiblog.pojo.Friends;
import com.yizhou.yiblog.pojo.Looper;
import com.yizhou.yiblog.response.ResponseResult;
import com.yizhou.yiblog.service.ILooperService;
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
public class LooperServiceImpl implements ILooperService {

    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;
    @Autowired
    private LooperDAO looperDAO;


    @Override
    public ResponseResult addLoop(Looper looper) {
        String title = looper.getTitle();
        if (title == null) {
            return ResponseResult.FAIL("Title is null");
        }

        String imageUrl = looper.getImageUrl();
        if (imageUrl == null) {
            return ResponseResult.FAIL("Image is null");
        }
        String targetUrl = looper.getTargetUrl();
        if (targetUrl == null) {
            return ResponseResult.FAIL("Target is null");
        }
        looper.setId(snowflakeIdWorker.nextId() + "");
        looper.setCreateTime(new Date());
        looper.setUpdateTime(new Date());
        looper.setState("1");
        looperDAO.save(looper);

        return ResponseResult.SUCCESS("SUCCESS ! ");
    }

    @Override
    public ResponseResult getLoop(String loopId) {
        Looper oneById = looperDAO.findOneById(loopId);
        if (oneById == null) {
            return ResponseResult.FAIL("Can't find it");
        }

        return ResponseResult.SUCCESS("Success").setData(oneById);
    }

    @Override
    public ResponseResult ListLoops(int page, int size) {
        if (page < Constrants.Page.DEFAULT_PAGE) {
            page = Constrants.Page.DEFAULT_PAGE;
        }
        if (size < Constrants.Page.MIN_SIZE) {
            size = Constrants.Page.MIN_SIZE;
        }
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by("createTime"));
        Page<Looper> list = looperDAO.findAll(pageable);
        return ResponseResult.SUCCESS("Success").setData(list);
    }

    @Override
    public ResponseResult updateLooper(String loopId, Looper looper) {
        Looper oneById = looperDAO.findOneById(loopId);
        if (oneById == null) {
            return ResponseResult.FAIL("Can't find it");
        }
        String title = looper.getTitle();
        if (title == null) {
            return ResponseResult.FAIL("no title");
        }
        String targetUrl = looper.getTargetUrl();
        if (targetUrl == null) {
            return ResponseResult.FAIL("targetUrl is null");
        }
        String imageUrl = looper.getImageUrl();
        if (imageUrl == null) {
            return ResponseResult.FAIL("imageUrl is null");
        }
        oneById.setImageUrl(imageUrl);
        oneById.setTitle(title);
        oneById.setTargetUrl(targetUrl);
        oneById.setOrder(looper.getOrder());
        oneById.setState("1");
        oneById.setUpdateTime(new Date());
        looperDAO.save(oneById);


        return ResponseResult.SUCCESS("Success");
    }

    @Override
    public ResponseResult deleteByLoopId(String loopId) {

        looperDAO.deleteById(loopId);
        return ResponseResult.SUCCESS("Success");
    }
}
