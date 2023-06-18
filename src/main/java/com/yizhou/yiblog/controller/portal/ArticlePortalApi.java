package com.yizhou.yiblog.controller.portal;

import com.yizhou.yiblog.response.ResponseResult;
import com.yizhou.yiblog.service.IArticleService;
import com.yizhou.yiblog.util.Constrants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/portal/article")
@CrossOrigin("*")
public class ArticlePortalApi {

    @Autowired
    private IArticleService iArticleService;

    @GetMapping("/hello")
    public String TestApi() {
        return "Hello World";
    }



        /*
    @GetMapping("/list")
    public ResponseResult listArticle(@RequestBody int page,
        @RequestBody int size) {

         */
        @GetMapping("/list/{page}/{size}")
        public ResponseResult listArticle(@PathVariable("page") int page,
                                          @PathVariable("size") int size) {
        return iArticleService.ListArticles(page, size, null, null, Constrants.Article.STATE_PUBLISH);
    }

    @GetMapping("/list/{categoryId}/{page}/{size}")
    public ResponseResult listArticleByCategory(@PathVariable("categoryId") String id,
                                                @PathVariable("page") int page,
                                                @PathVariable("size") int size) {
        return iArticleService.ListArticles(page, size, null, id, Constrants.Article.STATE_PUBLISH);
    }

    @GetMapping("/{articleId}")
    public ResponseResult GetArticleDetail(@PathVariable("articleId") String id) {
        return iArticleService.getArticleById(id);
    }
    @GetMapping("/getbyUserId/{userId}")
    public ResponseResult getListByUserId(@PathVariable("userId") String id) {
        return iArticleService.getListByUserId(id);
    }

    @GetMapping("/getbyUserName/{userName}/{page}/{size}")
    public ResponseResult getListByUserName(@PathVariable("userName") String userName,
                                            @PathVariable("page") int page,
                                            @PathVariable("size") int size) {
        return iArticleService.getListByUserName(userName,page,size);
    }



    /**
     * use label to get if user interest about those article
     * label has one or more,
     * find one of the label ---> make the recommend is not the same everytime,
     * use label to find if other article to have the same label
     * if not, then get the newest article from database
     *
     * @param id
     * @return
     */

    @GetMapping("/recommend/{articleId}/{size}")
    public ResponseResult GetRecommendArticle(@PathVariable("articleId") String id,
                                              @PathVariable("size") int size) {
        return iArticleService.ListRecommendArticle(id, size);
    }

    @GetMapping("/top")
    public ResponseResult GetTopArticle() {
        return iArticleService.ListTopArticles();
    }

    @GetMapping("/list/label/{label}/{page}/{size}")
    public ResponseResult listArticleByLabel(@PathVariable("label") String label,
                                             @PathVariable("page") int page,
                                             @PathVariable("size") int size) {
        return iArticleService.ListArticlesByLabel(page, size, label);
    }
    @GetMapping("/deleteOwnArticle/{id}/{userName}")
    public ResponseResult deleteByArticleId(@PathVariable("id") String id,
                                            @PathVariable("userName") String userName) {
        return iArticleService.normaldeleteArticleById(id,userName);
    }

    @GetMapping("/labels/{size}")
    public ResponseResult GetLabels(@PathVariable("size") int size) {
        return iArticleService.ListLabels(size);
    }


}
