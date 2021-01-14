package com.yizhou.yiblog.service;

import com.yizhou.yiblog.pojo.Looper;
import com.yizhou.yiblog.response.ResponseResult;

public interface ILooperService {
    ResponseResult addLoop(Looper looper);

    ResponseResult getLoop(String loopId);

    ResponseResult ListLoops(int page, int size);

    ResponseResult updateLooper(String loopId, Looper looper);

    ResponseResult deleteByLoopId(String loopId);
}
