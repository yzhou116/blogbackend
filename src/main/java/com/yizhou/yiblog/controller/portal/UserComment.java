package com.yizhou.yiblog.controller.portal;

import com.yizhou.yiblog.pojo.Comment;
import com.yizhou.yiblog.response.ResponseResult;
import com.yizhou.yiblog.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/portal/usercomment")
//@CrossOrigin("*")
public class UserComment {

    @Autowired
    private ICommentService iCommentService;

    @PostMapping
    public ResponseResult postComment(@RequestBody Comment comment) {
        return iCommentService.postComment(comment);

    }

    @GetMapping("/list")
    public ResponseResult getComments() {
        return iCommentService.listComments();

    }

    @RequestMapping(value = "/hello" , method = RequestMethod.GET)
    public String ccc() {
        return "ccc";

    }
}
