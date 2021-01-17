package com.yizhou.yiblog.service.impl;

import com.yizhou.yiblog.dao.SettingsDAO;
import com.yizhou.yiblog.pojo.Settings;
import com.yizhou.yiblog.response.ResponseResult;
import com.yizhou.yiblog.util.Constrants;
import com.yizhou.yiblog.util.RedisUtil;
import com.yizhou.yiblog.util.SnowflakeIdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class IWebsiteInfoServiceImpl implements IWebsiteService {

    @Autowired
    private SettingsDAO settingsDAO;
    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;
    @Autowired
    private RedisUtil redisUtil;


    @Override
    public ResponseResult getWebsiteTitle() {
        Settings title = settingsDAO.findOneByKey(Constrants.Settings.WEB_SITE_TITLE);
        return ResponseResult.SUCCESS("Success").setData(title);
    }

    @Override
    public ResponseResult updateWebsiteTitle(String title) {
        if (title == null) {
            ResponseResult.FAIL("Title is null");
        }
        Settings titlefromDB = settingsDAO.findOneByKey(Constrants.Settings.WEB_SITE_TITLE);
        if (titlefromDB == null) {
            titlefromDB = new Settings();
            titlefromDB.setId(snowflakeIdWorker.nextId() + "");
            titlefromDB.setCreateTime(new Date());
            titlefromDB.setUpdateTime(new Date());
            titlefromDB.setKey(Constrants.Settings.WEB_SITE_TITLE);

        }
        titlefromDB.setValue(title);
        settingsDAO.save(titlefromDB);
        return ResponseResult.SUCCESS("Success").setData(title);
    }

    @Override
    public ResponseResult getWebsiteSeoInfo() {

        Settings description = settingsDAO.findOneByKey(Constrants.Settings.WEB_SITE_DESCRIPTION);
        Settings keyword = settingsDAO.findOneByKey(Constrants.Settings.WEB_SITE_KEY_WORD);
        Map<String, String> map = new HashMap<>();
        map.put(description.getKey(), description.getValue());
        map.put(keyword.getKey(), keyword.getValue());
        return ResponseResult.SUCCESS("Success").setData(map);
    }

    @Override
    public ResponseResult putSeoInfo(String keyword, String desc) {
        if (keyword == null) {
            return ResponseResult.FAIL("No keyword");
        }
        if (desc == null) {
            return ResponseResult.FAIL("No description");
        }
        Settings descriptionfromDB = settingsDAO.findOneByKey(Constrants.Settings.WEB_SITE_DESCRIPTION);
        if (descriptionfromDB == null) {
            descriptionfromDB = new Settings();
            descriptionfromDB.setId(snowflakeIdWorker.nextId() + "");
            descriptionfromDB.setUpdateTime(new Date());
            descriptionfromDB.setCreateTime(new Date());
            descriptionfromDB.setKey(Constrants.Settings.WEB_SITE_DESCRIPTION);

        }
        descriptionfromDB.setValue(desc);
        settingsDAO.save(descriptionfromDB);

        Settings keywordfromDB = settingsDAO.findOneByKey(Constrants.Settings.WEB_SITE_KEY_WORD);
        if (keywordfromDB == null) {
            keywordfromDB = new Settings();
            keywordfromDB.setId(snowflakeIdWorker.nextId() + "");
            keywordfromDB.setUpdateTime(new Date());
            keywordfromDB.setCreateTime(new Date());
            keywordfromDB.setKey(Constrants.Settings.WEB_SITE_KEY_WORD);
        }
        keywordfromDB.setValue(keyword);
        settingsDAO.save(keywordfromDB);
        return ResponseResult.SUCCESS("Success");
    }

    @Override
    public ResponseResult getSizeViewCount() {

        String countStr = (String) redisUtil.get(Constrants.Settings.WEB_SITE_VIEW_COUNT);
        Settings viewCount = settingsDAO.findOneByKey(Constrants.Settings.WEB_SITE_VIEW_COUNT);
        if (viewCount == null) {
            viewCount = new Settings();
            viewCount.setId(snowflakeIdWorker.nextId() + "");
            viewCount.setKey(Constrants.Settings.WEB_SITE_VIEW_COUNT);
            viewCount.setCreateTime(new Date());
            viewCount.setUpdateTime(new Date());
            viewCount.setValue("1");
            settingsDAO.save(viewCount);
        }
        if (countStr == null) {
            countStr = viewCount.getValue();
            redisUtil.set(Constrants.Settings.WEB_SITE_VIEW_COUNT, countStr);
        } else {
            viewCount.setValue(countStr);
            settingsDAO.save(viewCount);

        }

        Map<String, Integer> map = new HashMap<>();
        map.put(viewCount.getKey(), Integer.valueOf(viewCount.getValue()));
        return ResponseResult.SUCCESS("Success").setData(map);
    }

    @Override
    public void updateViewCount() {
        Object viewCount = redisUtil.get(Constrants.Settings.WEB_SITE_VIEW_COUNT);
        if (viewCount == null) {
            Settings viewCountstr = settingsDAO.findOneByKey(Constrants.Settings.WEB_SITE_VIEW_COUNT);
            redisUtil.set(Constrants.Settings.WEB_SITE_VIEW_COUNT, viewCountstr.getValue());
        }
        redisUtil.incr(Constrants.Settings.WEB_SITE_VIEW_COUNT, 1);

    }
}
