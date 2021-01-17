package com.yizhou.yiblog.dao;

import com.yizhou.yiblog.pojo.Article;
import com.yizhou.yiblog.pojo.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ArticleDAO extends JpaRepository<Article, String>, JpaSpecificationExecutor<Article> {
    Article findOneById(String id);

    @Modifying
    int deleteAllById(String articleId);

    @Modifying
    @Query(nativeQuery = true, value = "update `tb_article` set `state` = '0' where `id` = ?")
    int deleteArticleByState(String articleId);

    @Modifying
    @Query(nativeQuery = true, value = "update `tb_article` set `state` = '3' where `id` = ?")
    int topArticleByState(String articleId);

    @Query(nativeQuery = true, value = "select `labels` from `tb_article` where `id` = ?")
    String ListArticleLabelById(String articleId);


    @Query(nativeQuery = true, value = "select * from `tb_article` where `labels` like ? and `id` != ? and (`state` = '1' or `state` = '3') limit ?")
    List<Article> listArticleByLabel(String label, String originalArticleId, int size);

    @Query(nativeQuery = true, value = "select * from `tb_article` where `id` != ? and (`state` = '1' or `state` = '3')  order by `create_time` desc limit ?")
    List<Article> listRestOfArticleBySize(String originalArticleId, int size);


}
