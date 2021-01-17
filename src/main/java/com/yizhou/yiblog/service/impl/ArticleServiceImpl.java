package com.yizhou.yiblog.service.impl;

import com.yizhou.yiblog.dao.ArticleDAO;
import com.yizhou.yiblog.dao.LabelDAO;
import com.yizhou.yiblog.pojo.Article;
import com.yizhou.yiblog.pojo.Category;
import com.yizhou.yiblog.pojo.Labels;
import com.yizhou.yiblog.pojo.User;
import com.yizhou.yiblog.response.ResponseResult;
import com.yizhou.yiblog.service.IArticleService;
import com.yizhou.yiblog.service.IUserService;
import com.yizhou.yiblog.util.Constrants;
import com.yizhou.yiblog.util.SnowflakeIdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * for state of article:
 * 0 means deleted, 1 means post, 2 means draft, 3 means keep it at top
 */

@Transactional
@Service
public class ArticleServiceImpl implements IArticleService {
    @Autowired
    private IUserService iUserService;
    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;
    @Autowired
    private ArticleDAO articleDAO;
    @Autowired
    private Random random;
    @Autowired
    private LabelDAO labelDAO;

    @Override
    public ResponseResult PostArticle(Article article) {

        User user = iUserService.checkUser();
        if (user == null) {
            ResponseResult.FAIL("No User log in");
        }
        String title = article.getTitle();
        if (title == null) {
            return ResponseResult.FAIL("Title is null");
        }
        String state = article.getState();
        if (!Constrants.Article.STATE_PUBLISH.equals(state) && !Constrants.Article.STATE_DRAFT.equals(state)) {
            return ResponseResult.FAIL("Can't Support");
        }
        String type = article.getType();
        if (type == null) {
            return ResponseResult.FAIL("Type is null");
        }
        if (!"0".equals(type) && !"1".equals(type)) {
            return ResponseResult.FAIL("Type is wrong");
        }

        if (Constrants.Article.STATE_PUBLISH.equals(state)) {
            if (title.length() > Constrants.Article.TITLE_MAX_LENGTH) {
                return ResponseResult.FAIL("Title is too long");
            }

            String content = article.getContent();
            if (content == null) {
                return ResponseResult.FAIL("Content is null");
            }

            String summary = article.getSummary();
            if (summary == null) {
                return ResponseResult.FAIL("Summary is null");
            }
            if (summary.length() > Constrants.Article.SUMMARY_MAX_LENGTH) {
                return ResponseResult.FAIL("Summary is long");
            }
            String labels = article.getLabels();
            if (labels == null) {
                return ResponseResult.FAIL("Label is null");
            }
        } else {

        }
        String articleId = article.getId();
        if (articleId == null) {
            article.setId(snowflakeIdWorker.nextId() + "");
            article.setState(Constrants.Article.STATE_PUBLISH);
            article.setUserName(user.getUserName());
            article.setUserAvatar(user.getAvatar());
            article.setViewCount(1);
            article.setUserId(user.getId());

        } else {
            Article oneById = articleDAO.findOneById(articleId);
            if (Constrants.Article.STATE_PUBLISH.equals(oneById.getState()) && Constrants.Article.STATE_DRAFT.equals(state)) {
                return ResponseResult.FAIL("Can't post it more than twice");
            }
        }
        article.setCreateTime(new Date());
        article.setUpdateTime(new Date());
        articleDAO.save(article);
        this.setUpLabel(article.getLabels());
        if (Constrants.Article.STATE_DRAFT.equals(state)) {
            return ResponseResult.SUCCESS("Draft post Success");
        }
        return ResponseResult.SUCCESS("Success").setData(article.getId());
    }

    /**
     * @param keyword(search by keyword)
     * @param page
     * @param size
     * @param categoryId
     * @param state(deleted article)
     * @return
     */

