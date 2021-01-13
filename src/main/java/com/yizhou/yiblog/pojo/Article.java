package com.yizhou.yiblog.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "tb_article")
public class Article {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_article.id
     *
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    @Id
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_article.title
     *
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    @Column(name = "title")
    private String title;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_article.user_id
     *
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_article.user_avatar
     *
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    @Column(name = "user_avatar")
    private String userAvatar;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_article.user_name
     *
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_article.category_id
     *
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    @Column(name = "category_id")
    private String categoryId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_article.type
     *
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    @Column(name = "type")
    private String type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_article.state
     *
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    @Column(name = "state")
    private String state;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_article.labels
     *
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    @Column(name = "labels")
    private String labels;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_article.view_count
     *
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    @Column(name = "view_count")
    private Integer viewCount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_article.create_time
     *
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_article.update_time
     *
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_article.content
     *
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    @Column(name = "content")
    private String content;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_article.summary
     *
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    @Column(name = "summary")
    private String summary;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_article.id
     *
     * @return the value of tb_article.id
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_article.id
     *
     * @param id the value for tb_article.id
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_article.title
     *
     * @return the value of tb_article.title
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_article.title
     *
     * @param title the value for tb_article.title
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_article.user_id
     *
     * @return the value of tb_article.user_id
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    public String getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_article.user_id
     *
     * @param userId the value for tb_article.user_id
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_article.user_avatar
     *
     * @return the value of tb_article.user_avatar
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    public String getUserAvatar() {
        return userAvatar;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_article.user_avatar
     *
     * @param userAvatar the value for tb_article.user_avatar
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_article.user_name
     *
     * @return the value of tb_article.user_name
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    public String getUserName() {
        return userName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_article.user_name
     *
     * @param userName the value for tb_article.user_name
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_article.category_id
     *
     * @return the value of tb_article.category_id
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    public String getCategoryId() {
        return categoryId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_article.category_id
     *
     * @param categoryId the value for tb_article.category_id
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_article.type
     *
     * @return the value of tb_article.type
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_article.type
     *
     * @param type the value for tb_article.type
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_article.state
     *
     * @return the value of tb_article.state
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    public String getState() {
        return state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_article.state
     *
     * @param state the value for tb_article.state
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_article.labels
     *
     * @return the value of tb_article.labels
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    public String getLabels() {
        return labels;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_article.labels
     *
     * @param labels the value for tb_article.labels
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    public void setLabels(String labels) {
        this.labels = labels;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_article.view_count
     *
     * @return the value of tb_article.view_count
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    public Integer getViewCount() {
        return viewCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_article.view_count
     *
     * @param viewCount the value for tb_article.view_count
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_article.create_time
     *
     * @return the value of tb_article.create_time
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_article.create_time
     *
     * @param createTime the value for tb_article.create_time
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_article.update_time
     *
     * @return the value of tb_article.update_time
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_article.update_time
     *
     * @param updateTime the value for tb_article.update_time
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_article.content
     *
     * @return the value of tb_article.content
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_article.content
     *
     * @param content the value for tb_article.content
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_article.summary
     *
     * @return the value of tb_article.summary
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    public String getSummary() {
        return summary;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_article.summary
     *
     * @param summary the value for tb_article.summary
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }
}