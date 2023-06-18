package com.yizhou.yiblog.controller.admin;

import com.yizhou.yiblog.pojo.Comment;
import com.yizhou.yiblog.response.ResponseResult;
import com.yizhou.yiblog.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/comment")
public class CommentApi {
    @Autowired
    private ICommentService iCommentService;

    //@PreAuthorize("@permission.admin()")
    @DeleteMapping("/{commentId}")
    public ResponseResult deleteComment(@PathVariable("commentId") String commentId) {
        return iCommentService.deleteCommentById(commentId);

    }

    //@PreAuthorize("@permission.admin()")
    @GetMapping("/{commentId}")
    public ResponseResult getComment(@PathVariable("commentId") String commentId) {
        return null;

    }

    //@PreAuthorize("@permission.admin()")
    @GetMapping("/list")
    public ResponseResult listComment(@RequestParam("page") int page,
                                      @RequestParam("size") int size) {
        return iCommentService.ListComments(page, size);

    }

    @PreAuthorize("@permission.admin()")
    @PutMapping("/top/{commentId}")
    public ResponseResult topComment(@PathVariable("commentId") String id) {
        return iCommentService.topComment(id);
    }

}
