package com.vivo.soft.excel.springexceldemo.service;

import com.vivo.soft.excel.springexceldemo.dto.AssociationKeyDto;
import com.vivo.soft.excel.springexceldemo.entity.AssociationKey;
import com.vivo.soft.excel.springexceldemo.query.AssociationKeyQuery;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author 张贵东
 * @Company TODO
 * @date 2018-11-17.
 * @Time 14:18
 * @Description TODO
 * @Version 2.0.0
 */
public interface AssociationKeyService {
    Page<AssociationKey> findAssociationKeyNoCriteria(Integer page, Integer size);
    Page<AssociationKey> findAssociationKeyCriteria(Integer page, Integer size, AssociationKeyQuery associationKeyQuery);
    List<AssociationKey> findAll();
    List<AssociationKeyDto> findDtoAll();
    AssociationKey findById(Long id);

    List<AssociationKey> findByIds(List<Long> keyIds);
}
