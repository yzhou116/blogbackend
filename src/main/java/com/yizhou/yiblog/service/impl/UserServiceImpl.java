package com.yizhou.yiblog.service.impl;

import com.google.gson.Gson;
import com.wf.captcha.ArithmeticCaptcha;
import com.wf.captcha.GifCaptcha;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import com.yizhou.yiblog.dao.RefreshTokenDAO;
import com.yizhou.yiblog.dao.SettingsDAO;
import com.yizhou.yiblog.dao.UserDAO;
import com.yizhou.yiblog.dao.UserNoPasswordDAO;
import com.yizhou.yiblog.pojo.Settings;
import com.yizhou.yiblog.pojo.User;
import com.yizhou.yiblog.pojo.RefreshToken;
import com.yizhou.yiblog.pojo.UserNoPassword;
import com.yizhou.yiblog.response.ReponseState;
import com.yizhou.yiblog.response.ResponseResult;
import com.yizhou.yiblog.service.IUserService;

import com.yizhou.yiblog.util.*;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.awt.*;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.regex.Pattern;

@Slf4j
@Service
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private SettingsDAO settingsDAO;
    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private Random random;

    @Autowired
    private TaskService taskService;

    @Autowired
    private RefreshTokenDAO refreshTokenDAO;

    @Autowired
    private Gson gson;
    @Autowired
    private UserNoPasswordDAO userNoPasswordDAO;

    public static final int[] captchatypes = {Captcha.FONT_1, Captcha.FONT_2, Captcha.FONT_3, Captcha.FONT_4,
            Captcha.FONT_5, Captcha.FONT_6, Captcha.FONT_7, Captcha.FONT_8, Captcha.FONT_9, Captcha.FONT_10};
    /*
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        User users = new User();
        users.setUserName("yzhou116");
        users.setPassword("sd4888202");

        users.setEmail("yzhou116@gmail.com");
        userService.initAdminAccount(users,null);
    }

     */

    /**
     * init admin.
     *
     * @param user
     * @param httpServletRequest
     * @return
     */


    @Override
    public ResponseResult initAdminAccount(User user, HttpServletRequest httpServletRequest) {

        Settings oneByKey = settingsDAO.findOneByKey(Constrants.Settings.HAD_MANAGER_ACCOUNT_INIT);
        if (oneByKey != null) {
            return ResponseResult.FAIL("Failed coz account has been used");
        }
        if (user.getUserName() == null) {
            return ResponseResult.FAIL("User name is null");
        }
        if (user.getPassword() == null) {
            return ResponseResult.FAIL("Password is null");
        }
        if (user.getEmail() == null) {
            return ResponseResult.FAIL("Email is null");
        }
        user.setId(String.valueOf(snowflakeIdWorker.nextId()));
        user.setRoles(Constrants.User.ROLE_ADMIN);
        user.setAvatar(Constrants.User.DEFAULT_STATE);
        user.setState(Constrants.User.DEFAULT_STATE);
        String remoteAddr = httpServletRequest.getRemoteAddr();
        user.setLoginIp(remoteAddr);
        user.setRegIp(remoteAddr);
        /**
         * frontend  use md5 to encode user's password first
         * then backend use bCryptPasswordEncoder to encode it again
         * double secure
         */
        String origin = user.getPassword();
        user.setPassword(bCryptPasswordEncoder.encode(origin));
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        userDAO.save(user);
        Settings settings = new Settings();

        settings.setId(snowflakeIdWorker.nextId() + "");
        settings.setKey(Constrants.Settings.HAD_MANAGER_ACCOUNT_INIT);
        settings.setCreateTime(new Date());
        settings.setUpdateTime(new Date());
        settings.setValue("1");

        settingsDAO.save(settings);

        return ResponseResult.SUCCESS("SUCCESS!");
    }

    public static void main(String[] args) {

    }



    /**
     * @param response
     * @param captcha
     * @throws IOException
     * @throws FontFormatException
     */


    @Override
    public void createCaptcha(HttpServletResponse response, String captcha) throws IOException, FontFormatException {
        /**
         * publisher's github address:
         * https://github.com/whvcse/EasyCaptcha
         */
        if (captcha == null || captcha.length() < 13) {
            return;
        }
        long key;

        try {
            key = Long.parseLong(captcha);
        } catch (Exception e) {
            return;
        }
        response.setContentType("image/gif");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        Captcha Tcaptcha = null;
        int type = random.nextInt(3);
        if (type == 0) {
            Tcaptcha = new SpecCaptcha(200, 60, 5);
        } else if (type == 1) {
            Tcaptcha = new GifCaptcha(200, 60);
        } else {
            Tcaptcha = new ArithmeticCaptcha(200, 60);
            Tcaptcha.setLen(3);  // 几位数运算，默认是两位

        }
        Tcaptcha.setFont(captchatypes[random.nextInt(captchatypes.length)]);
        Tcaptcha.setCharType(Captcha.TYPE_DEFAULT);
        String Content = Tcaptcha.text().toLowerCase();
        //save to redis
        redisUtil.set(Constrants.User.KEY_CAPTCHA_CONTENT + key, Content, 60 * 10);
        Tcaptcha.out(response.getOutputStream());
    }

    /**
     * Arttention
     * below method is for Email verification, only works for 163 and qq
     * Email verification
     *
     * @param request
     * @param emailAddress
     * @return
     */

    @Override
    public ResponseResult sendEmailVerification(String type, HttpServletRequest request, String emailAddress) {
        if (emailAddress == null) {
            return ResponseResult.FAIL("Email can not be empty");
        }

        if ("register".equals(type) || type.equals("update")) {
            User email = userDAO.findOneByEmail(emailAddress);
            if (email != null) {
                return ResponseResult.FAIL("Email has been registered");
            }
        } else if ("forget".equals(type)) {
            User email = userDAO.findOneByEmail(emailAddress);
            if (email == null) {
                return ResponseResult.FAIL("Email has not been registered");
            }


        }

        String remoteAddr = request.getRemoteAddr();

        Integer ipsendTime = (Integer) redisUtil.get(Constrants.User.KEY_EMAIL_SEND_IP + remoteAddr);

        if (remoteAddr != null) {
            remoteAddr = remoteAddr.replaceAll(":", "-");
        }

        if (ipsendTime != null && ipsendTime > 10) {

            return ResponseResult.FAIL("Do not send request so many times");

        }
        Object addressipTime = redisUtil.get(Constrants.User.KEY_EMAIL_SEND_ADDRESS + emailAddress);
        if (addressipTime != null) {

            return ResponseResult.FAIL("Do not send request so many times");

        }
        //code length is 6:
        int code = random.nextInt(999999);
        if (code < 100000) {
            code += 100000;
        }

        try {
            taskService.sendVerifyCode(String.valueOf(code), emailAddress);

        } catch (Exception e) {
            e.printStackTrace();
        }
        if (ipsendTime == null) {
            ipsendTime = 0;
        }
        ipsendTime++;
        redisUtil.set(Constrants.User.KEY_EMAIL_SEND_IP + remoteAddr, ipsendTime, 60 * 60);

        redisUtil.set(Constrants.User.KEY_EMAIL_SEND_ADDRESS + emailAddress, "true", 30);

        redisUtil.set(Constrants.User.KEY_EMAIL_CODE_CONTENT + emailAddress, String.valueOf(code), 60 * 10);

        return ResponseResult.SUCCESS("SUCCESS !");
    }

    @Override
    public ResponseResult registerUser(User user, String emailcode, String captchacode, String captcha_key, HttpServletRequest request) {
        //check if user is exits

        String userName = user.getUserName();
        if (userName == null) {
            return ResponseResult.FAIL("User name can not be empty");
        }
        User oneByUserName = userDAO.findOneByUserName(userName);
        if (oneByUserName != null) {
            return ResponseResult.FAIL("User name is used by others");
        }
        //check email is valid
        //frontend can check if email is well format.
        String email = user.getEmail();
        if (email == null) {
            return ResponseResult.FAIL("Email can not be empty");
        }
        //check if email verify code is right
        /**
         * if we don't use email we can uncomment this email check part,
         * also remove the parameter at UserApi register method, it is
         * second parameter called String code.
         */
        User oneByEmail = userDAO.findOneByEmail(email);
        if (oneByEmail != null) {
            return ResponseResult.FAIL("Email is used by others");
        }
        String emailVerify = (String) redisUtil.get(Constrants.User.KEY_EMAIL_CODE_CONTENT + email);
        if (emailVerify == null) {
            return ResponseResult.FAIL("Verification code is expired");
        } else {
            if (!emailcode.equals(emailVerify)) {
                return ResponseResult.FAIL("Verification code is not right");
            } else {
                redisUtil.del(Constrants.User.KEY_EMAIL_CODE_CONTENT + email);
            }
        }
        //check if human code is right
        String captVerifyCode = (String) redisUtil.get(Constrants.User.KEY_CAPTCHA_CONTENT + captcha_key);
        if (captVerifyCode == null) {
            return ResponseResult.FAIL("Human check code is expired");
        }
        if (captchacode.equals(captVerifyCode)) {
            return ResponseResult.FAIL("Human check code is wrong");
        } else {
            redisUtil.del(Constrants.User.KEY_EMAIL_CODE_CONTENT + captcha_key);
        }

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        String ipaddress = request.getRemoteAddr();
        user.setLoginIp(ipaddress);
        user.setRegIp(ipaddress);
        user.setUpdateTime(new Date());
        user.setCreateTime(new Date());
        user.setAvatar(Constrants.User.DEFAULT_AVATAR);
        user.setRoles(Constrants.User.ROLE_NORMAL);
        user.setState("1");
        user.setId(snowflakeIdWorker.nextId() + "");
        userDAO.save(user);
        return ResponseResult.GETREPONSESTATE(ReponseState.REGISTER_IN_SUCCESS);
    }

    @Override
    public ResponseResult CheckLogin(String captcha,
                                     String captchaKey,
                                     User user,
                                     HttpServletRequest request,
                                     HttpServletResponse response) {
      //  String captchaValue = (String) redisUtil.get(Constrants.User.KEY_CAPTCHA_CONTENT + captchaKey);
        /**
         * Attention: when I test captcha all of english letter need to be lowercase !
         *
         *
         */
       // if (!captcha.equals(captchaValue)) {
         //   return ResponseResult.FAIL("Captcha is wrong");
       // }
        if (user.getUserName() == null) {
            return ResponseResult.FAIL("User name  is empty");
        }
        if (user.getPassword() == null) {
            return ResponseResult.FAIL(" Password is empty");
        }
        if(isValid(user.getUserName()) && Objects.equals(user.getPassword(), Constrants.User.GOOGLE_LOGIN)){
            User byUserName = userDAO.findOneByUserName(user.getUserName());
            if(byUserName == null){
                String ipaddress = request.getRemoteAddr();
                user.setLoginIp(ipaddress);
                user.setRegIp(ipaddress);
                user.setEmail(user.getUserName());
                user.setUpdateTime(new Date());
                user.setCreateTime(new Date());
                user.setAvatar(Constrants.User.DEFAULT_AVATAR);
                user.setRoles(Constrants.User.ROLE_NORMAL);
                user.setState("1");
                user.setId(snowflakeIdWorker.nextId() + "");
                userDAO.save(user);
                return  successUser(user,ipaddress);
            }else{
               return successUser(byUserName, null);
            }
        }

        //check user name first
        User byUserName = userDAO.findOneByUserName(user.getUserName());
        if (byUserName == null) {
            byUserName = userDAO.findOneByEmail(user.getUserName());
        }
        if (byUserName == null) {
            return ResponseResult.FAIL("User name or Password is wrong");
        }
        //check password
        boolean matches = bCryptPasswordEncoder.matches(user.getPassword(), byUserName.getPassword());
        if (!matches) {
            return ResponseResult.FAIL("User name or Password is wrong");
        }
        if (!"1".equals(byUserName.getState())) {
            return ResponseResult.FAIL("Account has been banned");
        }
        //create token:
        RefreshTokenKK(response, byUserName);

       ResponseResult responseResult = ResponseResult.SUCCESS("Log in Success ! ");
       User resUser = new User();
       resUser.setId(byUserName.getId());
        resUser.setUserName(byUserName.getUserName());
        resUser.setEmail(byUserName.getEmail());
        resUser.setAvatar(byUserName.getAvatar());
       responseResult.setData(resUser);
      //  responseResult.setData(byUserName.getId());

       // return ResponseResult.SUCCESS("Log in Success ! ");
        return  responseResult;
    }

    private String RefreshTokenKK(HttpServletResponse response, User byUserName) {
        refreshTokenDAO.deleteAllByUserId(byUserName.getId());
        Map<String, Object> claims = ClaimsUtil.User2Claims(byUserName);


        String token = JwtUtil.createToken(claims);
        //return md5 value of token, token stores in redis
        //if frontend calls the token with md5 value , get token from redis
        String tokenKey = DigestUtils.md5DigestAsHex(token.getBytes());
        //add token to cookies:
    //    redisUtil.set(Constrants.User.KEY_TOKEN + tokenKey, token, 2 * Constrants.TimeValue.HOUR_1);

        CookieUtils.setupCookie(response, Constrants.User.KEY_TOKEN_COOKIE, tokenKey);
      /*  cookie.setDomain("localhost");
        //one year age for cookie
        cookie.setMaxAge(60*60*24*365);
        cookie.setPath("/");
        response.addCookie(cookie);*/
        String refreshTokenValue = JwtUtil.createRefreshToken(byUserName.getId(), Constrants.TimeValue.MONTH_1);

        RefreshToken refreshToken1 = new RefreshToken();
        refreshToken1.setId(snowflakeIdWorker.nextId() + "");
        refreshToken1.setUserId(byUserName.getId());
        refreshToken1.setRefreshToken(refreshTokenValue);
        refreshToken1.setTokenKey(tokenKey);
        refreshToken1.setCreateTime(new Date());
        refreshToken1.setUpdateTime(new Date());
        refreshTokenDAO.save(refreshToken1);
        return tokenKey;
    }

    @Override
    public User checkUser() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        HttpServletResponse response = servletRequestAttributes.getResponse();

        String tokenKey = CookieUtils.getCookie(request, Constrants.User.KEY_TOKEN_COOKIE);
        User user = parseByTokenKey(tokenKey);
        if (user == null) {
            RefreshToken oneByTokenKey = refreshTokenDAO.findOneByTokenKey(tokenKey);
            if (oneByTokenKey == null) {
                return null;
            } else {
                try {
                    JwtUtil.parseJWT(oneByTokenKey.getRefreshToken());
                    String userId = oneByTokenKey.getUserId();
                    User userfinbyId = userDAO.findOneById(userId);
                    String newtokenkey = RefreshTokenKK(response, userfinbyId);
                    return parseByTokenKey(newtokenkey);
                } catch (Exception r) {
                    return null;
                }

            }
        }
        return user;
    }

    @Override
    public ResponseResult getUserInfo(String id) {
        //remember to remove password ip and email
        User user = userDAO.findOneById(id);
        if (user == null) {
            return ResponseResult.FAIL("User is not exist");
        }
        /**
         * Attention , you can not just do something like user.setpassword("")
         * to remove the user's password. It is going to remove the password in database as well
         *
         */
        String userJson = gson.toJson(user);
        User fromJson = gson.fromJson(userJson, User.class);
        fromJson.setPassword("");
        fromJson.setEmail("");
        fromJson.setLoginIp("");
        fromJson.setLoginIp("");
        return ResponseResult.SUCCESS("Got the user").setData(fromJson);
    }

    @Override
    public ResponseResult checkEmail(String email) {
        User oneByEmail = userDAO.findOneByEmail(email);
        return oneByEmail == null ? ResponseResult.FAIL("Email is not been registered") :
                ResponseResult.SUCCESS("Email is been registered");
    }

    @Override
    public ResponseResult checkUserName(String userName) {

        User oneByUserName = userDAO.findOneByUserName(userName);
        return oneByUserName == null ? ResponseResult.FAIL("user name is not been registered") :
                ResponseResult.SUCCESS("user name is been registered");
    }

    /**
     * update user' info
     *
     * @param request
     * @param response
     * @param id
     * @param user
     * @return
     */

    @Override
    public ResponseResult UpdateUserInfo(HttpServletRequest request, HttpServletResponse response, String id, User user) {
        /**
         * userFromKey is from token.
         */
        User userFromKey = checkUser();

        if (userFromKey == null) {
            return ResponseResult.ACCOUNT_NOT_LOGIN();
        }
        User userDAOOneById = userDAO.findOneById(userFromKey.getId());
        if (user.getUserName() != null && user.getUserName().length() != 0) {
            User userByuserName = userDAO.findOneByUserName(user.getUserName());
            if (userByuserName != null) {
                return ResponseResult.FAIL("Same user name");
            }
            userDAOOneById.setUserName(user.getUserName());
        }
        if (!userDAOOneById.getId().equals(user.getId())) {
            return ResponseResult.PERMISSION_DENIED();
        }
        if (user.getAvatar() != null) {
            userDAOOneById.setAvatar(user.getAvatar());
        }
        userDAOOneById.setSign(user.getSign());
        userDAO.save(userDAOOneById);
        //remove the token in redis when next time i need token, refreshtoken will create one
        String cookiekey = CookieUtils.getCookie(request, Constrants.User.KEY_TOKEN_COOKIE);
      //  redisUtil.del(cookiekey);

        return ResponseResult.SUCCESS("Edit is Success !").setData(userDAOOneById);
    }

    @Override
    public ResponseResult deleteUserById(HttpServletRequest request, HttpServletResponse response, String id) {

        int result = userDAO.deleteUserByState(id);
        if (result > 0) {
            return ResponseResult.SUCCESS("Delete ! ");
        }
        return ResponseResult.FAIL("No user");
    }

    @Override
    public ResponseResult ListUsers(int page, int size,
                                    HttpServletRequest request,
                                    HttpServletResponse response) {

        //user is admin
        if (page < Constrants.Page.DEFAULT_PAGE) {
            page = Constrants.Page.DEFAULT_PAGE;
        }
        if (size < Constrants.Page.MIN_SIZE) {
            size = Constrants.Page.MIN_SIZE;
        }
        //sorted by register time
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by("createTime").descending());
        Page<UserNoPassword> userDAOAll = userNoPasswordDAO.findAll(pageable);

        return ResponseResult.SUCCESS("Success to get All users").setData(userDAOAll);
    }

    /**
     * change password
     *
     * @param verifycode
     * @param user
     * @return
     */

    @Override
    public ResponseResult updateUserPassword(String verifycode, User user) {
        if (user.getEmail() == null) {
            return ResponseResult.FAIL("Email can not be null");
        }
        //use email to find code from redis
        String redisVerify = (String) redisUtil.get(Constrants.User.KEY_EMAIL_CODE_CONTENT + user.getEmail());
        if (!redisVerify.equals(verifycode) || verifycode == null) {
            return ResponseResult.FAIL("verifycode is wrong");
        }
        redisUtil.del(Constrants.User.KEY_EMAIL_CODE_CONTENT + user.getEmail());
        int result = userDAO.updatePasswordByEmail(bCryptPasswordEncoder.encode(user.getPassword()), user.getEmail());

        return result > 0 ? ResponseResult.SUCCESS("Success to change the passowrd ") : ResponseResult.FAIL("Cant find user");
    }

    @Override
    public ResponseResult updateEmail(String email, String code) {
        User user = checkUser();
        if (user == null) {
            return ResponseResult.ACCOUNT_NOT_LOGIN();
        }
        String verfifyCode = (String) redisUtil.get(Constrants.User.KEY_EMAIL_CODE_CONTENT + user.getEmail());
        if (verfifyCode == null || !verfifyCode.equals(code)) {
            return ResponseResult.FAIL("verfiy code is wrong");
        }
        int result = userDAO.updateEmailById(email, user.getId());

        return result > 0 ? ResponseResult.SUCCESS("Success to change the email ") : ResponseResult.FAIL("Cant find user");

    }

    @Override
    public ResponseResult userLogout() {


        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        HttpServletResponse response = servletRequestAttributes.getResponse();
        //get token key
        String tokenKey = CookieUtils.getCookie(request, Constrants.User.KEY_TOKEN_COOKIE);
        if (tokenKey == null) {
            return ResponseResult.FAIL("Not log in");
        }
        redisUtil.del(Constrants.User.KEY_TOKEN + tokenKey);
        //delete mysql refreshtoken:
        refreshTokenDAO.deleteAllByTokenKey(tokenKey);
        //delete cookie 's token key
        CookieUtils.deleteCookie(response, Constrants.User.KEY_TOKEN_COOKIE);
        return ResponseResult.SUCCESS("Logout is successful");
    }

    private User parseByTokenKey(String tokenKey) {
        String token = (String) redisUtil.get(Constrants.User.KEY_TOKEN + tokenKey);
        if (token != null) {
            try {
                Claims claims = JwtUtil.parseJWT(token);
                return ClaimsUtil.claims2User(claims);
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }
    public static boolean isValid(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
    public   ResponseResult successUser(User user, String ip){

        ResponseResult responseResult = ResponseResult.SUCCESS("Log in Success ! ");
        User resUser = new User();
        if(ip != null){
            resUser.setLoginIp(ip);
            resUser.setRegIp(ip);

        }
        resUser.setId(user.getId());
        resUser.setUserName(user.getUserName());
        resUser.setEmail(user.getEmail());
        resUser.setAvatar(user.getAvatar());
        responseResult.setData(resUser);
        return  responseResult;
    }


}

