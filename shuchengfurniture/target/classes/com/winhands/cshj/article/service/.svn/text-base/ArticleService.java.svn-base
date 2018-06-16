package com.winhands.cshj.article.service;

import java.util.List;
import java.util.Map;

import com.winhands.cshj.article.entity.Article;
import com.winhands.cshj.article.entity.SubjectMag;
import com.winhands.cshj.article.entity.SubjectResult;


public interface ArticleService {
	//文章列表
	public List<Article> queryNotifyArticleList(Map<String,Object> conditions);
	
	//保存文章
		public Article save(Article article);
	//文章列表
		public List<Article>queryArticleList(Map<String,Object> conditions);
		public List<Article>queryArticleListWeb(Map<String,Object> conditions);
	//根据id获取文章对象
		public Article findById(String id); 
	//根据会员id获取会员文章收藏列表
		public List<Article>queryArticleListByMemId(Map<String,Object> conditions);
	//根据文章id删除信息
		public void delArticleById(String id);
	//收藏榜列表
		public List<Article>queryCollectList(Map<String,Object> conditions);
	//收藏榜置顶
		public int updateOrderSc(Article article) throws Exception;
	//收藏榜置顶  取消
		public int updateOrderScqx(Article article) throws Exception;
	//最新，热门，精选置顶
		public int updateOrderArticle(Article article) throws Exception;
	//最新，热门，精选置顶  取消
		public int updateOrderArticleQx(Article article) throws Exception;
	//更新待推送
	   public int updateArticleNotify(Article article) throws Exception;
	//获取文章相关的题库
	   public List<SubjectMag>querySubjectMagByArticleId(String article_id);
	//根据题目id获取题目
	   SubjectMag querySubjectById(String id) throws Exception;
	//新增题目
	   int saveSubjectMag(SubjectMag subjectMag) throws Exception;
	//修改题目
	   int updateSubjectMag(SubjectMag subjectMag) throws Exception;
	//删除题目
	   int deleteSubjectMagById(String subject_id) throws Exception;
	//保存学生答题结果
	   int saveSubjectResult(SubjectResult subjectResult) throws Exception;
	//查询学生答题结果
	   public List<SubjectResult>querySubjectResult(SubjectResult SubjectResult);
	//阅读排行榜列表
	   public List<Article>queryReadList(Map<String,Object> conditions);
	//阅读排行榜置顶
	   public int updateOrderRead(Article article) throws Exception;
	//阅读排行榜置顶  取消
	   public int updateOrderReadqx(Article article) throws Exception;
	//根据文章id获取该文章阅读数，收藏数，点赞数
	   public Article queryNumById(String id); 
}
