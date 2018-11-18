package com.vivo.soft.excel.springexceldemo.service.impl;

import com.vivo.soft.excel.springexceldemo.entity.AssociationKeyGame;
import com.vivo.soft.excel.springexceldemo.query.AssociationKeyGameQuery;
import com.vivo.soft.excel.springexceldemo.repository.AssociationKeyGameRepository;
import com.vivo.soft.excel.springexceldemo.service.AssociationKeyGameService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 张贵东
 * @Company TODO
 * @date 2018-11-17.
 * @Time 21:44
 * @Description TODO
 * @Version 2.0.0
 */
@Service
public class AssociationKeyGameServiceImpl implements AssociationKeyGameService {

    @Resource
    private AssociationKeyGameRepository associationKeyGameRepository;

    @Override
    public Page<AssociationKeyGame> findAssociationKeyGameNoCriteria(Integer page, Integer size) {
        Pageable pageable = new PageRequest(page, size, Sort.Direction.ASC, "updateTime");
        return associationKeyGameRepository.findAll(pageable);
    }

    @Override
    public Page<AssociationKeyGame> findAssociationKeyGameCriteria(Integer page, Integer size, final AssociationKeyGameQuery associationKeyQuery) {
        Pageable pageable = new PageRequest(page, size, Sort.Direction.ASC, "updateTime");
        Page<AssociationKeyGame> associationKeyGamePage = associationKeyGameRepository.findAll(new Specification<AssociationKeyGame>(){
            @Override
            public Predicate toPredicate(Root<AssociationKeyGame> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<Predicate>();
                if(null!=associationKeyQuery.getGameName()&&!"".equals(associationKeyQuery.getGameName())){
                    list.add(criteriaBuilder.equal(root.get("gameName").as(String.class), associationKeyQuery.getGameName()));
                }
                if(null!=associationKeyQuery.getKeyId()&&0!=associationKeyQuery.getKeyId()){
                    list.add(criteriaBuilder.equal(root.get("keyId").as(Integer.class), associationKeyQuery.getKeyId()));
                }
                Predicate[] p = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(p));
            }
        },pageable);
        return associationKeyGamePage;
    }
}
