package com.dao;

import com.bean.Express;
import com.bean.ExpressExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExpressMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table express
     *
     * @mbg.generated Thu Apr 09 10:12:00 CST 2020
     */
    long countByExample(ExpressExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table express
     *
     * @mbg.generated Thu Apr 09 10:12:00 CST 2020
     */
    int deleteByExample(ExpressExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table express
     *
     * @mbg.generated Thu Apr 09 10:12:00 CST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table express
     *
     * @mbg.generated Thu Apr 09 10:12:00 CST 2020
     */
    int insert(Express record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table express
     *
     * @mbg.generated Thu Apr 09 10:12:00 CST 2020
     */
    int insertSelective(Express record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table express
     *
     * @mbg.generated Thu Apr 09 10:12:00 CST 2020
     */
    List<Express> selectByExample(ExpressExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table express
     *
     * @mbg.generated Thu Apr 09 10:12:00 CST 2020
     */
    Express selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table express
     *
     * @mbg.generated Thu Apr 09 10:12:00 CST 2020
     */
    int updateByExampleSelective(@Param("record") Express record, @Param("example") ExpressExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table express
     *
     * @mbg.generated Thu Apr 09 10:12:00 CST 2020
     */
    int updateByExample(@Param("record") Express record, @Param("example") ExpressExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table express
     *
     * @mbg.generated Thu Apr 09 10:12:00 CST 2020
     */
    int updateByPrimaryKeySelective(Express record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table express
     *
     * @mbg.generated Thu Apr 09 10:12:00 CST 2020
     */
    int updateByPrimaryKey(Express record);
}