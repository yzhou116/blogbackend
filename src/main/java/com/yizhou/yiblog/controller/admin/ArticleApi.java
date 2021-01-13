package com.yizhou.yiblog.controller.admin;

import com.yizhou.yiblog.pojo.Article;
import com.yizhou.yiblog.pojo.Looper;
import com.yizhou.yiblog.response.ResponseResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/article")
public class ArticleApi {

    @PostMapping
    public ResponseResult addArticle(@RequestBody Article article) {
        return null;

    }

    @DeleteMapping("/{articleId}")
    public ResponseResult deleteArticle(@PathVariable("articleId") String articleId) {
        return null;

    }

    @PostMapping("/{articleId}")
    public ResponseResult updateArticle(@PathVariable("articleId") String articleId) {
        return null;

    }

    @GetMapping("/{articleId}")
    public ResponseResult getArticle(@PathVariable("articleId") String articleId) {
        return null;

    }

    @GetMapping("/list")
    public ResponseResult listArticle(@RequestParam("page") int page,
                                      @RequestParam("size") int size) {
        return null;

    }

    @PutMapping("/state/{articleId}/{state}")
    public ResponseResult updateArticleState(@PathVariable("articleId") String id,
                                             @PathVariable("state") String state) {
        return null;
    }

    @PutMapping("/top/{articleId}")
    public ResponseResult topArticleState(@PathVariable("articleId") String id) {

        return null;
    }


}
