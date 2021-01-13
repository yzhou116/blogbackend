package com.yizhou.yiblog.dao;

import com.yizhou.yiblog.pojo.Settings;
import com.yizhou.yiblog.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SettingsDAO extends JpaRepository<Settings, String>, JpaSpecificationExecutor<Settings> {

    Settings findOneByKey(String key);
}
