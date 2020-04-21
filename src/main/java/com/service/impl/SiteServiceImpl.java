package com.service.impl;

import com.bean.Site;
import com.bean.SiteExample;
import com.dao.SiteMapper;
import com.service.SiteService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SiteServiceImpl implements SiteService {
    @Resource
    private SiteMapper siteMapper;

    @Override
    public List<Site> findAll() {
        SiteExample siteExample = new SiteExample();
        List<Site> list = siteMapper.selectByExample(siteExample);
        return list;
    }

    @Override
    public void save(Site site) throws Exception {
        SiteExample siteExample = new SiteExample();
        siteExample.createCriteria().andNameEqualTo(site.getName());
        List<Site> list = siteMapper.selectByExample(siteExample);
        if (list.size() > 0)
            throw new Exception("发货地点已存在");
        siteMapper.insert(site);
    }

    @Override
    public void deleteById(Integer id) {
        siteMapper.deleteByPrimaryKey(id);
    }
}
