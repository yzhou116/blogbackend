package com.yizhou.yiblog.controller;

import com.yizhou.yiblog.response.ResponseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * error page
 */
@RestController
public class ErrorPageController {

    @GetMapping("/404")
    public ResponseResult page404() {
        return ResponseResult.FAIL("Cant find page ERROR 404");
    }

    @GetMapping("/403")
    public ResponseResult page403() {
        return ResponseResult.FAIL("No Aurithorization ERROE403");
    }

    @GetMapping("/504")
    public ResponseResult page504() {
        return ResponseResult.FAIL("Server is busy !  ERROR 504");
    }

    @GetMapping("/505")
    public ResponseResult page505() {
        return ResponseResult.FAIL("Request is not supported please check your data ERROR 505");
    }

}
