package com.yizhou.yiblog.service.impl;

import com.yizhou.yiblog.response.ResponseResult;

public interface IWebsiteService {
    ResponseResult getWebsiteTitle();

    ResponseResult updateWebsiteTitle(String title);

    ResponseResult getWebsiteSeoInfo();

    ResponseResult putSeoInfo(String keyword, String desc);

    ResponseResult getSizeViewCount();

    void updateViewCount();
}
