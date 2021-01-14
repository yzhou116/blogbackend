package com.yizhou.yiblog.service.impl;

import com.yizhou.yiblog.dao.CategoryDAO;
import com.yizhou.yiblog.pojo.Category;
import com.yizhou.yiblog.pojo.User;
import com.yizhou.yiblog.response.ResponseResult;
import com.yizhou.yiblog.service.ICategoryService;
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
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;
    @Autowired
    private CategoryDAO categoryDAO;

    @Override
    public ResponseResult addCategory(Category category) {
        if (category.getName() == null) {
            return ResponseResult.FAIL("Category name is empty");
        }
        if (category.getDescription() == null) {
            return ResponseResult.FAIL("Description name is empty");
        }
        category.setPinyin("Not Available");
        category.setOrder(1);
        category.setId(snowflakeIdWorker.nextId() + "");
        category.setStatus("1");
        category.setCreateTime(new Date());
        category.setUpdateTime(new Date());
        categoryDAO.save(category);
        return ResponseResult.SUCCESS("Successful ! ");
    }

    @Override
    public ResponseResult getCategory(String id) {
        Category oneById = categoryDAO.findOneById(id);
        if (oneById == null) {
            return ResponseResult.FAIL("Cant find category");
        }
        return ResponseResult.SUCCESS("Successful !").setData(oneById);
    }

    @Override
    public ResponseResult ListCategory(int page, int size) {
        if (page < Constrants.Page.DEFAULT_PAGE) {
            page = Constrants.Page.DEFAULT_PAGE;
        }
        if (size < Constrants.Page.MIN_SIZE) {
            size = Constrants.Page.MIN_SIZE;
        }
        //sorted by register time
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by("createTime").descending());
        Page<Category> categoryList = categoryDAO.findAll(pageable);

        return ResponseResult.SUCCESS("Successful !").setData(categoryList);
    }

    @Override
    public ResponseResult updateCategory(String id, Category category) {
        Category oneById = categoryDAO.findOneById(id);
        if (oneById == null) {
            return ResponseResult.FAIL("Category is not exist");
        }
        String name = category.getName();
        if (name != null) {
            oneById.setName(name);
        }
        String description = category.getDescription();
        if (description != null) {
            oneById.setDescription(description);
        }
        String pingyin = category.getPinyin();
        if (pingyin != null) {
            oneById.setPinyin(pingyin);
        }
        Integer orders = category.getOrder();
        if (orders != null) {
            oneById.setOrder(orders);
        }

        categoryDAO.save(oneById);
        return ResponseResult.SUCCESS("Update is Successful ! ");
    }

    @Override
    public ResponseResult deleteCategory(String id) {
        int result = categoryDAO.deleteCategoryByUpdateState(id);
        return result > 0 ? ResponseResult.SUCCESS("Delete is Successful") :
                ResponseResult.FAIL("NO this category");
    }
}
