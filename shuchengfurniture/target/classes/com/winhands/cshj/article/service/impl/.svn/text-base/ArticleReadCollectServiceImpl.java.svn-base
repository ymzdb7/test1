package com.winhands.cshj.article.service.impl;
  

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.winhands.cshj.article.entity.ArticleReadCollect;
import com.winhands.cshj.article.repository.ArticleReadCollectDao;
import com.winhands.cshj.article.repository.MBArticleReadCollectDao;
import com.winhands.cshj.article.service.ArticleReadCollectService;
@Service
@Transactional(readOnly=false)
public class ArticleReadCollectServiceImpl implements ArticleReadCollectService {
	private final Logger logger = LoggerFactory.getLogger(ArticleReadCollectServiceImpl.class);
	@Autowired
    private ArticleReadCollectDao articleReadCollectDao;
	@Autowired
    private MBArticleReadCollectDao mBArticleReadCollectDao;
	@Override
	public ArticleReadCollect save(ArticleReadCollect articleReadCollect) {
		return articleReadCollectDao.save(articleReadCollect);
	}
	@Override
	public List<ArticleReadCollect> queryStatueByType(
			Map<String, Object> conditions) {
		return mBArticleReadCollectDao.queryStatueByType(conditions);
	}
	@Override
	public void delReadByArticleId(String file_id) {
		mBArticleReadCollectDao.delReadByArticleId(file_id);
	}
	
}
