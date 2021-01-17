package com.yizhou.yiblog.dao;

import com.yizhou.yiblog.pojo.User;
import com.yizhou.yiblog.pojo.UserNoPassword;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserNoPasswordDAO extends JpaRepository<UserNoPassword, String>, JpaSpecificationExecutor<UserNoPassword> {

}
