package com.yizhou.yiblog.pojo.mapper;

import com.yizhou.yiblog.pojo.Looper;

import java.util.List;

public interface LooperMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_looper
     *
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_looper
     *
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    int insert(Looper record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_looper
     *
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    Looper selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_looper
     *
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    List<Looper> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_looper
     *
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    int updateByPrimaryKey(Looper record);
}