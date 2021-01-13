package com.yizhou.yiblog.controller.admin;

import com.yizhou.yiblog.pojo.Comment;
import com.yizhou.yiblog.response.ResponseResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/comment")
public class CommentApi {


    @DeleteMapping("/{commentId}")
    public ResponseResult deleteComment(@PathVariable("commentId") String commentId) {
        return null;

    }

    @GetMapping("/{commentId}")
    public ResponseResult getComment(@PathVariable("commentId") String commentId) {
        return null;

    }

    @GetMapping("/list")
    public ResponseResult listComment(@RequestParam("page") int page,
                                      @RequestParam("size") int size) {
        return null;

    }

    @PutMapping("/top/{commentId}")
    public ResponseResult topComment(@PathVariable("commentId") String id) {
        return null;
    }

}
