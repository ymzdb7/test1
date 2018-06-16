package com.winhands.ncjc.push.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.winhands.ncjc.push.entity.PushUserDevice;



/**
 * 日志数据库操作层 基于jpa默认使用此
 * @author yuanl
 *
 */  

public interface PushDao extends PagingAndSortingRepository<PushUserDevice, String>,JpaSpecificationExecutor<PushUserDevice>{
    
}
