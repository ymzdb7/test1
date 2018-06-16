package com.winhands.cshj.news.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winhands.cshj.news.entity.News;
import com.winhands.cshj.news.repository.MBNewsDao;
import com.winhands.cshj.news.service.NewsService;


@Service
public class NewsServiceImpl implements NewsService{
	@Autowired
    private MBNewsDao mbNewsDao;
	@Override
	public List<News> queryNewsList(News news) throws Exception {
		return mbNewsDao.queryNewsList(news);
	}

	@Override
	public int saveNews(News news) throws Exception {
		return mbNewsDao.saveNews(news);
	}

	@Override
	public int deleteNewsById(String id) throws Exception {
		return mbNewsDao.deleteNewsById(id);
	}

	@Override
	public List<News> queryCarouselList(News news) throws Exception {
		return mbNewsDao.queryCarouselList(news);
	}

	@Override
	public News queryCarouselById(String id) throws Exception {
		return mbNewsDao.queryCarouselById(id);
	}

	@Override
	public int updateNews(News news) throws Exception {
		return mbNewsDao.updateNews(news);
	}
  
}
