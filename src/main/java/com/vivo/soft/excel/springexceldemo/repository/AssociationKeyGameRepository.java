package com.vivo.soft.excel.springexceldemo.repository;

import com.vivo.soft.excel.springexceldemo.entity.AssociationKeyGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author 张贵东
 * @Company TODO
 * @date 2018-11-17.
 * @Time 14:17
 * @Description TODO
 * @Version 2.0.0
 */
public interface AssociationKeyGameRepository extends JpaRepository<AssociationKeyGame,Long>,JpaSpecificationExecutor<AssociationKeyGame> {
}
