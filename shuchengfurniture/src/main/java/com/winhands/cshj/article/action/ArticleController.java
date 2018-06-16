package com.winhands.cshj.article.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.winhands.base.util.BaseConstant;
import com.winhands.base.util.BaseJson;
import com.winhands.base.util.DateUtil;
import com.winhands.base.util.StringUtil;
import com.winhands.base.util.ZipUtil;
import com.winhands.base.web.BaseController;
import com.winhands.cshj.article.entity.Article;
import com.winhands.cshj.article.entity.ArticleReadCollect;
import com.winhands.cshj.article.entity.FileReadLog;
import com.winhands.cshj.article.entity.SubjectMag;
import com.winhands.cshj.article.entity.SubjectResult;
import com.winhands.cshj.article.service.ArticleReadCollectService;
import com.winhands.cshj.article.service.ArticleService;
import com.winhands.cshj.article.service.DirectoryService;
import com.winhands.cshj.article.service.FileReadLogService;
import com.winhands.cshj.integration.entity.Integration;
import com.winhands.cshj.integration.entity.IntegrationMember;
import com.winhands.cshj.integration.service.IntegrationService;
import com.winhands.cshj.member.entity.Member;
import com.winhands.cshj.member.service.MemberService;
import com.winhands.ncjc.push.PushApi;
import com.winhands.ncjc.push.entity.PushMsg;
import com.winhands.ncjc.push.service.PushApiService;

@Controller
@RequestMapping("/article")
public class ArticleController extends BaseController {
	private final Logger logger = LoggerFactory.getLogger(ArticleController.class);
	@Autowired
	private ArticleService articleService;
	@Autowired
	private DirectoryService directoryService;
	@Autowired
	private FileReadLogService fileReadLogService;
	@Autowired
	private ArticleReadCollectService articleReadCollectService;
	@Autowired
	private PushApiService pushApiService;
	@Autowired
	private IntegrationService integrationService;
	@Autowired
	private MemberService memberService;

