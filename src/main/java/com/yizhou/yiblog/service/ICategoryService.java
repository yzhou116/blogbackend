package com.yizhou.yiblog.service;

import com.yizhou.yiblog.pojo.Category;
import com.yizhou.yiblog.response.ResponseResult;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


public interface ICategoryService {
    ResponseResult addCategory(Category category);

    ResponseResult getCategory(String id);

    ResponseResult ListCategory(int page, int size);

    ResponseResult updateCategory(String id, Category category);

    ResponseResult deleteCategory(String id);
}
