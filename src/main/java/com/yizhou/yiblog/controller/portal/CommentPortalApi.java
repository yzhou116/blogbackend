package com.yizhou.yiblog.controller.portal;

import com.yizhou.yiblog.pojo.Comment;
import com.yizhou.yiblog.response.ResponseResult;
import com.yizhou.yiblog.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@CrossOrigin
@RestController
@RequestMapping("/portal/comment")
public class CommentPortalApi {

    @Autowired
    private ICommentService iCommentService;

    @PostMapping
    public ResponseResult postComment(@RequestBody Comment comment) {
        return iCommentService.postComment(comment);

    }

    @DeleteMapping("/{commentId}")
    public ResponseResult deleteComment(@PathVariable("commentId") String commentId) {
        return iCommentService.deleteCommentById(commentId);

    }

    @GetMapping("/list/{articleId}/{page}/{size}")
    public ResponseResult listComment(@PathVariable("articleId") String articleId,
                                      @PathVariable("page") int page,
                                      @PathVariable("size") int size) {
        return iCommentService.ListCommentById(articleId, page, size);

    }

    @GetMapping("/listss")
    public ResponseResult getComments(@PathVariable("count") String count) {
        return iCommentService.listComments();

    }


}
