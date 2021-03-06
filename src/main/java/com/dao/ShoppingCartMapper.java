package com.dao;

import com.bean.ShoppingCart;
import com.bean.ShoppingCartExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ShoppingCartMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shopping_cart
     *
     * @mbg.generated Wed Apr 01 10:33:55 CST 2020
     */
    long countByExample(ShoppingCartExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shopping_cart
     *
     * @mbg.generated Wed Apr 01 10:33:55 CST 2020
     */
    int deleteByExample(ShoppingCartExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shopping_cart
     *
     * @mbg.generated Wed Apr 01 10:33:55 CST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shopping_cart
     *
     * @mbg.generated Wed Apr 01 10:33:55 CST 2020
     */
    int insert(ShoppingCart record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shopping_cart
     *
     * @mbg.generated Wed Apr 01 10:33:55 CST 2020
     */
    int insertSelective(ShoppingCart record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shopping_cart
     *
     * @mbg.generated Wed Apr 01 10:33:55 CST 2020
     */
    List<ShoppingCart> selectByExample(ShoppingCartExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shopping_cart
     *
     * @mbg.generated Wed Apr 01 10:33:55 CST 2020
     */
    ShoppingCart selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shopping_cart
     *
     * @mbg.generated Wed Apr 01 10:33:55 CST 2020
     */
    int updateByExampleSelective(@Param("record") ShoppingCart record, @Param("example") ShoppingCartExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shopping_cart
     *
     * @mbg.generated Wed Apr 01 10:33:55 CST 2020
     */
    int updateByExample(@Param("record") ShoppingCart record, @Param("example") ShoppingCartExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shopping_cart
     *
     * @mbg.generated Wed Apr 01 10:33:55 CST 2020
     */
    int updateByPrimaryKeySelective(ShoppingCart record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shopping_cart
     *
     * @mbg.generated Wed Apr 01 10:33:55 CST 2020
     */
    int updateByPrimaryKey(ShoppingCart record);
}