package com.yizhou.yiblog.util;

import com.yizhou.yiblog.pojo.User;
import io.jsonwebtoken.Claims;

import java.util.HashMap;
import java.util.Map;

public class ClaimsUtil {
    public static final String ID = "ID";
    public static final String USERNAME = "username";
    public static final String ROLE = "role";
    public static final String AVATAR = "avatar";
    public static final String EMAIL = "email";
    public static final String SIGN = "sign";


    public static Map<String, Object> User2Claims(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(ID, user.getId());
        claims.put(USERNAME, user.getUserName());
        claims.put(ROLE, user.getRoles());
        claims.put(AVATAR, user.getAvatar());
        claims.put(EMAIL, user.getEmail());
        claims.put(SIGN, user.getSign());
        return claims;
    }

    public static User claims2User(Claims claims) {
        User user = new User();
        String id = (String) claims.get(ID);
        user.setId(id);
        String userName = (String) claims.get(USERNAME);
        user.setUserName(userName);
        String role = (String) claims.get(ROLE);
        user.setRoles(role);
        String avatar = (String) claims.get(AVATAR);
        user.setAvatar(avatar);
        String email = (String) claims.get(EMAIL);
        user.setEmail(email);
        String sign = (String) claims.get(SIGN);
        user.setSign(sign);
        return user;
    }
}
