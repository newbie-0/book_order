package com.dao.extend;

import com.bean.extend.RefundExtend;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface RefundExtendMapper {
    List<RefundExtend> findRefundLike(@Param("merchant") Integer merchant,
                                      @Param("id") Integer id,
                                      @Param("status") String status,
                                      @Param("consignee") String consignee,
                                      @Param("telephone") String telephone,
                                      @Param("date") Date date);

    List<RefundExtend> findByUserId(Integer userId);
}
