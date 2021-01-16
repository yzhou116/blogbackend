package com.yizhou.yiblog.dao;

import com.yizhou.yiblog.pojo.Friends;
import com.yizhou.yiblog.pojo.Looper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LooperDAO extends JpaRepository<Looper, String>, JpaSpecificationExecutor<Looper> {
    Looper findOneById(String loopId);

    @Query(nativeQuery = true, value = "select * from `tb_looper` where `state` = ?")
    List<Looper> listLoopsbyByState(String s);
}
