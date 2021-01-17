package com.yizhou.yiblog.service;

import com.yizhou.yiblog.pojo.Article;
import com.yizhou.yiblog.response.ResponseResult;

public interface IArticleService {
    ResponseResult PostArticle(Article article);

    ResponseResult ListArticles(int page, int size, String keyword, String categoryId, String state);

    ResponseResult getArticleById(String articleId);

    ResponseResult updateArticle(String articleId, Article article);

    ResponseResult deleteArticleId(String articleId);

    ResponseResult deleteArticleByState(String id);

    ResponseResult topArticleState(String id);

    ResponseResult ListTopArticles();

    ResponseResult ListRecommendArticle(String id, int size);

    ResponseResult ListArticlesByLabel(int page, int size, String label);

    ResponseResult ListLabels(int size);
}
