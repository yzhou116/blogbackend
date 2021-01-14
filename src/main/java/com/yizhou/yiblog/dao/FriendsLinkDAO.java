package com.yizhou.yiblog.dao;

import com.yizhou.yiblog.pojo.Category;
import com.yizhou.yiblog.pojo.Friends;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FriendsLinkDAO extends JpaRepository<Friends, String>, JpaSpecificationExecutor<Friends> {

    Friends findOneById(String id);


    int deleteAllById(String friendsId);
}
