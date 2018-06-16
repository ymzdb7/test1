package com.winhands.cshj.article.repository;



import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.winhands.base.service.MyBatisRepository;
import com.winhands.cshj.article.entity.ArticleReadCollect;

/**
 * 日志数据库操作层 基于mybais
 * @author yuanl
 *
 */  
@Component
public interface MBArticleReadCollectDao extends MyBatisRepository{
	public List<ArticleReadCollect>queryStatueByType(Map<String,Object> conditions);
	public void  delReadByArticleId(String file_id);
}
