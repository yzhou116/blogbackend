package com.yizhou.yiblog.pojo.mapper;

import com.yizhou.yiblog.pojo.Comment;

import java.util.List;

public interface CommentMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_comment
     *
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_comment
     *
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    int insert(Comment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_comment
     *
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    Comment selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_comment
     *
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    List<Comment> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_comment
     *
     * @mbg.generated Sat Jan 09 23:46:26 EST 2021
     */
    int updateByPrimaryKey(Comment record);
}