    @Override
    public ResponseResult ListArticles(int page, int size,
                                       String keyword, String categoryId,
                                       String state) {
        if (page < Constrants.Page.DEFAULT_PAGE) {
            page = Constrants.Page.DEFAULT_PAGE;
        }
        if (size < Constrants.Page.MIN_SIZE) {
            size = Constrants.Page.MIN_SIZE;
        }
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by("createTime").descending());
        Page<Article> categoryList = articleDAO.findAll(new Specification<Article>() {
            List<Predicate> list = new ArrayList<>();

            @Override
            public Predicate toPredicate(Root<Article> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                if (keyword != null) {
                    Predicate keywordPre = cb.like(root.get("title").as(String.class), "%" + keyword + "%");
                    list.add(keywordPre);
                }
                if (state != null) {
                    Predicate statePre = cb.equal(root.get("state").as(String.class), state);
                    list.add(statePre);
                }
                if (categoryId != null) {
                    Predicate categoryPre = cb.equal(root.get("categoryId").as(String.class), categoryId);
                    list.add(categoryPre);
                }
                Predicate[] array = new Predicate[list.size()];
                list.toArray(array);

                return cb.and(array);
            }
        }, pageable);

        return ResponseResult.SUCCESS("Success").setData(categoryList);
    }

    @Override
    public ResponseResult getArticleById(String articleId) {
        Article article = articleDAO.findOneById(articleId);
        if (article == null) {
            return ResponseResult.FAIL("Article is not exist");
        }
        String state = article.getState();
        if (Constrants.Article.STATE_PUBLISH.equals(state) || Constrants.Article.STATE_TOP.equals(state)) {
            return ResponseResult.SUCCESS("Success").setData(article);
        }
        //need to check if user is admin:
        User user = iUserService.checkUser();
        //  String roles = user.getRoles();
        if (user == null || !user.getRoles().equals(Constrants.User.ROLE_ADMIN)) {
            return ResponseResult.PERMISSION_DENIED();
        }
        return ResponseResult.SUCCESS("Success").setData(article);
    }

    @Override
    public ResponseResult updateArticle(String articleId, Article article) {
        Article oneById = articleDAO.findOneById(articleId);
        if (oneById == null) {
            return ResponseResult.FAIL("No article");
        }
        String title = article.getTitle();
        if (title != null) {
            oneById.setTitle(title);
        }
        String summary = article.getSummary();
        if (summary != null) {
            oneById.setSummary(summary);
        }
        String content = article.getContent();
        if (content != null) {
            oneById.setContent(content);
        }
        String labels = article.getLabels();
        if (labels != null) {
            oneById.setLabels(labels);
        }
        String categoryId = article.getCategoryId();
        if (categoryId != null) {
            oneById.setCategoryId(categoryId);
        }
        oneById.setCover(article.getCover());
        oneById.setUpdateTime(new Date());
        articleDAO.save(oneById);

        return ResponseResult.SUCCESS("Success");
    }

    @Override
    public ResponseResult deleteArticleId(String articleId) {
        int result = articleDAO.deleteAllById(articleId);
        return result > 0 ? ResponseResult.SUCCESS("Success") : ResponseResult.FAIL("No this article");
    }

    @Override
    public ResponseResult deleteArticleByState(String id) {
        int result = articleDAO.deleteArticleByState(id);

        return result > 0 ? ResponseResult.SUCCESS("Success") : ResponseResult.FAIL("No this article");
    }

    @Override
    public ResponseResult topArticleState(String id) {
        Article oneById = articleDAO.findOneById(id);
        if (oneById == null) {
            return ResponseResult.FAIL("No article");
        }
        String state = oneById.getState();
        if (Constrants.Article.STATE_PUBLISH.equals(state)) {
            oneById.setState(Constrants.Article.STATE_TOP);
      /*      int result = articleDAO.topArticleByState(id);
            return result>0 ? ResponseResult.SUCCESS("Success to top article")
                    : ResponseResult.FAIL("No this article");*/
            articleDAO.save(oneById);
            return ResponseResult.SUCCESS("Top the article");
        }
        if (Constrants.Article.STATE_TOP.equals(state)) {
            oneById.setState(Constrants.Article.STATE_PUBLISH);
      /*      int result = articleDAO.topArticleByState(id);
            return result>0 ? ResponseResult.SUCCESS("Success to top article")
                    : ResponseResult.FAIL("No this article");*/
            articleDAO.save(oneById);
            return ResponseResult.SUCCESS("stop to top this article");
        }

        return ResponseResult.FAIL("Can't support this behavior");
    }

    private void setUpLabel(String label) {
        List<String> list = new ArrayList<>();
        if (label.contains("-")) {
            list.addAll(Arrays.asList(label.split("-")));
        } else {
            list.add(label);
        }
        for (String labels : list) {
            //    Labels targetLabel = labelDAO.findOneByName(labels);
          /*  if (targetLabel ==null) {


            }
            Integer count = targetLabel.getCount();
            targetLabel.setCount(++count);
            targetLabel.setUpdateTime(new Date());*/
            int result = labelDAO.updateCountByName(labels);
            if (result == 0) {
                Labels targetLabel = new Labels();
                targetLabel.setId(snowflakeIdWorker.nextId() + "");
                targetLabel.setCount(1);
                targetLabel.setName(labels);
                targetLabel.setCreateTime(new Date());
                targetLabel.setUpdateTime(new Date());
                labelDAO.save(targetLabel);
            }

        }
    }

    @Override
    public ResponseResult ListTopArticles() {

        List<Article> result = articleDAO.findAll(new Specification<Article>() {
            @Override
            public Predicate toPredicate(Root<Article> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("state").as(String.class), Constrants.Article.STATE_TOP);
            }
        });

        return ResponseResult.SUCCESS("Success").setData(result);
    }

    @Override
    public ResponseResult ListRecommendArticle(String id, int size) {
        String labels = articleDAO.ListArticleLabelById(id);
        List<String> labelsList = new ArrayList<>();
        if (!labels.contains("-")) {
            labelsList.add(labels);
        } else {
            labelsList.addAll(Arrays.asList(labels.split("-")));
        }
        String targetLabel = labelsList.get(random.nextInt(labelsList.size()));
        List<Article> articleList = articleDAO.listArticleByLabel("%" + targetLabel + "%", id, size);
        if (articleList.size() < size) {
            //means not enougth relative article
            int dxSize = size = articleList.size();
            List<Article> dxList = articleDAO.listRestOfArticleBySize(id, dxSize);
            articleList.addAll(dxList);
        }
        return ResponseResult.SUCCESS("Success").setData(articleList);
    }

    @Override
    public ResponseResult ListArticlesByLabel(int page, int size, String label) {
        if (page < Constrants.Page.DEFAULT_PAGE) {
            page = Constrants.Page.DEFAULT_PAGE;
        }
        if (size < Constrants.Page.MIN_SIZE) {
            size = Constrants.Page.MIN_SIZE;
        }
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by("createTime").descending());
        Page<Article> articles = articleDAO.findAll(new Specification<Article>() {
            @Override
            public Predicate toPredicate(Root<Article> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate predicate = criteriaBuilder.like(root.get("labels").as(String.class), "%" + label + "%");
                Predicate predicate1 = criteriaBuilder.equal(root.get("state").as(String.class), Constrants.Article.STATE_PUBLISH);
                Predicate predicate2 = criteriaBuilder.equal(root.get("state").as(String.class), Constrants.Article.STATE_TOP);
                Predicate or = criteriaBuilder.or(predicate1, predicate2);

                return criteriaBuilder.and(or, predicate);
            }
        }, pageable);
        return ResponseResult.SUCCESS("Success").setData(articles);
    }

    @Override
    public ResponseResult ListLabels(int size) {
        if (size < Constrants.Page.MIN_SIZE) {
            size = Constrants.Page.MIN_SIZE;
        }
        Pageable pageable = PageRequest.of(0, size, Sort.by("count").descending());
        Page<Labels> labelsPage = labelDAO.findAll(pageable);
        return ResponseResult.SUCCESS("Success").setData(labelsPage);
    }
}
