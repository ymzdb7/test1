package com.winhands.cshj.article.repository;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.winhands.base.service.MyBatisRepository;
import com.winhands.cshj.article.entity.FileReadLog;

/**
 * 日志数据库操作层 基于mybais
 * @author yuanl
 *
 */  
@Component
public interface MBFileReadLogDao extends MyBatisRepository{
	public List<FileReadLog>queryFileReadLogLimit(Map<String,Object> conditions);
	public List<FileReadLog>queryLogList(Map<String,Object> conditions);
}
