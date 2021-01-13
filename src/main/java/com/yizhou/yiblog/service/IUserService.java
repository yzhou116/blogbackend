package com.yizhou.yiblog.service;

import com.yizhou.yiblog.pojo.User;
import com.yizhou.yiblog.response.ResponseResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;

public interface IUserService {
    ResponseResult initAdminAccount(User user, HttpServletRequest httpServletRequest);

    void createCaptcha(HttpServletResponse response, String captcha) throws IOException, FontFormatException;

    ResponseResult sendEmailVerification(String type, HttpServletRequest request, String emailAddress);

    ResponseResult registerUser(User user, String emailcode, String captchacode, String captcha_key, HttpServletRequest request);

    ResponseResult CheckLogin(String captcha_key, String captcha, User user, HttpServletRequest request, HttpServletResponse response);

    User checkUser();

    ResponseResult getUserInfo(String id);

    ResponseResult checkEmail(String email);

    ResponseResult checkUserName(String userName);

    ResponseResult UpdateUserInfo(HttpServletRequest request, HttpServletResponse response,
                                  String id, User user);

    ResponseResult deleteUserById(HttpServletRequest request, HttpServletResponse response, String id);

    ResponseResult ListUsers(int page, int size, HttpServletRequest request, HttpServletResponse response);

    ResponseResult updateUserPassword(String verifycode, User user);

    ResponseResult updateEmail(String email, String code);

    ResponseResult userLogout();

}
