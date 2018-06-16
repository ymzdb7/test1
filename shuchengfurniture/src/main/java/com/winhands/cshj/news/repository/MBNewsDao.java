package com.winhands.cshj.news.repository;




import java.util.List;

import org.springframework.stereotype.Component;

import com.winhands.base.service.MyBatisRepository;
import com.winhands.cshj.news.entity.News;

/**
 * 积分管理数据库操作层 基于mybais
 * @author yuanl
 *
 */  
@Component
public interface MBNewsDao extends MyBatisRepository{
	 List<News> queryNewsList(News news) throws Exception;
	 List<News> queryCarouselList(News news) throws Exception;
	 int saveNews(News news) throws Exception;
	 int updateNews(News news) throws Exception;
	 int deleteNewsById(String id) throws Exception;
	 News queryCarouselById(String id) throws Exception;
}
