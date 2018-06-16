package com.winhands.cshj.article.service.impl;
  

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.winhands.cshj.article.entity.Article;
import com.winhands.cshj.article.entity.SubjectMag;
import com.winhands.cshj.article.entity.SubjectResult;
import com.winhands.cshj.article.repository.ArticleDao;
import com.winhands.cshj.article.repository.MBArticleDao;
import com.winhands.cshj.article.service.ArticleService;
@Service
@Transactional(readOnly=false)
public class ArticleServiceImpl implements ArticleService {
	private final Logger logger = LoggerFactory.getLogger(ArticleServiceImpl.class);
	@Autowired
    private ArticleDao articleDao;
	@Autowired
    private MBArticleDao mBArticleDao;
	@Override
	public Article save(Article article) {
		return articleDao.save(article);
	}
	@Override
	public List<Article> queryArticleList(Map<String, Object> conditions) {
		return mBArticleDao.queryArticleList(conditions);
	}
	@Override
	public Article findById(String id) {
		return articleDao.findOne(id);
	}
	@Override
	public List<Article> queryArticleListByMemId(Map<String, Object> conditions) {
		return mBArticleDao.queryArticleListByMemId(conditions);
	}
	@Override
	public void delArticleById(String id) {
		mBArticleDao.delArticleById(id);
	}
	@Override
	public List<Article> queryCollectList(Map<String, Object> conditions) {
		return mBArticleDao.queryCollectList(conditions);
	}
	@Override
	public int updateOrderSc(Article article) throws Exception {
		return mBArticleDao.updateOrderSc(article);
	}
	@Override
	public int updateOrderScqx(Article article) throws Exception {
		return mBArticleDao.updateOrderScqx(article);
	}
	@Override
	public int updateOrderArticle(Article article) throws Exception {
		return mBArticleDao.updateOrderArticle(article);
	}
	@Override
	public int updateOrderArticleQx(Article article) throws Exception {
		return mBArticleDao.updateOrderArticleQx(article);
	}
	@Override
	public List<Article> queryArticleListWeb(Map<String, Object> conditions) {
		return mBArticleDao.queryArticleListWeb(conditions);
	}
	@Override
	public List<Article> queryNotifyArticleList(Map<String, Object> conditions) {
		return mBArticleDao.queryNotifyArticleList(conditions);
	}
	@Override
	public int updateArticleNotify(Article article) throws Exception {
		return mBArticleDao.updateArticleNotify(article);
	}
	@Override
	public List<SubjectMag> querySubjectMagByArticleId(String article_id) {
		return mBArticleDao.querySubjectMagByArticleId(article_id);
	}
	@Override
	public int saveSubjectMag(SubjectMag subjectMag) throws Exception {
		return mBArticleDao.saveSubjectMag(subjectMag);
	}
	@Override
	public int saveSubjectResult(SubjectResult subjectResult) throws Exception {
		return mBArticleDao.saveSubjectResult(subjectResult);
	}
	@Override
	public List<SubjectResult> querySubjectResult(SubjectResult SubjectResult) {
		return mBArticleDao.querySubjectResult(SubjectResult);
	}
	@Override
	public List<Article> queryReadList(Map<String, Object> conditions) {
		return mBArticleDao.queryReadList(conditions);
	}
	@Override
	public int updateOrderRead(Article article) throws Exception {
		return mBArticleDao.updateOrderRead(article);
	}
	@Override
	public int updateOrderReadqx(Article article) throws Exception {
		return mBArticleDao.updateOrderReadqx(article);
	}
	@Override
	public int deleteSubjectMagById(String subject_id) throws Exception {
		return mBArticleDao.deleteSubjectMagById(subject_id);
	}
	@Override
	public Article queryNumById(String id) {
		return mBArticleDao.queryNumById(id);
	}
	@Override
	public SubjectMag querySubjectById(String id) throws Exception {
		return mBArticleDao.querySubjectById(id);
	}
	@Override
	public int updateSubjectMag(SubjectMag subjectMag) throws Exception {
		return mBArticleDao.updateSubjectMag(subjectMag);
	} 
	 
	
}
