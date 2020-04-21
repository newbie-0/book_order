package com.bean.extend;

import com.bean.Express;

public class ExpressExtend extends Express {
    private OrderExtend orderExtend;

    public OrderExtend getOrderExtend() {
        return orderExtend;
    }

    public void setOrderExtend(OrderExtend orderExtend) {
        this.orderExtend = orderExtend;
    }
}
