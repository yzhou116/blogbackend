package com.yizhou.yiblog.dao;

import com.yizhou.yiblog.pojo.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserDAO extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {
    /**
     * check if user is exit
     *
     * @param userName
     * @return
     */
    User findOneByUserName(String userName);


    User findOneByEmail(String email);

    List<User> findOneByEmailOrUserName(String email, String UserName);

    User findOneById(String userId);

    /**
     * use change the state to delete user
     * remember to add @Modifying
     *
     * @param id
     * @return
     */
    @Modifying
    @Query(nativeQuery = true, value = "UPDATE `tb_user` SET `state` = '0' where `id` = ?")
    int deleteUserByState(String id);

    @Query(value = "select new User(u.id,u.userName,u.roles,u.avatar,u.email,u.sign,u.state,u.regIp,u.loginIp,u.createTime,u.updateTime) from User as u")
    Page<User> listAllUserWithoutPassword(Pageable pageable);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE `tb_user` SET `password` = ? where `email` = ?")
    int updatePasswordByEmail(String encode, String email);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE `tb_user` SET `email` = ? where `id` = ?")
    int updateEmailById(String email, String id);
}
