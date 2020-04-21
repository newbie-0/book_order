package com.bean.extend;

import com.bean.Refund;

public class RefundExtend extends Refund {
    private OrderExtend orderExtend;

    public OrderExtend getOrderExtend() {
        return orderExtend;
    }

    public void setOrderExtend(OrderExtend orderExtend) {
        this.orderExtend = orderExtend;
    }
}
