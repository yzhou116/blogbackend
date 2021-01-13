package com.yizhou.yiblog.dao;

import com.yizhou.yiblog.pojo.Category;
import com.yizhou.yiblog.pojo.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface CategoryDAO extends JpaRepository<Category, String>, JpaSpecificationExecutor<Category> {

    Category findOneById(String id);

}
