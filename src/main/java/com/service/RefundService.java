package com.service;

import com.bean.Refund;
import com.bean.extend.RefundExtend;
import com.bean.extend.UserExtend;

import java.util.Date;
import java.util.List;

public interface RefundService {
    void saveOrUpdate(Refund refund);

    List<RefundExtend> findRefundLike(Integer merchant,
                                      String status,
                                      Integer id,
                                      String consignee,
                                      String telephone,
                                      Date date);

    List<RefundExtend> findByUserId(Integer userId);

    UserExtend refund(Integer orderId);
}
