package com.yizhou.yiblog.controller.user;

import com.wf.captcha.ArithmeticCaptcha;
import com.wf.captcha.GifCaptcha;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import com.yizhou.yiblog.pojo.User;
import com.yizhou.yiblog.response.ResponseResult;

import com.yizhou.yiblog.service.IUserService;
import com.yizhou.yiblog.util.Constrants;
import com.yizhou.yiblog.util.RedisUtil;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.Locale;
import java.util.Random;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserApi {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private Random random;


    @PostMapping("/admin_account")
    public ResponseResult initManagerAccount(@RequestBody User user, HttpServletRequest request) {
        System.out.println(user.getUserName());
        System.out.println(user.getPassword());
        System.out.println(user.getEmail());
        return iUserService.initAdminAccount(user, request);
    }

    /**
     * register
     *
     * @param user
     * @return
     */
    @PostMapping("/register")
    public ResponseResult register(@RequestBody User user,
                                   @RequestParam("email_code") String code,
                                   @RequestParam("captcha_code") String captchacode,
                                   @RequestParam("captcha_key") String captcha_key,
                                   HttpServletRequest request) {
        return iUserService.registerUser(user, code, captchacode, captcha_key, request);

    }

    /**
     * set the random type for captcha
     */
    /**
     * 1,User log in -->userName or Email
     * 2,password
     * 3,graphic check
     * 4, graphic key
     *
     * @param captchaKey
     * @param captcha
     * @param user
     * @return
     */


    @PostMapping("/login/{captcha}/{captcha_key}")
    public ResponseResult Login(@PathVariable("captcha_key") String captchaKey,
                                @PathVariable("captcha") String captcha,
                                @RequestBody User user,
                                HttpServletRequest request,
                                HttpServletResponse response) {
        return iUserService.CheckLogin(captcha, captchaKey, user, request, response);
    }

    @GetMapping("/captcha")
    public void getCaptcha(HttpServletResponse response,
                           @RequestParam("captcha_key") String captcha) {

        try {
            iUserService.createCaptcha(response, captcha);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FontFormatException e) {
            e.printStackTrace();
        }

    }

    @GetMapping("/verify_code")
    public ResponseResult SendVerifyCode(HttpServletRequest request,
                                         @RequestParam("type") String type,
                                         @RequestParam("email") String emailAddress) {

        return iUserService.sendEmailVerification(type, request, emailAddress);
    }

    /**
     * change password or find password back
     * change password
     * use old one to change to new one
     * find password
     * send verification code to email or phone -> to check code
     * change password and find password
     * Process :
     * 1,user input Email
     * 2 user get code type = forget
     * 3 user input code
     * 4 user input new passowrd
     * 5 submit
     *
     * @param verifycode
     * @param user
     * @return
     */
    @PutMapping("/password/{verifycode}")
    public ResponseResult UpdatePassword(@PathVariable("verifycode") String verifycode,
                                         @RequestBody User user) {
        return iUserService.updateUserPassword(verifycode, user);

    }

    @GetMapping("/user_info/{userId}")
    public ResponseResult getUserInfo(@PathVariable("userId") String id) {

        return iUserService.getUserInfo(id);
    }

    @PutMapping("/user_info/{userId}")
    public ResponseResult updateUserInfo(HttpServletRequest request,
                                         HttpServletResponse response,
                                         @PathVariable("userId") String id,
                                         @RequestBody User user) {


        return iUserService.UpdateUserInfo(request, response, id, user);

    }

    /**
     * need admin role:
     *
     * @param page
     * @param size
     * @return
     */
    @PreAuthorize("@permission.admin()")
    @GetMapping("/list")
    public ResponseResult listUsers(@RequestParam("page") int page,
                                    @RequestParam("size") int size,
                                    HttpServletRequest request,
                                    HttpServletResponse response) {
        return iUserService.ListUsers(page, size, request, response);
    }

    @PreAuthorize("@permission.admin()")
    @DeleteMapping("/{userId}")
    public ResponseResult deleteUser(HttpServletRequest request,
                                     HttpServletResponse response,
                                     @PathVariable("userId") String id) {
        return iUserService.deleteUserById(request, response, id);

    }

    /**
     * below two method can help frontend guy to use ajax to find
     * out if user name or email does exist in our database
     *
     * @param email
     * @return
     */
    @ApiResponses({
            @ApiResponse(code = 204, message = "Email is resgisted"),
            @ApiResponse(code = 205, message = "Email is not registerd")
    })

    @GetMapping("/email")
    public ResponseResult checkEmail(@RequestParam("email") String email) {

        return iUserService.checkEmail(email);
    }

    @ApiResponses({
            @ApiResponse(code = 204, message = "username is resgisted"),
            @ApiResponse(code = 205, message = "username is not registerd")
    })
    @GetMapping("/user_name")
    public ResponseResult checkUserName(@RequestParam("userName") String userName) {

        return iUserService.checkUserName(userName);
    }

    @PutMapping("/email")
    public ResponseResult updateEmail(@RequestParam("email") String email,
                                      @RequestParam("verify_code") String code) {

        return iUserService.updateEmail(email, code);
    }

    /**
     * process:
     * get token key
     * delete redis token
     * delete mysql refreshtoken
     * delete cookie token key
     *
     * @return
     */
    @GetMapping("/logout")
    public ResponseResult logOut() {
        return iUserService.userLogout();


    }
/*

    @RequestMapping("/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 设置请求头为输出图片类型
        response.setContentType("image/gif");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        // 三个参数分别为宽、高、位数
        SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, 5);
        // 设置字体
        specCaptcha.setFont(new Font("Verdana", Font.PLAIN, 32));  // 有默认字体，可以不用设置
        // 设置类型，纯数字、纯字母、字母数字混合
        specCaptcha.setCharType(Captcha.TYPE_ONLY_NUMBER);

        String aCase = specCaptcha.text().toLowerCase();

        // 验证码存入session
      //  request.getSession().setAttribute("captcha", specCaptcha.text().toLowerCase());
        redisUtil.set(Constrants.User.KEY_CAPTCHA_CONTENT + "123456",aCase,60*10);


        // 输出图片流
        specCaptcha.out(response.getOutputStream());
    }*/


}
