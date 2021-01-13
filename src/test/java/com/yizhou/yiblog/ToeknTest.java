package com.yizhou.yiblog;

import com.yizhou.yiblog.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.DigestUtils;

import java.util.HashMap;
import java.util.Map;

public class ToeknTest {
    public static void main(String[] args) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", "732250648279580671");
        claims.put("userName", "TestUser");
        claims.put("role", "role_normal");
        claims.put("avatar", "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__340.jpg");
        claims.put("email", "test@gmail.net");
        String token = JwtUtil.createToken(
                claims);//有效期为1分钟
        Claims claims1 = JwtUtil.parseJWT(token);
        String id = (String) claims1.get("id");
        String username = (String) claims1.get("userName");
        Object role = claims1.get("role");
        Object avatar = claims1.get("avatar");
        Object email = claims1.get("email");
        //eyJhbGciOiJIUzI1NiJ9
        //.eyJyb2xlIjoicm9sZV9ub3JtYWwiLCJpZCI6IjcyMjI1MDY0ODI3OTU4MDY3MyIsImF2YXRhciI6Imh0dHBzOi8vY2RuLnBpeGFiYXkuY29tL3Bob3RvLzIwMTUvMDQvMjMvMjIvMDAvdHJlZS03MzY4ODVfXzM0MC5qcGciLCJ1c2VyTmFtZSI6Iua1i-ivleeUqOaItyIsImV4cCI6MTYxMDQzMDAwNiwiZW1haWwiOiJ0ZXN0QGdtYWlsLm5ldCJ9
        //.-RCNsziS3H5aDqg7MPxOO26hWV4dkqkGA-0JE_f-O9M
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String password = "19900511";
        String encode = bCryptPasswordEncoder.encode(password);
        System.out.println(encode);
        String ppp = "$2a$10$h9NQiwcHM7wBmdJEJRKpEefQSyysUrmv2W3cqQuBF/LmwIkwwpWsK";
        boolean b = bCryptPasswordEncoder.matches(password, ppp);
        System.out.println(b);

     /*   System.out.println(token);
        System.out.println(id);
        System.out.println(username);
        System.out.println(role);
        System.out.println(avatar);
        System.out.println(email);*/


    }

}

