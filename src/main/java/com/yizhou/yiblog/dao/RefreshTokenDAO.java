package com.yizhou.yiblog.dao;

import com.yizhou.yiblog.pojo.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RefreshTokenDAO extends JpaRepository<RefreshToken, String>, JpaSpecificationExecutor<RefreshToken> {
    RefreshToken findOneByTokenKey(String tokenKey);

    int deleteAllByUserId(String userId);

    int deleteAllByTokenKey(String tokenKey);
}