	// 获取最新、热门、精选文章列表页面
	@RequestMapping("/contentIndex")
	public ModelAndView contentIndex(String pageNo, String pageSize, String searchId, String searchName,
			String author_name, String key_words, String file_type, String query_date,String is_jx) {

		int pageSizeI = BaseConstant.pageSize;
		int pageNoI = 1;
		try {
			pageNoI = Integer.parseInt(pageNo);
			pageSizeI = Integer.parseInt(pageSize);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		Map<String, Object> conditions = new HashMap<String, Object>();
		if (!StringUtil.isNull(author_name)) {
			conditions.put("author_name", author_name);
		}
		if (!StringUtil.isNull(key_words)) {
			conditions.put("key_words", key_words);
		}
		if (!StringUtil.isNull(file_type)) {
			conditions.put("file_type", file_type);
		}
		if (!StringUtil.isNull(query_date)) {
			conditions.put("create_time2", query_date);
		}
		if (!StringUtil.isNull(is_jx)) {
			conditions.put("is_jx", is_jx);
		}
		conditions.put("order_new", "1");
		Page<Article> page2 = new Page<Article>();
		PageHelper.startPage(pageNoI, pageSizeI);
		page2 = (Page<Article>) articleService.queryArticleListWeb(conditions);
		String title = "";
		List articleList = page2.getResult();
		try {
			if (articleList != null && articleList.size() > 0) {
				for (int i = 0; i < articleList.size(); i++) {
					Article artTemp = (Article) articleList.get(i);
					if (artTemp.getTitle() != null && artTemp.getTitle().length() > 0) {
						title = (artTemp.getTitle().length() > 15) ? artTemp.getTitle().substring(0, 14) + "......"
								: artTemp.getTitle();
					}
					if ("1".equals(artTemp.getIs_online())) {
						Date nowDate = new Date();
						long nowLong = nowDate.getTime();
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
						Date onlinedate = sdf.parse(artTemp.getOnline_date() + ":00");
						long onlineLong = onlinedate.getTime();
						long dayNum = nowLong - onlineLong;
						if (dayNum > 0) {// 已上线

						} else {// 待发布
							artTemp.setIs_online("5");
						}
					}
					artTemp.setTitle(title);
					artTemp.setSpare_1(BaseConstant.fileUploadPathZip + artTemp.getId().substring(0, 6) + "/article/"
							+ artTemp.getId() + "/" + artTemp.getId() + ".html");
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		ModelAndView mv = new ModelAndView("forward:/view/articleFile/contentIndex.jsp?ac=500004");// redirect模式
		mv.addObject("pageList", page2.getResult());
		mv.addObject("pageBt", page2PageBt(page2));
		mv.addObject("searchId", searchId);
		mv.addObject("searchName", searchName);
		mv.addObject("file_type", file_type);
		return mv;
	}

	// 最新，热门，精选置顶功能
	@RequestMapping("/updateOrderArticle")
	public ModelAndView updateOrderArticle(String id, String order_type, String file_type) {
		Article tempArt = new Article();
		tempArt.setId(id);
		try {
			if (!StringUtil.isNull(order_type)) {
				if ("zx".equals(order_type)) {// 最新
					tempArt.setOrder_new(999);
				}
				if ("rm".equals(order_type)) {// 热门
					// tempArt.setOrder_hot(999);
				}
				if ("jx".equals(order_type)) {// 精选
					tempArt.setOrder_col(999);
				}
			}
			articleService.updateOrderArticle(tempArt);
			logger.info("置顶成功");
		} catch (Exception e) {
			logger.info("置顶失败");
			e.printStackTrace();
		}
		return contentIndex("1", "10", "", "", "", "", order_type, "","");
	}

	// 最新，热门，精选置顶功能
	@RequestMapping("/updateOrderArticleQx")
	public ModelAndView updateOrderArticleQx(String id, String order_type, String file_type) {
		Article tempArt = new Article();
		tempArt.setId(id);
		try {
			if (!StringUtil.isNull(order_type)) {
				if ("zx".equals(order_type)) {// 最新
					tempArt.setOrder_new(1);
				}
				if ("rm".equals(order_type)) {// 热门
					// tempArt.setOrder_hot(1);
				}
				if ("jx".equals(order_type)) {// 精选
					tempArt.setOrder_col(1);
				}
			}
			articleService.updateOrderArticleQx(tempArt);
			logger.info("取消置顶成功");
		} catch (Exception e) {
			logger.info("取消置顶失败");
			e.printStackTrace();
		}
		return contentIndex("1", "10", "", "", "", "", order_type, "","");
	}
	// 收藏榜置顶功能

	@RequestMapping("/upadteOrderSc")
	public ModelAndView upadteOrderSc(String id) {
		Article tempArt = new Article();
		tempArt.setId(id);
		try {
			articleService.updateOrderSc(tempArt);
			logger.info("置顶成功");
		} catch (Exception e) {
			logger.info("置顶失败");
			e.printStackTrace();
		}
		return collectList("1", "10", "", "", "", "", "", "");
	}

	// 收藏榜取消置顶功能
	@RequestMapping("/updateOrderScqx")
	public ModelAndView updateOrderScqx(String id) {
		Article tempArt = new Article();
		tempArt.setId(id);
		try {
			articleService.updateOrderScqx(tempArt);
			logger.info("取消置顶成功");
		} catch (Exception e) {
			logger.info("取消置顶失败");
			e.printStackTrace();
		}
		return collectList("1", "10", "", "", "", "", "", "");
	}
	
	// 获取收藏榜列表
	@RequestMapping("/collectList")
	public ModelAndView collectList(String pageNo, String pageSize, String searchId, String searchName,
			String author_name, String key_words, String file_type, String query_date) {
		int pageSizeI = BaseConstant.pageSize;
		int pageNoI = 1;
		try {
			pageNoI = Integer.parseInt(pageNo);
			pageSizeI = Integer.parseInt(pageSize);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		Map<String, Object> conditions = new HashMap<String, Object>();
		if (!StringUtil.isNull(author_name)) {
			conditions.put("author_name", author_name);
		}
		if (!StringUtil.isNull(key_words)) {
			conditions.put("key_words", key_words);
		}
		if (!StringUtil.isNull(file_type)) {
			conditions.put("file_type", file_type);
		}
		if (!StringUtil.isNull(query_date)) {
			conditions.put("create_time2", query_date);
		}
		Page<Article> page2 = new Page<Article>();
		PageHelper.startPage(pageNoI, pageSizeI);
		page2 = (Page<Article>) articleService.queryCollectList(conditions);
		String title = "";
		List articleList = page2.getResult();
		try {
			if (articleList != null && articleList.size() > 0) {
				for (int i = 0; i < articleList.size(); i++) {
					Article artTemp = (Article) articleList.get(i);
					if (artTemp.getTitle() != null && artTemp.getTitle().length() > 0) {
						title = (artTemp.getTitle().length() > 15) ? artTemp.getTitle().substring(0, 14) + "......"
								: artTemp.getTitle();
					}
					artTemp.setTitle(title);
					artTemp.setSpare_1(BaseConstant.fileUploadPathZip + artTemp.getId().substring(0, 6) + "/article/"
							+ artTemp.getId() + "/" + artTemp.getId() + ".html");
					if ("1".equals(artTemp.getIs_online())) {
						Date nowDate = new Date();
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
						Date onlinedate = sdf.parse(artTemp.getOnline_date());
						int dayNum = DateUtil.differenceOfDay(nowDate, onlinedate);
						if (dayNum > 0) {// 已上线

						} else {// 待发布
							artTemp.setIs_online("5");
						}
					}
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		ModelAndView mv = new ModelAndView("forward:/view/articleFile/collectList.jsp?ac=500007");// redirect模式
		mv.addObject("pageList", page2.getResult());
		mv.addObject("pageBt", page2PageBt(page2));
		mv.addObject("searchId", searchId);
		mv.addObject("searchName", searchName);
		return mv;
	}
	// 获取排行榜列表
	@RequestMapping("/readList")
	public ModelAndView readList(String pageNo, String pageSize, String searchId, String searchName,
			String author_name, String key_words, String file_type, String query_date,String ac) {
		int pageSizeI = BaseConstant.pageSize;
		int pageNoI = 1;
		try {
			pageNoI = Integer.parseInt(pageNo);
			pageSizeI = Integer.parseInt(pageSize);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		Map<String, Object> conditions = new HashMap<String, Object>();
		if (!StringUtil.isNull(author_name)) {
			conditions.put("author_name", author_name);
		}
		if (!StringUtil.isNull(key_words)) {
			conditions.put("key_words", key_words);
		}
		if (!StringUtil.isNull(file_type)) {
			conditions.put("file_type", file_type);
		}
		if (!StringUtil.isNull(query_date)) {
			conditions.put("create_time2", query_date);
		}
		Page<Article> page2 = new Page<Article>();
		PageHelper.startPage(pageNoI, pageSizeI);
		page2 = (Page<Article>) articleService.queryReadList(conditions);
		String title = "";
		List articleList = page2.getResult();
		try {
			if (articleList != null && articleList.size() > 0) {
				for (int i = 0; i < articleList.size(); i++) {
					Article artTemp = (Article) articleList.get(i);
					if (artTemp.getTitle() != null && artTemp.getTitle().length() > 0) {
						title = (artTemp.getTitle().length() > 15) ? artTemp.getTitle().substring(0, 14) + "......"
								: artTemp.getTitle();
					}
					artTemp.setTitle(title);
					artTemp.setSpare_1(BaseConstant.fileUploadPathZip + artTemp.getId().substring(0, 6) + "/article/"
							+ artTemp.getId() + "/" + artTemp.getId() + ".html");
					if ("1".equals(artTemp.getIs_online())) {
						Date nowDate = new Date();
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
						Date onlinedate = sdf.parse(artTemp.getOnline_date());
						int dayNum = DateUtil.differenceOfDay(nowDate, onlinedate);
						if (dayNum > 0) {// 已上线

						} else {// 待发布
							artTemp.setIs_online("5");
						}
					}
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		ModelAndView mv = new ModelAndView("forward:/view/articleFile/readList.jsp");// redirect模式
		mv.addObject("pageList", page2.getResult());
		mv.addObject("pageBt", page2PageBt(page2));
		mv.addObject("searchId", searchId);
		mv.addObject("ac", ac);
		mv.addObject("searchName", searchName);
		return mv;
	}
	// 排行榜置顶功能
		@RequestMapping("/upadteOrderRead")
		public ModelAndView upadteOrderRead(String id,String ac) {
			Article tempArt = new Article();
			tempArt.setId(id);
			try {
				articleService.updateOrderRead(tempArt);
				logger.info("置顶成功");
			} catch (Exception e) {
				logger.info("置顶失败");
				e.printStackTrace();
			}
			return readList("1", "10", "", "", "", "", "", "",ac);
		}

		// 收藏榜取消置顶功能
		@RequestMapping("/updateOrderReadqx")
		public ModelAndView updateOrderReadqx(String id,String ac) {
			Article tempArt = new Article();
			tempArt.setId(id);
			try {
				articleService.updateOrderReadqx(tempArt);
				logger.info("取消置顶成功");
			} catch (Exception e) {
				logger.info("取消置顶失败");
				e.printStackTrace();
			}
			return readList("1", "10", "", "", "", "", "", "",ac);
		}
	// 客户端获取收藏榜首页列表
	@ResponseBody
	@RequestMapping("/msCollectList")
	public BaseJson msCollectList(String pageNo, String pageSize, String searchId, String searchName, String user_id,
			String key_words, String author_name, String create_time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date a = new Date();
		String online_date = sdf.format(a);
		int pageSizeI = BaseConstant.pageSize;
		int pageNoI = 1;
		try {
			pageNoI = Integer.parseInt(pageNo);
			pageSizeI = Integer.parseInt(pageSize);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		Map<String, Object> conditions = new HashMap<String, Object>();
		if (!StringUtil.isNull(key_words)) {
			conditions.put("key_words", key_words);
		}
		if (!StringUtil.isNull(author_name)) {
			conditions.put("author_name", author_name);
		}
		if (!StringUtil.isNull(create_time)) {
			conditions.put("create_time2", create_time);
		}
		conditions.put("online_date", online_date);
		Page<Article> page2 = new Page<Article>();
		PageHelper.startPage(pageNoI, pageSizeI);
		page2 = (Page<Article>) articleService.queryCollectList(conditions);
		List articleList = page2.getResult();
		ArticleReadCollect temp = new ArticleReadCollect();
		for (int i = 0; i < articleList.size(); i++) {
			Article artTemp = (Article) articleList.get(i);
			artTemp.setSpare_1(BaseConstant.fileUploadPathZip + artTemp.getId().substring(0, 6) + "/article/"
					+ artTemp.getId() + "/" + artTemp.getId() + ".html");
			// 获取当前用户对文章的喜爱和收藏状态
			// 获取喜爱状态
			Map<String, Object> conditionMem = new HashMap<String, Object>();
			conditionMem.put("article_id", artTemp.getId());
			conditionMem.put("read_user_id", user_id);
			conditionMem.put("type", "1");
			List collectList = articleReadCollectService.queryStatueByType(conditionMem);
			if (collectList == null || collectList.size() == 0) {
				artTemp.setIs_love("0");
			} else {
				temp = (ArticleReadCollect) collectList.get(0);
				artTemp.setIs_love(temp.getState_value());
			}
			// 获取收藏状态
			conditionMem.clear();
			conditionMem.put("article_id", artTemp.getId());
			conditionMem.put("read_user_id", user_id);
			conditionMem.put("type", "2");
			List collectList2 = articleReadCollectService.queryStatueByType(conditionMem);
			if (collectList2 == null || collectList2.size() == 0) {
				artTemp.setIs_collect("0");
			} else {
				temp = (ArticleReadCollect) collectList2.get(0);
				artTemp.setIs_collect(temp.getState_value());
			}
		}
		return new BaseJson(200, "查询成功", "", page2, page2PageBt(page2));
	}
	// 客户端获取排行榜首页列表
	@ResponseBody
	@RequestMapping("/msReadList")
	public BaseJson msReadList(String pageNo, String pageSize, String searchId, String searchName, String user_id,
			String key_words, String author_name, String create_time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date a = new Date();
		String online_date = sdf.format(a);
		int pageSizeI = BaseConstant.pageSize;
		int pageNoI = 1;
		try {
			pageNoI = Integer.parseInt(pageNo);
			pageSizeI = Integer.parseInt(pageSize);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		Map<String, Object> conditions = new HashMap<String, Object>();
		if (!StringUtil.isNull(key_words)) {
			conditions.put("key_words", key_words);
		}
		if (!StringUtil.isNull(author_name)) {
			conditions.put("author_name", author_name);
		}
		if (!StringUtil.isNull(create_time)) {
			conditions.put("create_time2", create_time);
		}
		conditions.put("online_date", online_date);
		Page<Article> page2 = new Page<Article>();
		PageHelper.startPage(pageNoI, pageSizeI);
		page2 = (Page<Article>) articleService.queryReadList(conditions);
		List articleList = page2.getResult();
		ArticleReadCollect temp = new ArticleReadCollect();
		for (int i = 0; i < articleList.size(); i++) {
			Article artTemp = (Article) articleList.get(i);
			artTemp.setSpare_1(BaseConstant.fileUploadPathZip + artTemp.getId().substring(0, 6) + "/article/"
					+ artTemp.getId() + "/" + artTemp.getId() + ".html");
			// 获取当前用户对文章的喜爱和收藏状态
			// 获取喜爱状态
			Map<String, Object> conditionMem = new HashMap<String, Object>();
			conditionMem.put("article_id", artTemp.getId());
			conditionMem.put("read_user_id", user_id);
			conditionMem.put("type", "1");
			List collectList = articleReadCollectService.queryStatueByType(conditionMem);
			if (collectList == null || collectList.size() == 0) {
				artTemp.setIs_love("0");
			} else {
				temp = (ArticleReadCollect) collectList.get(0);
				artTemp.setIs_love(temp.getState_value());
			}
			// 获取收藏状态
			conditionMem.clear();
			conditionMem.put("article_id", artTemp.getId());
			conditionMem.put("read_user_id", user_id);
			conditionMem.put("type", "2");
			List collectList2 = articleReadCollectService.queryStatueByType(conditionMem);
			if (collectList2 == null || collectList2.size() == 0) {
				artTemp.setIs_collect("0");
			} else {
				temp = (ArticleReadCollect) collectList2.get(0);
				artTemp.setIs_collect(temp.getState_value());
			}
			// 获取是否答题状态
			SubjectResult subResult = new SubjectResult();
			subResult.setArticleId(artTemp.getId());
			subResult.setMemberId(user_id);
			List resultList= new ArrayList();
			try {
				resultList = articleService.querySubjectResult(subResult);
				if(resultList==null||resultList.size()==0){
					artTemp.setIs_answer("0");
				}else{
					artTemp.setIs_answer("1");
				}
			} catch (Exception e) {
				e.printStackTrace();
			   }			
		}
		return new BaseJson(200, "查询成功", "", page2, page2PageBt(page2));
	}
	// 客户端根据文章id获取文章信息
		@ResponseBody
		@RequestMapping("/msQueryArticleById")
		public BaseJson msQueryArticleById(String id) {
			Article article = new Article();
			article = articleService.findById(id);
			article.setSpare_1(BaseConstant.fileUploadPathZip + article.getId().substring(0, 6) + "/article/"
					+ article.getId() + "/" + article.getId() + ".html");
			//获取该文章各种统计数
			Article art =articleService.queryNumById(id);
			article.setCollect_num(art.getCollect_num());
			article.setRead_num(art.getRead_num());
			article.setComment_num(art.getComment_num());
			article.setLove_num(art.getLove_num());
			// 获取当前用户对文章的喜爱和收藏状态
			// 获取喜爱状态
			ArticleReadCollect temp = new ArticleReadCollect(); 
			Map<String, Object> conditionMem = new HashMap<String, Object>();
			conditionMem.put("article_id", article.getId());
			conditionMem.put("read_user_id", getUserSession().userId);
			conditionMem.put("type", "1");
			List collectList = articleReadCollectService.queryStatueByType(conditionMem);
			if (collectList == null || collectList.size() == 0) {
				article.setIs_love("0");
			} else {
				temp = (ArticleReadCollect) collectList.get(0);
				article.setIs_love(temp.getState_value());
			}
			// 获取收藏状态
			conditionMem.clear();
			conditionMem.put("article_id", article.getId());
			conditionMem.put("read_user_id", getUserSession().userId);
			conditionMem.put("type", "2");
			List collectList2 = articleReadCollectService.queryStatueByType(conditionMem);
			if (collectList2 == null || collectList2.size() == 0) {
				article.setIs_collect("0");
			} else {
				temp = (ArticleReadCollect) collectList2.get(0);
				article.setIs_collect(temp.getState_value());
			}
			// 获取是否答题状态
			SubjectResult subResult = new SubjectResult();
			subResult.setArticleId(article.getId());
			subResult.setMemberId(getUserSession().userId);
			List resultList= new ArrayList();
			try {
				resultList = articleService.querySubjectResult(subResult);
				if(resultList==null||resultList.size()==0){
					article.setIs_answer("0");
				}else{
					article.setIs_answer("1");
					}
				} catch (Exception e) {
					e.printStackTrace();
				   }			
			return new BaseJson(200, "查询成功", "",article);
		}
	// 客户端获取文章列表
	@ResponseBody
	@RequestMapping("/msContentList")
	public BaseJson msContentList(String pageNo, String pageSize, String searchId, String searchName, String user_id,
			String key_words, String author_name, String create_time, String ml_type,String is_jx,String jx_type,String industry_name) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date a = new Date();
		String online_date = sdf.format(a);
		int pageSizeI = BaseConstant.pageSize;
		int pageNoI = 1;
		try {
			pageNoI = Integer.parseInt(pageNo);
			pageSizeI = Integer.parseInt(pageSize);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		if(getUserSession()==null){
			user_id="999";
		}else{
			user_id = getUserSession().userId;
		}
		Map<String, Object> conditions = new HashMap<String, Object>();
		if (!StringUtil.isNull(key_words)) {
			conditions.put("key_words", key_words);
		}
		if (!StringUtil.isNull(author_name)) {
			conditions.put("author_name", author_name);
		}
		if (!StringUtil.isNull(industry_name)) {
			conditions.put("industry_name", industry_name);
		}
		if (!StringUtil.isNull(create_time)) {
			conditions.put("create_time2", create_time);
		}
		//获取精选文章   is_jx  1  是精选文章
		if (!StringUtil.isNull(is_jx)) {
			conditions.put("is_jx", is_jx);
		}
		if (!StringUtil.isNull(ml_type)) {
			if ("0".equals(ml_type)) {
				conditions.put("order_new", "1");

			} else if (ml_type.equals("1")) {// 精选
				conditions.put("file_type", "精选");
				conditions.put("order_col", "1");
			} else if (ml_type.equals("2")) {// 热门
				conditions.put("file_type", "热门");
				// conditions.put("order_hot", "1");

			}
		}
		conditions.put("online_date", online_date);
		Page<Article> page2 = new Page<Article>();
		PageHelper.startPage(pageNoI, pageSizeI);
		page2 = (Page<Article>) articleService.queryArticleList(conditions);
		List articleList = page2.getResult();
		ArticleReadCollect temp = new ArticleReadCollect();
		for (int i = 0; i < articleList.size(); i++) {
			Article artTemp = (Article) articleList.get(i);
			artTemp.setSpare_1(BaseConstant.fileUploadPathZip + artTemp.getId().substring(0, 6) + "/article/"
					+ artTemp.getId() + "/" + artTemp.getId() + ".html");
			// 获取当前用户对文章的喜爱和收藏状态
			// 获取喜爱状态
			Map<String, Object> conditionMem = new HashMap<String, Object>();
			conditionMem.put("article_id", artTemp.getId());
			conditionMem.put("read_user_id", user_id);
			conditionMem.put("type", "1");
			List collectList = articleReadCollectService.queryStatueByType(conditionMem);
			if (collectList == null || collectList.size() == 0) {
				artTemp.setIs_love("0");
			} else {
				temp = (ArticleReadCollect) collectList.get(0);
				artTemp.setIs_love(temp.getState_value());
			}
			// 获取收藏状态
			conditionMem.clear();
			conditionMem.put("article_id", artTemp.getId());
			conditionMem.put("read_user_id", user_id);
			conditionMem.put("type", "2");
			List collectList2 = articleReadCollectService.queryStatueByType(conditionMem);
			if (collectList2 == null || collectList2.size() == 0) {
				artTemp.setIs_collect("0");
			} else {
				temp = (ArticleReadCollect) collectList2.get(0);
				artTemp.setIs_collect(temp.getState_value());
			}
			// 获取阅读状态
			conditionMem.clear();
			conditionMem.put("article_id", artTemp.getId());
			conditionMem.put("read_user_id", user_id);
			conditionMem.put("type", "0");
			List collectList3 = articleReadCollectService.queryStatueByType(conditionMem);
			if (collectList3 == null || collectList3.size() == 0) {
				artTemp.setIs_read("0");
			} else {
				artTemp.setIs_read("1");
			}
			// 获取是否答题状态
			SubjectResult subResult = new SubjectResult();
			subResult.setArticleId(artTemp.getId());
			subResult.setMemberId(user_id);
			List resultList= new ArrayList();
			try {
				resultList = articleService.querySubjectResult(subResult);
				if(resultList==null||resultList.size()==0){
					artTemp.setIs_answer("0");
				}else{
					artTemp.setIs_answer("1");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return new BaseJson(200, "查询成功", "", page2, page2PageBt(page2));
	}

	// 客户端收藏/喜爱状态更改/阅读记录
	// type 区分 0：阅读，1：喜爱，2，收藏
	// spare_1 0:取消 1 确认
	@ResponseBody
	@RequestMapping("/msReadLogChangeState")
	public BaseJson msReadLogChangeState(String article_id, String type, String user_id, String state) {
		// 保存到状态表中去
		// 根据文章id，用户id查看是否有收藏和喜爱记录，若无，则执行新增操作，有则执行新增操作

		ArticleReadCollect collect = new ArticleReadCollect();
		Map<String, Object> conditionMem = new HashMap<String, Object>();
		if (type.equals("0")) {
			collect.setId(StringUtil.getUUIDString());
			collect.setFile_id(article_id);
			collect.setType(type);
			collect.setRead_user_id(user_id);
			collect.setState_value(state);
			articleReadCollectService.save(collect);
		} else {
			conditionMem.put("article_id", article_id);
			conditionMem.put("read_user_id", user_id);
			conditionMem.put("type", type);
			List collectList = articleReadCollectService.queryStatueByType(conditionMem);
			if (collectList == null || collectList.size() == 0) {
				collect.setId(StringUtil.getUUIDString());
				collect.setFile_id(article_id);
				collect.setType(type);
				collect.setRead_user_id(user_id);
				collect.setState_value(state);
				collect.setCreate_date(new Date());
				articleReadCollectService.save(collect);
			} else {
				collect = (ArticleReadCollect) collectList.get(0);
				collect.setState_value(state);
				articleReadCollectService.save(collect);
			}
		}
		//判断是否满足积分规则
		String jfId="";
		if(type.equals("0")&&state.equals("0")){//首次阅读 加积分
			 jfId=BaseConstant.JFYDID;
			//插入记录到积分日志表中
				IntegrationMember inter = new IntegrationMember();
				inter.setId(StringUtil.getUUIDString());
				inter.setMemberId(user_id);
				inter.setArticleId(article_id);
				Integration gz = new Integration();
				try {
					gz = integrationService.queryIntegrationById(jfId);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				inter.setIntegrationId(gz.getId());//答题规则id
				inter.setScore(gz.getScore());
				inter.setCreateDate(new Date());

				try {
					integrationService.saveIntegrationMember(inter);
					//根据用户id获取用户信息  查积分
					Member member = memberService.queryMemberById(user_id);
					if(StringUtil.isNull(member.getIntegration())){
						member.setIntegration(gz.getScore());
					}else{
						String jf = member.getIntegration();
						member.setIntegration(Integer.parseInt(jf)+Integer.parseInt(gz.getScore())+"");
					}
					//积分累计
					memberService.updateMember(member);
				} catch (Exception e) {
					e.printStackTrace();
				}
		}else if(type.equals("1")){//喜爱  根据积分
			//判断之前是否对该文章已喜爱过，若有，则不新增积分，若无，按规则新增积分
			jfId=BaseConstant.JFDZID;
			IntegrationMember tempInteg = new IntegrationMember();
			tempInteg.setArticleId(article_id);
			tempInteg.setMemberId(user_id);
			tempInteg.setIntegrationId(jfId);
			List tempList = new ArrayList();
			try {
				tempList = integrationService.queryIntegrationMemberList(tempInteg);
				if(tempList==null||tempList.size()==0){
					//插入记录到积分日志表中
					IntegrationMember inter = new IntegrationMember();
					inter.setId(StringUtil.getUUIDString());
					inter.setMemberId(user_id);
					inter.setArticleId(article_id);
					Integration gz = new Integration();
					try {
						gz = integrationService.queryIntegrationById(jfId);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					inter.setIntegrationId(gz.getId());//积分规则id
					inter.setScore(gz.getScore());
					inter.setCreateDate(new Date());

					try {
						integrationService.saveIntegrationMember(inter);
						//根据用户id获取用户信息  查积分
						Member member = memberService.queryMemberById(user_id);
						if(StringUtil.isNull(member.getIntegration())){
							member.setIntegration(gz.getScore());
						}else{
							String jf = member.getIntegration();
							member.setIntegration(Integer.parseInt(jf)+Integer.parseInt(gz.getScore())+"");
						}
						//积分累计
						memberService.updateMember(member);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}
		
		// 保存到日志表
		FileReadLog fileReadLog = new FileReadLog();
		fileReadLog.setId(StringUtil.getUUIDString());
		fileReadLog.setFile_id(article_id);
		fileReadLog.setType(type);
		fileReadLog.setRead_date(new Date());
		fileReadLog.setRead_user_id(user_id);
		fileReadLog.setSpare_1(state);
		fileReadLogService.save(fileReadLog);
		return new BaseJson(200, "新增成功", "");
	}

	// 客户端获取收藏列表
	@ResponseBody
	@RequestMapping("/msQueryCollectList")
	public BaseJson msQueryCollectList(String pageNo, String pageSize, String searchId, String searchName,
			String member_id) {
		int pageSizeI = BaseConstant.pageSize;
		int pageNoI = 1;
		try {
			pageNoI = Integer.parseInt(pageNo);
			pageSizeI = Integer.parseInt(pageSize);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		Map<String, Object> conditions = new HashMap<String, Object>();
		Page<Article> page2 = new Page<Article>();
		PageHelper.startPage(pageNoI, pageSizeI);
		page2 = (Page<Article>) articleService.queryArticleList(conditions);
		List articleList = page2.getResult();
		List collectArtList = new ArrayList();
		ArticleReadCollect temp = new ArticleReadCollect();
		Map<String, Object> conditionMem = new HashMap<String, Object>();
		for (int i = 0; i < articleList.size(); i++) {
			Article artTemp = (Article) articleList.get(i);
			artTemp.setSpare_1(BaseConstant.fileUploadPathZip + artTemp.getId().substring(0, 6) + "/article/"
					+ artTemp.getId() + "/" + artTemp.getId() + ".html");
			// 获取当前用户对文章的喜爱和收藏状态
			// 获取喜爱状态

			conditionMem.put("article_id", artTemp.getId());
			conditionMem.put("read_user_id", member_id);
			conditionMem.put("type", "1");
			List collectList = articleReadCollectService.queryStatueByType(conditionMem);
			if (collectList == null || collectList.size() == 0) {
				artTemp.setIs_love("0");
			} else {
				temp = (ArticleReadCollect) collectList.get(0);
				artTemp.setIs_love(temp.getState_value());
			}
			// 获取收藏状态
			conditionMem.clear();
			conditionMem.put("article_id", artTemp.getId());
			conditionMem.put("read_user_id", member_id);
			conditionMem.put("type", "2");
			List collectList2 = articleReadCollectService.queryStatueByType(conditionMem);
			if (collectList2 == null || collectList2.size() == 0) {
				artTemp.setIs_collect("0");
			} else {
				temp = (ArticleReadCollect) collectList2.get(0);
				artTemp.setIs_collect(temp.getState_value());
				if (temp.getState_value().equals("0")) {
				} else {
					collectArtList.add(artTemp);
				}

			}
		}
		return new BaseJson(200, "查询成功", "", collectArtList);
	}

	// 客户端取消收藏（可多个）
	@ResponseBody
	@RequestMapping("/msChangeCollectState")
	public BaseJson msChangeCollectState(String member_id, String article_ids) {
		if (!StringUtil.isNull(article_ids)) {
			String[] artIds = article_ids.split(",");
			Map<String, Object> conditions = new HashMap<String, Object>();
			for (int i = 0; i < artIds.length; i++) {
				conditions.put("article_id", artIds[i]);
				conditions.put("read_user_id", member_id);
				conditions.put("type", "2");
				List collectList = articleReadCollectService.queryStatueByType(conditions);
				if (collectList == null || collectList.size() == 0) {

				} else {
					ArticleReadCollect temp = (ArticleReadCollect) collectList.get(0);
					temp.setState_value("0");
					articleReadCollectService.save(temp);
				}
			}
		}
		return new BaseJson(200, "取消收藏操作成功", "");
	}

	// 文章管理首页
	@RequestMapping("/contentMag")
	public ModelAndView contentMag(String msg) {
		Map<String, Object> conditions = new HashMap<String, Object>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// 获取作者list
		conditions.put("super_dir_id", "1001");
		List authorList = new ArrayList();
		List industryList = new ArrayList();
		authorList = directoryService.findDirListByPId(conditions);
		conditions.clear();
		// 获取行业list
		conditions.put("super_dir_id", "1002");
		industryList = directoryService.findDirListByPId(conditions);
		// 判断当前日期是否在有效期内
		Date date = new Date();
		String nowDate = sdf.format(date);
		conditions.put("nowDate", nowDate);
		List dirList = new ArrayList();
		dirList = directoryService.findDirListByPId(conditions);
		ModelAndView mv = new ModelAndView("forward:/view/articleFile/contentMag.jsp?ac=500003");// redirect模式
		mv.addObject("authorList", authorList);
		mv.addObject("industryList", industryList);
		mv.addObject("dirList", dirList);
		mv.addObject("msg" , msg);
		return mv;
	}

	@RequestMapping("/contentSave")
	public ModelAndView contentSave(HttpServletRequest request, Article article, MultipartFile pic, String newsContent,
			MultipartFile zip) {
		long dayNum = 0;
		String result = "";
		String fileName = pic.getOriginalFilename();
		String filePath = BaseConstant.fileUploadPath + DateUtil.getCurrentMonth() + "/image/";
		String fileZipPath = BaseConstant.fileUploadPathZip + DateUtil.getCurrentMonth() + "/article/";
		String type = pic.getOriginalFilename().substring(fileName.indexOf(".") + 1, fileName.length());
		File file = null;
		article.setId(DateUtil.getCurrentMonth() + StringUtil.getUUIDString());
		article.setCreate_date(new Date());
		try {
			if (article.getIs_online().equals("0")) {
				Date nowDate = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				article.setOnline_date(sdf.format(nowDate));
				// 判断是否推送 1不推送 2 未推送 3 已推送
				article.setOrder_hot(1);
			} else if (article.getIs_online().equals("1")) {
				Date nowDate = new Date();
				long nowLong = nowDate.getTime();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				Date onlinedate = sdf.parse(article.getOnline_date() + ":00");
				long onlineLong = onlinedate.getTime();
				dayNum = nowLong - onlineLong;
				if (dayNum > 0) {// 可推送
					article.setOrder_hot(3);
				} else {// 待推送
					article.setOrder_hot(2);
				}

			}
			// 保存缩略图
			if (pic != null && pic.getSize() != 0) {
				CommonsMultipartFile cf = (CommonsMultipartFile) pic;
				DiskFileItem fi = (DiskFileItem) cf.getFileItem();
				file = fi.getStoreLocation();
				article.setSmall_pic_path(filePath + article.getId() + "." + type);
				article.setSmall_pic_prior_name(fileName);
				article.setSmall_pic_save_name(article.getId() + "." + type);
				result = copyFile(request, filePath, article.getId() + "." + type, file);
			}
			// 保存压缩包

			if (zip != null && zip.getSize() != 0) {
				CommonsMultipartFile cf = (CommonsMultipartFile) zip;
				DiskFileItem fi = (DiskFileItem) cf.getFileItem();
				file = fi.getStoreLocation();
				result = copyFile(request, fileZipPath, article.getId() + ".zip", file);
				// 获取zip的本地路径；保存路径；文章id
				String localPath = request.getSession().getServletContext()
						.getRealPath(fileZipPath + article.getId() + ".zip");
				String jyPath = request.getSession().getServletContext().getRealPath(fileZipPath);
				ZipUtil.unZip(localPath, jyPath + "/", article.getId());
				article.setSpare_3(zip.getOriginalFilename());
			}
		} catch (Exception e) {
			e.printStackTrace(); 
			ModelAndView mv = new ModelAndView("forward:/view/articleFile/contentMag.jsp?ac=500003");// redirect模式
			mv.addObject("msg", "保存信息异常，请检查文件格式(附件只支持zip格式)，填写内容是否有误!");
			return mv;
		}
		if ("error".equals(result)) {
		} else {
			article.setOrder_sc(0);
			article.setOrder_col(0);
			article.setOrder_new(0);
			articleService.save(article);
		}
		if (dayNum > 0) {// 可推送
			// 推送
			String text = "标题：" + article.getTitle() + ",作者：" + article.getAuthor_name();
			// 安卓推送
			PushMsg pushMsg = new PushMsg(StringUtil.getUUIDString(), PushApi.dgbAndroidApiKey, new Date(),
					getUserSession().userId, "0", text);
			// ios推送
			PushMsg pushMsgIos = new PushMsg(StringUtil.getUUIDString(), PushApi.dgbIosApiKey, new Date(),
					getUserSession().userId, "0", text);
			try {
				// ios android
				PushApi.pussToAll(pushMsg);// 推送安卓平台
//				pushMsgIos.setMsg_text(text);
//				PushApi.push2All(text);// 推送ios平台
//				pushApiService.savePushMsg(pushMsg);
			} catch (Exception e) {
				logger.info("推送失败");
				e.printStackTrace();
			}
		}

		ModelAndView mv = new ModelAndView("forward:/view/articleFile/contentMag.jsp?ac=500003");// redirect模式
		return contentIndex("1", "10", "", "", "", "", "", "","");
	}

	// 查看收藏、喜爱日志列表
	@RequestMapping("/showLogList")
	public ModelAndView showLogList(String pageNo, String pageSize, String article_id, String type, String searchName) {
		int pageSizeI = BaseConstant.pageSize;
		int pageNoI = 1;
		try {
			pageNoI = Integer.parseInt(pageNo);
			pageSizeI = Integer.parseInt(pageSize);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		Map<String, Object> conditions = new HashMap<String, Object>();
		conditions.put("id", article_id);
		conditions.put("type", type);
		if (!StringUtil.isNull(searchName)) {
			conditions.put("user_name", searchName);
		}
		Page<FileReadLog> page2 = new Page<FileReadLog>();
		PageHelper.startPage(pageNoI, pageSizeI);
		page2 = (Page<FileReadLog>) fileReadLogService.queryLogList(conditions);
		ModelAndView mv = new ModelAndView("forward:/view/articleFile/loveLogList.jsp?ac=500004");// redirect模式
		mv.addObject("pageList", page2.getResult());
		mv.addObject("pageBt", page2PageBt(page2));
		mv.addObject("article_id", article_id);
		mv.addObject("type", type);
		return mv;
	}
		//删除信息
		@ResponseBody
		@RequestMapping("/removeSubject")  
		public BaseJson removeSubject(String subject_id){
			
			try {
				if(StringUtil.isNull(subject_id)){ 
					return new BaseJson(600,"参数无效","");
				}else{
					//删除从表相关信息
					articleService.deleteSubjectMagById(subject_id);
					return new BaseJson(1,"删除成功!","");
				}
			} catch (Exception e) {
				e.printStackTrace();
				return new BaseJson(600,"参数无效","");
			}
			
		}
	// 跳转到答题列表页面
	@RequestMapping("/subjectList")
	public ModelAndView subjectList(String pageNo, String pageSize,String id,String ac) {
			int pageSizeI = BaseConstant.pageSize;
			int pageNoI = 1;
			try {
				pageNoI = Integer.parseInt(pageNo);
				pageSizeI = Integer.parseInt(pageSize);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
			Page<SubjectMag> page = new Page<SubjectMag>();
			PageHelper.startPage(pageNoI, pageSizeI);
			page = (Page<SubjectMag>) articleService.querySubjectMagByArticleId(id);
			List subjectList = new ArrayList();
			subjectList=page.getResult();
			String ans = "";
			if(subjectList!=null||subjectList.size()!=0){
				for(int i=0;i<subjectList.size();i++){
					SubjectMag temp = (SubjectMag) subjectList.get(i);
					if(temp.getSubjectType().equals("0")){
						if(temp.getAnswer().equals("1")){
							temp.setAnswer("对");
						}else if(temp.getAnswer().equals("2")){
							temp.setAnswer("错");
						}
						
					}else if(temp.getSubjectType().equals("1")||temp.getSubjectType().equals("2")){
						ans = temp.getAnswer().replace("1", "A").replace("2", "B").replace("3", "C").replace("4", "D");
						temp.setAnswer(ans);
					}
				}
			}
			ModelAndView mv = new ModelAndView("forward:/view/articleFile/subjectList.jsp");// redirect模式
			mv.addObject("pageList", subjectList);
			mv.addObject("pageBt", page2PageBt(page));
			mv.addObject("ac", ac);
			mv.addObject("article_id", id);
			return mv;
		}
	// 客户端获取答题列表
	@ResponseBody
	@RequestMapping("/msSubjectList") 
	public BaseJson msSubjectList(String id) {
		List subjestList = new ArrayList();
		subjestList = articleService.querySubjectMagByArticleId(id);
		if(subjestList!=null||subjestList.size()==0){
			for(int i=0;i<subjestList.size();i++){
				SubjectMag temp = (SubjectMag) subjestList.get(i);
				if(temp.getSubjectType().equals("0")){
					
				}else{
					String [] items =temp.getSubjectItems().split("%%&&");
					for(int j=0;j<items.length;j++){
						String [] items_sub =items[j].split("\\:");
						temp.getItems().put(items_sub[0], items_sub[1]);
					}
				}
			}
			
		}
		return new BaseJson(200, "查询!", "",subjestList);
	}
	// 客户端获取答题列表
	@ResponseBody
	@RequestMapping("/msSaveSubject") 
	public BaseJson msSaveSubject(String article_id,String subjectItems,String memberId) {
		BaseJson json = new BaseJson();
		String []items = subjectItems.split(";");
		try {
			for(int i=0;i<items.length;i++){
				SubjectResult temp = new SubjectResult();
				String [] tempSub = items[i].split(",");
				temp.setId(StringUtil.getUUIDString());
				temp.setArticleId(article_id);
				temp.setMemberId(memberId);
				temp.setSubjectId(tempSub[0]);
				temp.setMemberAnswer(tempSub[1]);
				temp.setSubjectAnswer(tempSub[2]);
				if(tempSub[1].equals(tempSub[2])){
					temp.setResult("1");//正确
				}else{
					temp.setResult("0");//错误
				}
				
					//保存到答题结果表中
					articleService.saveSubjectResult(temp);
					json.setStatus(200);
				}
			//将是否答题记录到日志表中
			ArticleReadCollect collect = new ArticleReadCollect();
			collect.setId(StringUtil.getUUIDString());
			collect.setFile_id(article_id);
			collect.setRead_user_id(memberId);
			collect.setType("3");
			collect.setCreate_date(new Date());
			articleReadCollectService.save(collect);
			//插入记录到积分日志表中
			IntegrationMember inter = new IntegrationMember();
			inter.setId(StringUtil.getUUIDString());
			inter.setMemberId(memberId);
			inter.setArticleId(article_id);
			//根据积分规则id获取积分规则信息
			Integration gz = new Integration();
			gz = integrationService.queryIntegrationById(BaseConstant.JFDTID);
			inter.setIntegrationId(gz.getId());//答题规则id
			inter.setScore(gz.getScore());
			inter.setCreateDate(new Date());
			integrationService.saveIntegrationMember(inter);
			//根据用户id获取用户信息  查积分
			Member member = memberService.queryMemberById(memberId);
			if(StringUtil.isNull(member.getIntegration())){
				member.setIntegration(gz.getScore());
			}else{
				String jf = member.getIntegration();
				member.setIntegration(Integer.parseInt(jf)+Integer.parseInt(gz.getScore())+"");
			}
			//积分累计
			memberService.updateMember(member);
			} catch (Exception e) {
				json.setStatus(600);
				e.printStackTrace();
			}
		
		return json;
	}
	// 跳转到答题新增页面
	@RequestMapping("/toAddSubject")
	public ModelAndView toAddSubject(String article_id,String ac) {
		ModelAndView mv = new ModelAndView("forward:/view/articleFile/subjectAdd.jsp");// redirect模式
		mv.addObject("ac", ac);
		mv.addObject("article_id", article_id);
		return mv;
	}
	// 跳转到答题修改页面
	@RequestMapping("/toEditSubject")
	public ModelAndView toEditSubject(String article_id,String subjectId,String ac) {
	   SubjectMag subject = new SubjectMag();
	   try {
		subject = articleService.querySubjectById(subjectId);
	} catch (Exception e) {
		e.printStackTrace();
	}
	   ModelAndView mv = new ModelAndView("forward:/view/articleFile/subjectEdit.jsp");// redirect模式
	   mv.addObject("ac", ac);
	   mv.addObject("subject", subject);
	   mv.addObject("article_id", article_id);
	   return mv;
	}
	//保存题目
	@RequestMapping("/saveSubject")
	public ModelAndView saveSubject(SubjectMag subjectMag,String article_id,String ac,String chooseItems,String sub_answer) {
		try {
			if(subjectMag!=null){
				subjectMag.setSubjectId(StringUtil.getUUIDString());
				subjectMag.setArticleId(article_id);
				if(subjectMag.getSubjectType().equals("0")){
				}else{
					subjectMag.setSubjectItems(chooseItems);
				}
				subjectMag.setAnswer(sub_answer);
				subjectMag.setCreateUserId(getUserSession().userNameCn);
				articleService.saveSubjectMag(subjectMag);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return subjectList("1", "10", article_id, ac);
	}
	//保存修改后题目
		@RequestMapping("/saveEditSubject")
		public ModelAndView saveEditSubject(SubjectMag subjectMag,String article_id,String ac,String chooseItems,String sub_answer) {
			try {
				if(subjectMag!=null){
					if(subjectMag.getSubjectType().equals("0")){
					}else{
						subjectMag.setSubjectItems(chooseItems);
					}
					subjectMag.setAnswer(sub_answer);
					subjectMag.setCreateUserId(getUserSession().userNameCn);
					articleService.updateSubjectMag(subjectMag);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return subjectList("1", "10", article_id, ac);
		}
	// 跳转到修改页面
	@RequestMapping("/toEditcontent")
	public ModelAndView toEditcontent(HttpServletRequest request, String id, String order_type, String file_type,
			String ac,String msg) {
		Article article = new Article();
		article = articleService.findById(id);
		Map<String, Object> conditions = new HashMap<String, Object>();
		
		// 获取作者list
		conditions.put("super_dir_id", "1001");
		List authorList = new ArrayList();
		List industryList = new ArrayList();
		authorList = directoryService.findDirListByPId(conditions);
		conditions.clear();
		// 获取行业list
		conditions.put("super_dir_id", "1002");
		industryList = directoryService.findDirListByPId(conditions);
		conditions.clear();

		// 删除压缩包及解压文件夹
		String fileZipPath1 = BaseConstant.fileUploadPathZip + article.getId().substring(0, 6) + "/article/"
				+ article.getId() + ".zip";
		String fileZipPath2 = request.getSession().getServletContext().getRealPath(fileZipPath1);
		File fileZip = new File(fileZipPath2);
		if (fileZip.exists()) {
			// 压缩包文件路径
			article.setSpare_1(fileZipPath1);
		}
		ModelAndView mv = new ModelAndView("forward:/view/articleFile/editContent.jsp");// redirect模式
		mv.addObject("article", article);
		mv.addObject("authorList", authorList);
		mv.addObject("industryList", industryList);
		mv.addObject("order_type", order_type);
		mv.addObject("file_type", file_type);
		mv.addObject("ac", ac);
		mv.addObject("msg", msg);
		return mv;
	}

	// 保存修改信息
	@RequestMapping("/saveEditArticle")
	public ModelAndView saveEditArticle(HttpServletRequest request, Article article, MultipartFile pic,
			MultipartFile zip) {
		long dayNum = 0;
		String result = "";
		String fileName = pic.getOriginalFilename();
		String filePath = BaseConstant.fileUploadPath + DateUtil.getCurrentMonth() + "/image/";
		String type = pic.getOriginalFilename().substring(fileName.indexOf(".") + 1, fileName.length());
		String textPath = BaseConstant.fileUploadPath + DateUtil.getCurrentMonth() + "/txt/";
		String htmlPath = BaseConstant.fileUploadPath + DateUtil.getCurrentMonth() + "/html/";
		String fileZipPath = BaseConstant.fileUploadPathZip + article.getId().substring(0, 6) + "/article/";
		File file = null;
		// article.setId(DateUtil.getCurrentMonth()+StringUtil.getUUIDString());
		article.setCreate_date(new Date());
		Article artTemp = new Article();
		artTemp = articleService.findById(article.getId());
		article.setOrder_new(artTemp.getOrder_new());
		article.setOrder_sc(artTemp.getOrder_sc());
		// 判断是否推送
		try {
			if (article.getIs_online().equals("0")) {
				Date nowDate = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				article.setOnline_date(sdf.format(nowDate));
				// 1不推送 2 未推送 3 已推送
				article.setOrder_hot(1);
			} else if (article.getIs_online().equals("1")) {
				Date nowDate = new Date();
				long nowLong = nowDate.getTime();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				Date onlinedate = sdf.parse(article.getOnline_date() + ":00");
				long onlineLong = onlinedate.getTime();
				dayNum = nowLong - onlineLong;
				if (dayNum > 0) {// 可推送
					article.setOrder_hot(3);
				} else {// 待推送
					article.setOrder_hot(2);
				}

			}
			// 保存缩略图
			if (pic != null && pic.getSize() != 0) {
				// 删除缩略图
				if (artTemp != null && null != artTemp.getSmall_pic_path() && !artTemp.getSmall_pic_path().equals("")) {
					String filePath2 = request.getSession().getServletContext().getRealPath(artTemp.getSmall_pic_path());
					file = new File(filePath2);
					if (file.exists())
						file.delete();
				}
				CommonsMultipartFile cf = (CommonsMultipartFile) pic;
				DiskFileItem fi = (DiskFileItem) cf.getFileItem();
				file = fi.getStoreLocation();
				article.setSmall_pic_path(filePath + article.getId() + "." + type);
				article.setSmall_pic_prior_name(fileName);
				article.setSmall_pic_save_name(article.getId() + "." + type);
				result = copyFile(request,filePath, article.getId() + "." + type, file);
			}
			// 保存压缩包

			if (zip != null && zip.getSize() != 0) {
				// 删除压缩包及解压文件夹
				String fileZipPath1 = BaseConstant.fileUploadPathZip + article.getId().substring(0, 6) + "/article/"
						+ article.getId();
				fileZipPath1 = request.getSession().getServletContext().getRealPath(fileZipPath1);
				File fileZip = new File(fileZipPath1);
				try {
					if (fileZip.exists()) {
						deletefile(fileZipPath1);
					}
				} catch (Exception e) {
					logger.info("文件夹删除失败");
					e.printStackTrace();
				}
				// 删除压缩包
				String oriZip = fileZipPath1 + ".zip";
				fileZip = new File(oriZip);
				if (fileZip.exists()) {
					fileZip.delete();
				}
				CommonsMultipartFile cf = (CommonsMultipartFile) zip;
				DiskFileItem fi = (DiskFileItem) cf.getFileItem();
				file = fi.getStoreLocation();
				result = copyFile(request, fileZipPath, article.getId() + ".zip", file);
				// 获取zip的本地路径；保存路径；文章id
				String localPath = request.getSession().getServletContext()
						.getRealPath(fileZipPath + article.getId() + ".zip");
				String jyPath = request.getSession().getServletContext().getRealPath(fileZipPath);
				ZipUtil.unZip(localPath, jyPath + "/", article.getId());
				article.setSpare_3(zip.getOriginalFilename());
			}
			if ("error".equals(result)) {
			} else {
				articleService.save(article);
				if (dayNum > 0) {// 可推送
					// 推送
					String text = "标题：" + article.getTitle() + ",作者：" + article.getAuthor_name();
					// 安卓推送
					PushMsg pushMsg = new PushMsg(StringUtil.getUUIDString(), PushApi.dgbAndroidApiKey, new Date(),
							getUserSession().userId, "0", text);
					// ios推送
					PushMsg pushMsgIos = new PushMsg(StringUtil.getUUIDString(), PushApi.dgbIosApiKey, new Date(),
							getUserSession().userId, "0", text);
					try {
//						// ios android
//						PushApi.pussToAll(pushMsg);// 推送安卓平台
//						pushMsgIos.setMsg_text(text);
//						PushApi.push2All(text);// 推送ios平台
						//pushApiService.savePushMsg(pushMsg);

					} catch (Exception e) {
						logger.info("推送失败");
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();  
			return toEditcontent(request,article.getId(),"zx", "","500004",
					     "保存信息异常，请检查文件格式(附件只支持zip格式)，填写内容是否有误!"); 
		} 
		ModelAndView mv = new ModelAndView("forward:/view/articleFile/contentMag.jsp?ac=500003");// redirect模式
		return contentIndex("1", "10", "", "", "", "", "zx", "","");
	}

	// 删除文章
	@ResponseBody
	@RequestMapping("/removeArticle")
	public BaseJson removeArticle(HttpServletRequest request, String article_id) {
		String filePath = "";
		File file = null;
		if (StringUtil.isNull(article_id)) {
			return new BaseJson(600, "参数无效", "");
		} else {
			// 根据article_id获取文章对象
			Article article = new Article();

			article = articleService.findById(article_id);
			// 删除缩略图
			if (article != null && null != article.getSmall_pic_path() && !article.getSmall_pic_path().equals("")) {
				filePath = request.getSession().getServletContext().getRealPath(article.getSmall_pic_path());
				file = new File(filePath);
				if (file.exists())
					file.delete();
			}
			boolean flag = false;
			// 删除压缩包及解压文件夹
			String fileZipPath = BaseConstant.fileUploadPathZip + article.getId().substring(0, 6) + "/article/"
					+ article.getId();
			fileZipPath = request.getSession().getServletContext().getRealPath(fileZipPath);
			File fileZip = new File(fileZipPath);
			try {
				if (fileZip.exists()) {
					flag = deletefile(fileZipPath);
				}
			} catch (Exception e) {
				logger.info("文件夹删除失败");
				e.printStackTrace();
			}
			// 删除压缩包
			String oriZip = fileZipPath + ".zip";
			fileZip = new File(oriZip);
			if (fileZip.exists()) {
				flag = fileZip.delete();
			}
			// 删除从表相关信息
			articleReadCollectService.delReadByArticleId(article_id);
			articleService.delArticleById(article_id);
			return new BaseJson(200, "删除成功!", "");
		}

	}

	// 保存备份文件
	@ResponseBody
	@RequestMapping("/saveCopyFile")
	public BaseJson saveCopyFile(HttpServletResponse response, String filePath, String fileName) {
		response.setContentType("application/x-download");// 设置为下载application/x-
															// download
		String filenamedownload = filePath;// 即将下载的文件的相对路径
		String filenamedisplay = fileName;// 下载文件时显示的文件保存名称
		try {
			filenamedisplay = URLEncoder.encode(filenamedisplay, "UTF-8");
			response.addHeader("Content-Disposition", "attachment;filename=\"" + filenamedisplay);

			java.io.OutputStream os = response.getOutputStream();
			java.io.FileInputStream fis = new java.io.FileInputStream(filenamedownload);

			byte[] b = new byte[1024];
			int j = 0;
			while ((j = fis.read(b)) > 0) {
				os.write(b, 0, j);
			}

			fis.close();
			os.flush();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new BaseJson(200, "", "");
	}

	/**
	 * 
	 * <b>Summary: </b> copyFile 保存上传文件 String
	 * 
	 * @param path
	 * @param saveName
	 * @param file
	 * @return copyFile Dec 6, 2013
	 */
	private String copyFile(HttpServletRequest request, String realpath, String saveName, File file) {
		// 数据流方式上传文件
		FileOutputStream fos = null;
		FileInputStream fis = null;
		try {
			realpath = request.getServletContext().getRealPath(realpath);
			File dirFile = new File(realpath);
			if (!dirFile.isDirectory()) {// 目录月份目录不存在
				dirFile.mkdirs();// 创建目录
			}
			// 建立文件输出流
			File savefile = new File(new File(realpath), saveName);// 创建要保存到文件
			fos = new FileOutputStream(realpath + "/" + saveName);
			// 建立文件上传流
			fis = new FileInputStream(file);
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = fis.read(buffer)) > 0) {
				fos.write(buffer, 0, len);
			}
		} catch (Exception e) {
			System.out.println("文件上传失败");
			e.printStackTrace();
			return "error";
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					System.out.println("FileInputStream关闭失败");
					e.printStackTrace();
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					System.out.println("FileOutputStream关闭失败");
					e.printStackTrace();
				}
			}
		}
		return "success";
	}

	/**
	 * 删除某个文件夹下的所有文件夹和文件
	 * 
	 * @param delpath
	 *            String
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @return boolean
	 */
	public static boolean deletefile(String delpath) throws Exception {
		try {

			File file = new File(delpath);
			// 当且仅当此抽象路径名表示的文件存在且 是一个目录时，返回 true
			if (!file.isDirectory()) {
				file.delete();
			} else if (file.isDirectory()) {
				String[] filelist = file.list();
				for (int i = 0; i < filelist.length; i++) {
					File delfile = new File(delpath + "\\" + filelist[i]);
					if (!delfile.isDirectory()) {
						delfile.delete();
						System.out.println(delfile.getAbsolutePath() + "删除文件成功");
					} else if (delfile.isDirectory()) {
						deletefile(delpath + "\\" + filelist[i]);
					}
				}
				System.out.println(file.getAbsolutePath() + "删除成功");
				file.delete();
			}

		} catch (FileNotFoundException e) {
			System.out.println("deletefile() Exception:" + e.getMessage());
		}
		return true;
	}

	/**
	 * 
	 * <b>Summary: </b> readHtmlByNewsId 通过新闻ID读取文本内容 String
	 * 
	 * @return readHtmlByNewsId Dec 9, 2013
	 */
	private String readHtmlByNewsId(HttpServletRequest request, String fileName, String htmlPath) throws Exception {

		StringBuffer newContentStr = new StringBuffer();
		htmlPath = request.getSession().getServletContext().getRealPath(htmlPath);

		// 获得文件句柄
		File news_file = new File(htmlPath + "\\" + fileName);
		System.out.println(htmlPath + "\\" + fileName);
		// 判断文件是否存在
		if (news_file.isFile() && news_file.exists()) {
			BufferedReader buffreader = new BufferedReader(
					new InputStreamReader(new FileInputStream(news_file), "utf-8"));
			String lineTxt = null;
			while ((lineTxt = buffreader.readLine()) != null) {
				newContentStr.append(lineTxt);
			}
			buffreader.close();
		}
		return newContentStr.toString();
	}

	private String saveAsHtmlAndTxt(HttpServletRequest request, String newsContent, Article article, String news_id,
			String htmlPath) {
		String path = request.getContextPath();
		String jsPath = request.getServerName();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
				+ "/";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String realpath = request.getSession().getServletContext().getRealPath(htmlPath);
		File dirFile = new File(realpath);
		if (!dirFile.isDirectory()) {// 目录月份目录不存在
			dirFile.mkdirs();// 创建目录
		}

		// 生成静态网页 区分两种，第一种html展示 第二种增加单纯pdf展示
		StringBuffer newContentStr = new StringBuffer();
		newContentStr
				.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">")
				.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">").append("<head>")
				.append("<meta http-equiv=Content-Type content=\'text/html;charset=utf-8;\'/>")
				.append("<meta id='viewport' name='viewport' content='width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1;\'/>")
				.append("<script src='" + basePath + "js/jquery-1.10.2.min.js'></script>").append("<title>")
				.append(article.getTitle()).append("</title>").append("</head>").append("<style>")
				.append("img{width='100%';height=''}").append("</style>")
				.append("<body  style='padding-left:10px;padding-right:10px;>'")
				// .append("<div class=\"container\">")
				.append("<div class=\"T_mainc\">");
		// 拼接标题
		newContentStr
				.append("<span style=\"font-size:18.0pt;font-family:宋体;mso-font-kerning:0pt;mso-bidi-font-weight:bold\"><center>")
				.append(article.getTitle()).append("<h5 class=\"z_Tyxl_h3\">")
				.append(sdf.format(article.getCreate_date())).append("</h5></center>");
		// .append("<div class=\"T_Conter\" style=\"color: #000000;\">");
		newContentStr.append("</div>");
		// 拼接正文
		newContentStr.append("<div id='content'>");
		newContentStr.append(newsContent).append("</div>");
		// $('video').click(function(){
		newContentStr.append("<div class=\"clear\"></div>").append("</body>").append("<script type='text/javascript'>")
				.append("window.onload=function(){ ").append("$('video').click(function(){")
				.append("var src= $('video').attr('src');").append("window.client.openVideo(src);").append("} );")
				.append("var imgs=document.getElementsByTagName('img');").append("for(var i=0;i<imgs.length;i++){")
				.append("var imgTh=imgs[i];").append("imgTh.style.width='100%';").append("imgTh.style.height='100%';")
				.append("} ").append("} ")

				.append("</script>").append("</html>");
		byte[] buff = new byte[] {};
		String htmlName = news_id + ".html";
		String txtName = news_id + ".txt";
		FileOutputStream txtOutputStream = null;
		FileOutputStream outputStream = null;
		try {
			buff = newsContent.getBytes("UTF-8");
			txtOutputStream = new FileOutputStream(realpath + "/" + txtName);
			txtOutputStream.write(buff, 0, buff.length);
			buff = newContentStr.toString().getBytes("UTF-8");
			outputStream = new FileOutputStream(realpath + "/" + htmlName);
			outputStream.write(buff, 0, buff.length);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return "error";
		} catch (IOException e) {
			e.printStackTrace();
			return "error";
		} finally {
			if (txtOutputStream != null) {
				try {
					txtOutputStream.close();
				} catch (IOException e) {
					System.out.println("txtOutputStream关闭失败");
					e.printStackTrace();
				}
			}
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					System.out.println("outputStream关闭失败");
					e.printStackTrace();
				}
			}
		}
		return "success";
	}
}
