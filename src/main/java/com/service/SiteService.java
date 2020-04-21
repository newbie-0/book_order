package com.service;

import com.bean.Site;

import java.util.List;

public interface SiteService {
    List<Site> findAll();

    void save(Site site) throws Exception;

    void deleteById(Integer id);
}
