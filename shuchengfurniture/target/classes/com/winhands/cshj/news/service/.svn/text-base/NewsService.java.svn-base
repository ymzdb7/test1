package com.winhands.cshj.news.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.winhands.cshj.news.entity.News;


@Component
public interface NewsService {
	 List<News> queryNewsList(News news) throws Exception;
	 List<News> queryCarouselList(News news) throws Exception;
	 News queryCarouselById(String id) throws Exception;
	 int saveNews(News news) throws Exception;
	 int updateNews(News news) throws Exception;
	 int deleteNewsById(String id) throws Exception;
}
