package com.dao.extend;

import com.bean.extend.OrderExtend;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface OrderExtendMapper {
    List<OrderExtend> findByMerchant(Integer id);

    List<OrderExtend> findByUserIdAndStatus(@Param("userId") Integer userId,
                                            @Param("status") String status);

    List<OrderExtend> findOrderLike(@Param("userId") Integer userId,
                                    @Param("id") Integer id,
                                    @Param("consignee") String consignee,
                                    @Param("date") Date date);

    OrderExtend selectById(Integer id);
}
