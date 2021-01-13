package com.yizhou.yiblog.dao;

import com.yizhou.yiblog.pojo.Labels;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LabelDAO extends JpaRepository<Labels, String>, JpaSpecificationExecutor<Labels> {
}
