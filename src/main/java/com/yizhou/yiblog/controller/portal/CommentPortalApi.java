package com.yizhou.yiblog.controller.portal;

import com.yizhou.yiblog.pojo.Comment;
import com.yizhou.yiblog.response.ResponseResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/portal/comment")
public class CommentPortalApi {

    @PostMapping
    public ResponseResult postComment(@RequestBody Comment comment) {
        return null;

    }

    @DeleteMapping("/{commentId}")
    public ResponseResult deleteComment(@PathVariable("commentId") String commentId) {
        return null;

    }

    @GetMapping("/list/{articleId} ")
    public ResponseResult listComment(@PathVariable("articleId") String articleId) {
        return null;

    }


}
