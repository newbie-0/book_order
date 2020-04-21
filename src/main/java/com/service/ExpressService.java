package com.service;

import com.bean.Express;
import com.bean.extend.ExpressExtend;

import java.util.List;

public interface ExpressService {
    List<ExpressExtend> findAllByMerchant(Integer merchant);

    void save(Express express);
}
