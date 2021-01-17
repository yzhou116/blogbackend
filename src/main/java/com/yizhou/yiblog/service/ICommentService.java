package com.yizhou.yiblog.service;

import com.yizhou.yiblog.pojo.Comment;
import com.yizhou.yiblog.response.ResponseResult;

public interface ICommentService {
    ResponseResult postComment(Comment comment);

    ResponseResult ListCommentById(String articleId, int page, int size);

    ResponseResult deleteCommentById(String commentId);

    ResponseResult ListComments(int page, int size);

    ResponseResult topComment(String id);
}
