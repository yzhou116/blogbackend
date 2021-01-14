package com.yizhou.yiblog.dao;

import com.yizhou.yiblog.pojo.Friends;
import com.yizhou.yiblog.pojo.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ImageDAO extends JpaRepository<Image, String>, JpaSpecificationExecutor<Image> {

    @Modifying
    @Query(nativeQuery = true, value = "update `tb_images` set `state` = '0' where `id` = ?")
    int deleteImageByUpdateState(String imageId);
}
