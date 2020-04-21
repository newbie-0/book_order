package com.dao.extend;

import com.bean.Express;
import com.bean.extend.ExpressExtend;

import java.util.List;

public interface ExpressExtendMapper {
    List<ExpressExtend> findAllByMerchant(Integer merchant);

    Express findByOrderId(Integer id);
}
