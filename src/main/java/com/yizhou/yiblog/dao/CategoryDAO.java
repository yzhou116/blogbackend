package com.yizhou.yiblog.dao;

import com.yizhou.yiblog.pojo.Category;
import com.yizhou.yiblog.pojo.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryDAO extends JpaRepository<Category, String>, JpaSpecificationExecutor<Category> {

    Category findOneById(String id);


    @Modifying
    @Query(nativeQuery = true, value = "update `tb_categories` set `status` = '0' where `id` = ?")
    int deleteCategoryByUpdateState(String id);

    @Query(nativeQuery = true, value = "select * from `tb_categories` where `status` = ?")
    List<Category> listCategoryByState(String status);
}
