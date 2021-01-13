package com.yizhou.yiblog.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name = "tb_refresh_token")
public class RefreshToken {

    @Id
    String id;

    @Column(name = "refresh_token")
    String refreshToken;

    @Column(name = "user_id")
    String userId;

    @Column(name = "token_key")
    String tokenKey;
    @Column(name = "create_time")
    Date createTime;
    @Column(name = "update_time")
    Date updateTime;
}
