package com.yizhou.yiblog.service;

import com.yizhou.yiblog.dao.ArticleDAO;
import com.yizhou.yiblog.dao.CommentDAO;
import com.yizhou.yiblog.pojo.Article;
import com.yizhou.yiblog.pojo.Category;
import com.yizhou.yiblog.pojo.Comment;
import com.yizhou.yiblog.pojo.User;
import com.yizhou.yiblog.response.ResponseResult;
import com.yizhou.yiblog.util.Constrants;
import com.yizhou.yiblog.util.SnowflakeIdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class CommentServiceImpl implements ICommentService {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private ArticleDAO articleDAO;

    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;

    @Autowired
    private CommentDAO commentDAO;


    @Override
    public ResponseResult postComment(Comment comment) {
        User user = iUserService.checkUser();
        if (user == null) {
            return ResponseResult.FAIL("Not login");
        }
        String articleId = comment.getArticleId();
        comment.setState(Constrants.Comment.STATE_PUBLISH);
        if (articleId == null) {
            return ResponseResult.FAIL("Article is null");
        }
        Article oneById = articleDAO.findOneById(comment.getArticleId());
        if (oneById == null) {
            return ResponseResult.FAIL("No article");
        }
        String content = comment.getContent();
        if (content == null) {
            return ResponseResult.FAIL("Content is null");
        }
        comment.setId(snowflakeIdWorker.nextId() + "");
        comment.setCreateTime(new Date());
        comment.setUpdateTime(new Date());
        comment.setUserAvatar(user.getAvatar());
        comment.setUserName(user.getUserName());
        comment.setUserId(user.getId());
        commentDAO.save(comment);

        return ResponseResult.SUCCESS("Success");
    }

    /**
     * how to sort the comment
     * basic: sort comment by time,
     * when people post their comment, the first hour will put comment at the top
     * after first hour  sort it by the thumb up numbers
     *
     * @param articleId
     * @param page
     * @param size
     * @return
     */

    @Override
    public ResponseResult ListCommentById(String articleId, int page, int size) {
        if (page < Constrants.Page.DEFAULT_PAGE) {
            page = Constrants.Page.DEFAULT_PAGE;
        }
        if (size < Constrants.Page.MIN_SIZE) {
            size = Constrants.Page.MIN_SIZE;
        }
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by(Sort.Direction.DESC, "createTime"));
        Page<Comment> all = commentDAO.findAll(pageable);

        return ResponseResult.SUCCESS("Success").setData(all);
    }

    @Override
    public ResponseResult deleteCommentById(String commentId) {
        User user = iUserService.checkUser();
        if (user == null) {
            return ResponseResult.FAIL("Not log in");
        }
        Comment comment = commentDAO.findOneById(commentId);
        if (comment == null) {
            return ResponseResult.FAIL("Comment is not exist");
        }
        if (user.getId().equals(comment.getUserId()) || user.getRoles().equals(Constrants.User.ROLE_ADMIN)) {
            commentDAO.deleteById(commentId);
            return ResponseResult.SUCCESS("Success!");
        } else {
            return ResponseResult.PERMISSION_DENIED();
        }

    }

    @Override
    public ResponseResult ListComments(int page, int size) {
        if (page < Constrants.Page.DEFAULT_PAGE) {
            page = Constrants.Page.DEFAULT_PAGE;
        }
        if (size < Constrants.Page.MIN_SIZE) {
            size = Constrants.Page.MIN_SIZE;
        }
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by("state", "createTime").descending());
        Page<Comment> all = commentDAO.findAll(pageable);

        return ResponseResult.SUCCESS("Success").setData(all);
    }

    @Override
    public ResponseResult topComment(String id) {
        Comment comment = commentDAO.findOneById(id);
        if (comment == null) {
            return ResponseResult.FAIL("Can't find comment");
        }
        if (comment.getState().equals(Constrants.Comment.STATE_TOP)) {
            comment.setState(Constrants.Comment.STATE_PUBLISH);
            return ResponseResult.SUCCESS("Success to change top comment to publish");

        } else if (comment.getState().equals(Constrants.Comment.STATE_PUBLISH)) {
            comment.setState(Constrants.Comment.STATE_TOP);
            return ResponseResult.SUCCESS("Success to top the comment");
        }
        return ResponseResult.FAIL("Can't support this behavior");
    }
}
