package com.service.impl;

import com.bean.Express;
import com.bean.extend.ExpressExtend;
import com.dao.ExpressMapper;
import com.dao.extend.ExpressExtendMapper;
import com.service.ExpressService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ExpressServiceImpl implements ExpressService {
    @Resource
    ExpressExtendMapper expressExtendMapper;
    @Resource
    ExpressMapper expressMapper;

    @Override
    public List<ExpressExtend> findAllByMerchant(Integer merchant) {
        List<ExpressExtend> list = expressExtendMapper.findAllByMerchant(merchant);
        return list;
    }

    @Override
    public void save(Express express) {
        expressMapper.insert(express);
    }
}
