package com.yizhou.yiblog.controller.admin;

import com.yizhou.yiblog.pojo.Article;
import com.yizhou.yiblog.pojo.Looper;
import com.yizhou.yiblog.response.ResponseResult;
import com.yizhou.yiblog.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/article")
public class ArticleApi {

    @Autowired
    private IArticleService iArticleService;

    @PreAuthorize("@permission.admin()")
    @PostMapping
    public ResponseResult addArticle(@RequestBody Article article) {
        return iArticleService.PostArticle(article);

    }

    @PreAuthorize("@permission.admin()")
    @DeleteMapping("/{articleId}")
    public ResponseResult deleteArticle(@PathVariable("articleId") String articleId) {
        return iArticleService.deleteArticleId(articleId);

    }

    @PreAuthorize("@permission.admin()")
    @PostMapping("/{articleId}")
    public ResponseResult updateArticle(@PathVariable("articleId") String articleId,
                                        @RequestBody Article article) {
        return iArticleService.updateArticle(articleId, article);

    }

    @PreAuthorize("@permission.admin()")
    @GetMapping("/{articleId}")
    public ResponseResult getArticle(@PathVariable("articleId") String articleId) {
        return iArticleService.getArticleById(articleId);

    }

    @PreAuthorize("@permission.admin()")
    @GetMapping("/list/{page}/{size}")
    public ResponseResult listArticle(@PathVariable("page") int page,
                                      @PathVariable("size") int size,
                                      @RequestParam(value = "state", required = false) String state,
                                      @RequestParam(value = "keyword", required = false) String keyword,
                                      @RequestParam(value = "categoryId", required = false) String categoryId) {

        return iArticleService.ListArticles(page, size, keyword, categoryId, state);

    }

    @PreAuthorize("@permission.admin()")
    @DeleteMapping("/state/{articleId}")
    public ResponseResult deleteArticleByState(@PathVariable("articleId") String id) {

        return iArticleService.deleteArticleByState(id);
    }

    @PreAuthorize("@permission.admin()")
    @PutMapping("/top/{articleId}")
    public ResponseResult topArticleState(@PathVariable("articleId") String id) {

        return iArticleService.topArticleState(id);
    }


}
