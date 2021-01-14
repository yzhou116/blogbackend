package com.yizhou.yiblog.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "tb_friends")
public class Friends {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_friends.id
     *
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    @Id
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_friends.name
     *
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    @Column(name = "name")
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_friends.logo
     *
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    @Column(name = "logo")
    private String logo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_friends.url
     *
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    @Column(name = "url")
    private String url;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_friends.order
     *
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    @Column(name = "forder")
    private Integer forder;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_friends.state
     *
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    @Column(name = "state")
    private String state;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_friends.create_time
     *
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_friends.update_time
     *
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_friends.id
     *
     * @return the value of tb_friends.id
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_friends.id
     *
     * @param id the value for tb_friends.id
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_friends.name
     *
     * @return the value of tb_friends.name
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_friends.name
     *
     * @param name the value for tb_friends.name
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_friends.logo
     *
     * @return the value of tb_friends.logo
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    public String getLogo() {
        return logo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_friends.logo
     *
     * @param logo the value for tb_friends.logo
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    public void setLogo(String logo) {
        this.logo = logo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_friends.url
     *
     * @return the value of tb_friends.url
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    public String getUrl() {
        return url;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_friends.url
     *
     * @param url the value for tb_friends.url
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_friends.order
     *
     * @return the value of tb_friends.order
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    public Integer getFOrder() {
        return forder;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_friends.order
     *
     * @param order the value for tb_friends.order
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    public void setFOrder(Integer order) {
        this.forder = order;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_friends.state
     *
     * @return the value of tb_friends.state
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    public String getState() {
        return state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_friends.state
     *
     * @param state the value for tb_friends.state
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_friends.create_time
     *
     * @return the value of tb_friends.create_time
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_friends.create_time
     *
     * @param createTime the value for tb_friends.create_time
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_friends.update_time
     *
     * @return the value of tb_friends.update_time
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_friends.update_time
     *
     * @param updateTime the value for tb_friends.update_time
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}