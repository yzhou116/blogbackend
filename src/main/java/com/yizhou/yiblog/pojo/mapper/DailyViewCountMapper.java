package com.yizhou.yiblog.pojo.mapper;

import com.yizhou.yiblog.pojo.DailyViewCount;

import java.util.List;

public interface DailyViewCountMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_daily_view_count
     *
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_daily_view_count
     *
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    int insert(DailyViewCount record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_daily_view_count
     *
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    DailyViewCount selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_daily_view_count
     *
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    List<DailyViewCount> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_daily_view_count
     *
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    int updateByPrimaryKey(DailyViewCount record);
}