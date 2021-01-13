package com.yizhou.yiblog.service.impl;

import com.yizhou.yiblog.pojo.User;
import com.yizhou.yiblog.service.IUserService;
import com.yizhou.yiblog.util.Constrants;
import com.yizhou.yiblog.util.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service("permission")
public class PermissionService {

    @Autowired
    private IUserService iUserService;

    //check if it is admin
    public boolean admin() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        HttpServletResponse response = servletRequestAttributes.getResponse();
        String cookie = CookieUtils.getCookie(request, Constrants.User.KEY_TOKEN_COOKIE);
        if (cookie == null) {
            return false;
        }
        User user = iUserService.checkUser();
        if (user == null) {
            return false;
        }
        if (Constrants.User.ROLE_ADMIN.equals(user.getRoles())) {
            return true;
        }
        return false;
    }
}
