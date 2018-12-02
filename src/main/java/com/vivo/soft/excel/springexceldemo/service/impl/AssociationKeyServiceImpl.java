package com.vivo.soft.excel.springexceldemo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.vivo.soft.excel.springexceldemo.dto.AssociationKeyDto;
import com.vivo.soft.excel.springexceldemo.entity.AssociationKey;
import com.vivo.soft.excel.springexceldemo.query.AssociationKeyQuery;
import com.vivo.soft.excel.springexceldemo.repository.AssociationKeyRepository;
import com.vivo.soft.excel.springexceldemo.service.AssociationKeyService;
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
import java.util.Optional;

/**
 * @author 张贵东
 * @Company TODO
 * @date 2018-11-17.
 * @Time 14:22
 * @Description TODO
 * @Version 2.0.0
 */
@Service
public class AssociationKeyServiceImpl implements AssociationKeyService {

    @Resource
    private AssociationKeyRepository associationKeyRepository;

    @Override
    public Page<AssociationKey> findAssociationKeyNoCriteria(Integer page, Integer size) {
        Pageable pageable = new PageRequest(page, size, Sort.Direction.ASC, "updateTime");
        return associationKeyRepository.findAll(pageable);
    }

    @Override
    public Page<AssociationKey> findAssociationKeyCriteria(Integer page, Integer size, final AssociationKeyQuery associationKeyQuery) {
        Pageable pageable = new PageRequest(page, size, Sort.Direction.ASC, "updateTime");
        Page<AssociationKey> associationKeyPage = associationKeyRepository.findAll(new Specification<AssociationKey>(){
            @Override
            public Predicate toPredicate(Root<AssociationKey> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<Predicate>();
                if(null!=associationKeyQuery.getKeyName()&&!"".equals(associationKeyQuery.getKeyName())){
                    list.add(criteriaBuilder.equal(root.get("keyName").as(String.class), associationKeyQuery.getKeyName()));
                }
                if(null!=associationKeyQuery.getGameId()&&0L!=associationKeyQuery.getGameId()){
                    list.add(criteriaBuilder.equal(root.get("isbn").as(Long.class), associationKeyQuery.getGameId()));
                }
                Predicate[] p = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(p));
            }
        },pageable);
        return associationKeyPage;
    }

    @Override
    public List<AssociationKey> findAll() {
        return associationKeyRepository.findAll();
    }

    @Override
    public List<AssociationKeyDto> findDtoAll() {
        List<AssociationKey> associationKeys = findAll();
        List<AssociationKeyDto> dtos = null;
        if (associationKeys!=null&&associationKeys.size()>0){
            String json = JSONObject.toJSONString(associationKeys);
            dtos = JSONObject.parseArray(json,AssociationKeyDto.class);
        }
        return dtos;
    }

    @Override
    public AssociationKey findById(Long id) {
        Optional<AssociationKey> optional = associationKeyRepository.findById(id);
        return optional.get();
    }

    @Override
    public List<AssociationKey> findByIds(List<Long> keyIds) {
        List<AssociationKey> optional = associationKeyRepository.findAllById(keyIds);
        return optional;
    }
}
