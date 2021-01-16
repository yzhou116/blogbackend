package com.yizhou.yiblog.dao;

import com.yizhou.yiblog.pojo.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CommentDAO extends JpaRepository<Comment, String>, JpaSpecificationExecutor<Comment> {
    Comment findOneById(String commentId);
}
