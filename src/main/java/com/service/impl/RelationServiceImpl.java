package com.service.impl;

import com.bean.Relation;
import com.bean.RelationExample;
import com.dao.RelationMapper;
import com.service.RelationService;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class RelationServiceImpl implements RelationService {
    @Resource
    private RelationMapper relationMapper;

    @Override
    public void save(Integer bookId, Integer[] relationId) {
        RelationExample relationExample = new RelationExample();
        relationExample.createCriteria().andBookIdEqualTo(bookId);
        List<Relation> list = relationMapper.selectByExample(relationExample);
        List<Integer> newIds = Arrays.asList(relationId);
        Map<Integer, Integer> map = new HashMap<>();
        for (Relation relation : list) {
            map.put(relation.getId(), relation.getRelationId());
        }
        Set<Integer> set = map.keySet();
        for (Integer id : set) {
            if (!newIds.contains(map.get(id))) {
                relationMapper.deleteByPrimaryKey(id);
            }
        }
        Collection<Integer> oldIds = map.values();
        for (Integer id : newIds) {
            if (!oldIds.contains(id)) {
                Relation relation = new Relation();
                relation.setBookId(bookId);
                relation.setRelationId(id);
                relationMapper.insert(relation);
            }
        }
    }
}
