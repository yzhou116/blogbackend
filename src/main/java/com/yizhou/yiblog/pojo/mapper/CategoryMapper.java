package com.yizhou.yiblog.pojo.mapper;

import com.yizhou.yiblog.pojo.Category;

import java.util.List;

public interface CategoryMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_categories
     *
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_categories
     *
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    int insert(Category record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_categories
     *
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    Category selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_categories
     *
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    List<Category> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_categories
     *
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    int updateByPrimaryKey(Category record);
}