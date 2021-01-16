package com.yizhou.yiblog.dao;

import com.yizhou.yiblog.pojo.Category;
import com.yizhou.yiblog.pojo.Friends;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FriendsLinkDAO extends JpaRepository<Friends, String>, JpaSpecificationExecutor<Friends> {

    Friends findOneById(String id);


    int deleteAllById(String friendsId);

    @Query(nativeQuery = true, value = "select * from `tb_friends` where `state` = ?")
    List<Friends> listFriendsbyByState(String s);
}
