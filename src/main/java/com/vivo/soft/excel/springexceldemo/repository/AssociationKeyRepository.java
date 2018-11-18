package com.vivo.soft.excel.springexceldemo.repository;

import com.vivo.soft.excel.springexceldemo.entity.AssociationKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author 张贵东
 * @Company TODO
 * @date 2018-11-17.
 * @Time 14:15
 * @Description TODO
 * @Version 2.0.0
 */
@Repository
public interface AssociationKeyRepository extends JpaRepository<AssociationKey,Long>,JpaSpecificationExecutor<AssociationKey> {
}
