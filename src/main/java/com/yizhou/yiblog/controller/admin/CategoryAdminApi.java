package com.yizhou.yiblog.controller.admin;

import com.yizhou.yiblog.pojo.Category;
import com.yizhou.yiblog.response.ResponseResult;
import com.yizhou.yiblog.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/category")
public class CategoryAdminApi {

    @Autowired
    private ICategoryService iCategoryService;


    @PreAuthorize("@permission.admin()")
    @PostMapping
    public ResponseResult addCategory(@RequestBody Category category) {
        return iCategoryService.addCategory(category);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseResult deleteCategory(@PathVariable("categoryId") String id) {
        return null;
    }

    @PreAuthorize("@permission.admin()")
    @PutMapping("/{categoryId}")
    public ResponseResult updateCategory(@PathVariable("categoryId") String id,
                                         @RequestBody Category category) {
        return iCategoryService.updateCategory(id, category);
    }

    @PreAuthorize("@permission.admin()")
    @GetMapping("/{categoryId}")
    public ResponseResult getCategory(@PathVariable("categoryId") String id) {
        return iCategoryService.getCategory(id);
    }

    @PreAuthorize("@permission.admin()")
    @GetMapping("/list/{page}/{size}")
    public ResponseResult listCategory(@PathVariable("page") int page,
                                       @PathVariable("size") int size) {
        return iCategoryService.ListCategory(page, size);
    }


}
