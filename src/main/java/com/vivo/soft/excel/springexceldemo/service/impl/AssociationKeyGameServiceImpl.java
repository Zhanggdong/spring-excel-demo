package com.vivo.soft.excel.springexceldemo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.vivo.soft.excel.springexceldemo.dto.AssociationGameDto;
import com.vivo.soft.excel.springexceldemo.dto.AssociationKeyDto;
import com.vivo.soft.excel.springexceldemo.entity.AssociationKey;
import com.vivo.soft.excel.springexceldemo.entity.AssociationKeyGame;
import com.vivo.soft.excel.springexceldemo.query.AssociationKeyGameQuery;
import com.vivo.soft.excel.springexceldemo.repository.AssociationKeyGameRepository;
import com.vivo.soft.excel.springexceldemo.service.AssociationKeyGameService;
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
    @Resource
    private AssociationKeyService associationKeyService;

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

    @Override
    public List<AssociationKeyGame> findAll() {
        return associationKeyGameRepository.findAll();
    }

    @Override
    public List<AssociationGameDto> findDtoAll() {
        List<AssociationKeyGame> associationKeyGames = findAll();
        List<AssociationGameDto> gameDtos = null;
        if (associationKeyGames!=null&&associationKeyGames.size()>0){
            gameDtos = new ArrayList<>();
            List<Long> keyIds = new ArrayList<>();
            for(AssociationKeyGame keyGame:associationKeyGames){
                if (keyIds.contains(keyGame.getKeyId())){
                    continue;
                }
                keyIds.add(keyGame.getKeyId());
            }
            List<AssociationKey> keyList = associationKeyService.findByIds(keyIds);
            for(AssociationKeyGame keyGame:associationKeyGames){
                String gameJson = JSONObject.toJSONString(keyGame);
                AssociationGameDto dto = JSONObject.parseObject(gameJson,AssociationGameDto.class);
                for (AssociationKey associationKey:keyList){
                    if (keyGame.getKeyId().toString().equals(associationKey.getId().toString())){
                        dto.setKeyName(associationKey.getKeyName());
                        dto.setFromDate(associationKey.getFromDate());
                        dto.setEndDate(associationKey.getEndDate());
                    }
                }
                gameDtos.add(dto);
            }

        }
        return gameDtos;
    }
}
