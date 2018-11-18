package com.vivo.soft.excel.springexceldemo.service;

import com.vivo.soft.excel.springexceldemo.entity.AssociationKeyGame;
import com.vivo.soft.excel.springexceldemo.query.AssociationKeyGameQuery;
import org.springframework.data.domain.Page;

/**
 * @author 张贵东
 * @Company TODO
 * @date 2018-11-17.
 * @Time 21:41
 * @Description TODO
 * @Version 2.0.0
 */
public interface AssociationKeyGameService {
    Page<AssociationKeyGame> findAssociationKeyGameNoCriteria(Integer page, Integer size);
    Page<AssociationKeyGame> findAssociationKeyGameCriteria(Integer page, Integer size, AssociationKeyGameQuery associationKeyQuery);
}
