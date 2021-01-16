package com.yizhou.yiblog.dao;

import com.yizhou.yiblog.pojo.Labels;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface LabelDAO extends JpaRepository<Labels, String>, JpaSpecificationExecutor<Labels> {
    Labels findOneByName(String labels);

    @Modifying
    int deleteOneById(String id);

    Labels findOneById(String id);


    @Query(nativeQuery = true, value = "update `tb_labels` set `count` = `count` +1 where `name` = ?")
    int updateCountByName(String labelName);


}
