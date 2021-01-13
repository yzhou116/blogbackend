package com.yizhou.yiblog.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "tb_settings")
public class Settings {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_settings.id
     *
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    @Id
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_settings.key
     *
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    @Column(name = "`key`")
    private String key;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_settings.value
     *
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    @Column(name = "`value`")
    private String value;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_settings.create_time
     *
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_settings.update_time
     *
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_settings.id
     *
     * @return the value of tb_settings.id
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_settings.id
     *
     * @param id the value for tb_settings.id
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_settings.key
     *
     * @return the value of tb_settings.key
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    public String getKey() {
        return key;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_settings.key
     *
     * @param key the value for tb_settings.key
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_settings.value
     *
     * @return the value of tb_settings.value
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    public String getValue() {
        return value;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_settings.value
     *
     * @param value the value for tb_settings.value
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_settings.create_time
     *
     * @return the value of tb_settings.create_time
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_settings.create_time
     *
     * @param createTime the value for tb_settings.create_time
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_settings.update_time
     *
     * @return the value of tb_settings.update_time
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_settings.update_time
     *
     * @param updateTime the value for tb_settings.update_time
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}