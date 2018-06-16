package com.winhands.cshj.article.repository;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.winhands.base.service.MyBatisRepository;
import com.winhands.cshj.article.entity.Directory;

/**
 * 日志数据库操作层 基于mybais
 * @author yuanl
 *
 */  
@Component
public interface MBDirectoryDao extends MyBatisRepository{
	public List<Directory> findDirListByPId(Map<String,Object> conditions);
	public List<Directory> findDirListByPIdWeb(Map<String,Object> conditions);
	public void delDirectoryByDirId(String dir_id); 
}
