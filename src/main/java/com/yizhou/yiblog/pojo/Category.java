package com.yizhou.yiblog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "tb_categories")
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_categories.id
     *
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    @Id
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_categories.name
     *
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    @Column(name = "name")
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_categories.pinyin
     *
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    @Column(name = "pinyin")
    private String pinyin;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_categories.order
     *
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    @Column(name = "`order`")
    private Integer order;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_categories.status
     *
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    @Column(name = "status")
    private String status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_categories.create_time
     *
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_categories.update_time
     *
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_categories.description
     *
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    @Column(name = "description")
    private String description;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_categories.id
     *
     * @return the value of tb_categories.id
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_categories.id
     *
     * @param id the value for tb_categories.id
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_categories.name
     *
     * @return the value of tb_categories.name
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_categories.name
     *
     * @param name the value for tb_categories.name
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_categories.pinyin
     *
     * @return the value of tb_categories.pinyin
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    public String getPinyin() {
        return pinyin;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_categories.pinyin
     *
     * @param pinyin the value for tb_categories.pinyin
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_categories.order
     *
     * @return the value of tb_categories.order
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    public Integer getOrder() {
        return order;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_categories.order
     *
     * @param order the value for tb_categories.order
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    public void setOrder(Integer order) {
        this.order = order;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_categories.status
     *
     * @return the value of tb_categories.status
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_categories.status
     *
     * @param status the value for tb_categories.status
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_categories.create_time
     *
     * @return the value of tb_categories.create_time
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_categories.create_time
     *
     * @param createTime the value for tb_categories.create_time
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_categories.update_time
     *
     * @return the value of tb_categories.update_time
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_categories.update_time
     *
     * @param updateTime the value for tb_categories.update_time
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_categories.description
     *
     * @return the value of tb_categories.description
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_categories.description
     *
     * @param description the value for tb_categories.description
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    public void setDescription(String description) {
        this.description = description;
    }
}