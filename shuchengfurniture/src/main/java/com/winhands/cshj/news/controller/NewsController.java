package com.winhands.cshj.news.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import com.winhands.base.web.BaseController;
import com.winhands.cshj.news.entity.News;
import com.winhands.cshj.news.service.NewsService;

/** 
 * @author guojun
 */
@RequestMapping("/news")
@Controller
public class NewsController extends BaseController{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private NewsService newsService;
    /**
     * 查询轮播列表
     */
    @RequestMapping("/carouselIndex")
    public ModelAndView carouselIndex(String pageNo,String pageSize,News news,String ac){
    	if(StringUtil.isNull(pageNo)){
            pageNo = "1";
        }
        if(StringUtil.isNull(pageSize)){
            pageSize = "10";
        }
        PageHelper.startPage(Integer.parseInt(pageNo),Integer.parseInt(pageSize));
        Page<News> page = new Page<News>();
        try{
            page = (Page<News>)newsService.queryNewsList(news);
        }catch (Exception e){
            logger.error("查询轮播列表出错：",e);
        }
        ModelAndView mv = new ModelAndView("forward:/view/news/carouselIndex.jsp");
        mv.addObject("pagesBt",page2PageBt(page));
        mv.addObject("list",page.getResult());
        mv.addObject("news",news);
        mv.addObject("type",news.getType());
        mv.addObject("ac",ac);
        return mv;
    }
   
    //跳转到新增轮播页面
  	@RequestMapping("/addCarousel")  
  	public ModelAndView addCarousel(String ac,String type){
  		 ModelAndView mv=null;
  		 if(type.equals("0")){
  			mv= new ModelAndView("forward:/view/news/carouselAdd.jsp");
  		 }else if(type.equals("1")){
   			mv= new ModelAndView("forward:/view/news/infoAdd.jsp");
   		 }
  		 mv.addObject("ac", ac);
  		 mv.addObject("type", type);
  		 return mv;
  	}
  //跳转到修改页面
  	@RequestMapping("/toEditNews")  
  	public ModelAndView toEditNews(HttpServletRequest request,String ac,String id,String type){
  		News news = new News();
  		String newsContent = "";
  		try {
			if(!StringUtil.isNull(id)){
				news = newsService.queryCarouselById(id);
				newsContent = readHtmlByNewsId(request,news.getId()+".txt",news.getNewsUrl());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
  		 ModelAndView mv=null;
  		 if("0".equals(type)){
  			mv= new ModelAndView("forward:/view/news/carouselEdit.jsp");
  		 }else if(type.equals("1")){
   			mv= new ModelAndView("forward:/view/news/infoEdit.jsp");
   		 }
  		 mv.addObject("ac", ac);
  		 mv.addObject("news", news);
  		 mv.addObject("type", type);
  		 mv.addObject("newsContent", newsContent);
  		 return mv;
  	}
  //保存修改信息
  	@RequestMapping("/saveEditNews")  
  	public ModelAndView saveEditNews(HttpServletRequest request,String ac,News news,MultipartFile pic, String newsContent,String lbType){
    	String result = "";
    	String fileName = "";
    	String htmlPath = "";
    	ModelAndView mv = null;
    	String editFileName = StringUtil.getUUIDString();
 	   
 	    String filePath= BaseConstant.fileUploadCarouselPath + DateUtil.getCurrentMonth() + "/image/";
 	    if("0".equals(lbType)){
 	    	htmlPath = BaseConstant.fileUploadCarouselPath + DateUtil.getCurrentMonth() + "/html/";
 		 }else if("1".equals(lbType)){
 			htmlPath = BaseConstant.fileUploadInfoPath + DateUtil.getCurrentMonth() + "/html/";
  		 }
 	   
        try {
			if(news!=null){ 
			   News oldNews = newsService.queryCarouselById(news.getId());
			   // 保存缩略图
			   if("0".equals(lbType)){
			   if (pic != null && pic.getSize() != 0) { 
				   fileName = pic.getOriginalFilename();
				   String filetype = pic.getOriginalFilename().substring(fileName.indexOf(".") + 1, fileName.length()); 
				   if(!StringUtil.isNull(oldNews.getImgUrl())){
					 deletefile(request.getServletContext().getRealPath(oldNews.getImgUrl()));
				   }
					CommonsMultipartFile cf = (CommonsMultipartFile) pic;
					DiskFileItem fi = (DiskFileItem) cf.getFileItem(); 
					File file = fi.getStoreLocation();  
					result = copyFile(request, filePath, editFileName + "." + filetype, file);
					if(!"success".equals(result)){
			   		 mv = new ModelAndView("forward:/view/news/corouselEdit.jsp");  
			         mv.addObject("news",news); 
			         mv.addObject("type",lbType);
			         mv.addObject("ac",ac);
			         mv.addObject("msg","保存异常!");
			         return mv;
			   	   }
				news.setImgUrl(filePath+editFileName + "." + filetype);
			   }else{
				   news.setImgUrl(oldNews.getImgUrl());
			   }
			   }
			   File htmlFile = new File(request.getServletContext().getRealPath(oldNews.getNewsUrl()+"/"+oldNews.getId()+".html"));
			   if(htmlFile.exists()){
				   deletefile(request.getServletContext().getRealPath(oldNews.getNewsUrl()+"/"+oldNews.getId()+".html"));
			   }
			   news.setPublishTime(new Date());
			   result = saveAsHtmlAndTxt(request, newsContent, news, news.getId().toString(), htmlPath); 
			   if(!"success".equals(result)){
			   	 if("0".equals(lbType)){
					mv= new ModelAndView("forward:/view/news/carouselEdit.jsp");
				 }else if(lbType.equals("1")){
					mv= new ModelAndView("forward:/view/news/infoEdit.jsp");
				 }
			     mv.addObject("news",news); 
			     mv.addObject("type",lbType);
			     mv.addObject("ac",ac);
			     mv.addObject("msg","保存异常!");
			     return mv;
			   }
			   news.setNewsUrl(htmlPath);
			   newsService.updateNews(news);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
        news.setType(lbType);
        return carouselIndex("1", "10", news, ac);
  	}
	//删除信息
	@ResponseBody
	@RequestMapping("/remove")  
	public BaseJson remove(HttpServletRequest request,String news_id){
		String realpath ="";
		File news_file;
		try {
			if(StringUtil.isNull(news_id)){ 
				return new BaseJson(600,"参数无效","");
			}else{
				News oldNews = newsService.queryCarouselById(news_id);
				//删除图片信息
				if(!StringUtil.isNull(oldNews.getImgUrl())){
					 deletefile(request.getServletContext().getRealPath(oldNews.getImgUrl()));
				}
				//删txt文件         BaseConstant.fileUploadCarouselPath + DateUtil.getCurrentMonth() + "/html/"
				realpath = request.getSession().getServletContext().getRealPath(BaseConstant.fileUploadCarouselPath+oldNews.getId().substring(0, 6)+"/"+oldNews.getId()+".txt");
				news_file = new File(realpath);
				if(news_file.exists()) news_file.delete();
				//删除html文件
				realpath = request.getSession().getServletContext().getRealPath(oldNews.getNewsUrl());
				news_file = new File(realpath);
				if(news_file.exists()) news_file.delete();
				newsService.deleteNewsById(news_id);
				return new BaseJson(1,"删除成功!","");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new BaseJson(600,"参数无效","");
		}
		
	}
  	@RequestMapping("/saveCarousel")
     public  ModelAndView saveCarousel(HttpServletRequest request,String ac,News news,MultipartFile pic, String newsContent){ 
       String result = "";
 	   String fileName = pic.getOriginalFilename();
 	   String filePath = BaseConstant.fileUploadCarouselPath + DateUtil.getCurrentMonth() + "/image/";
 	   String type = pic.getOriginalFilename().substring(fileName.indexOf(".") + 1, fileName.length()); 
 	   String htmlPath = BaseConstant.fileUploadCarouselPath + DateUtil.getCurrentMonth() + "/html/";
        if(news!=null){ 
     	   news.setId(StringUtil.getUUIDString());
     	   news.setCreateUserName(getUserSession().userNameCn);
     	   news.setPublishTime(new Date());
     	   news.setType("0");
 	       // 保存缩略图
 	   	   if (pic != null && pic.getSize() != 0) { 
 	   			CommonsMultipartFile cf = (CommonsMultipartFile) pic;
 	   			DiskFileItem fi = (DiskFileItem) cf.getFileItem(); 
 	   			File file = fi.getStoreLocation();  
 	   			result = copyFile(request, filePath, news.getId() + "." + type, file);
 	   			if(!"success".equals(result)){
 	   	   		 ModelAndView mv = new ModelAndView("forward:/view/news/corouselAdd.jsp");  
 	   	         mv.addObject("news",news); 
 	   	         mv.addObject("msg","保存异常!");
 	   	         return mv;
 	   	   	   }
 	   	   } 
 	   	   news.setImgUrl(filePath+news.getId() + "." + type);
 	   	   result = saveAsHtmlAndTxt(request, newsContent, news, news.getId().toString(), htmlPath);
 	   	  if(!"success".equals(result)){
 	   		 ModelAndView mv = new ModelAndView("forward:/view/news/corouselAdd.jsp");  
 	         mv.addObject("news",news); 
 	         mv.addObject("msg","保存异常!");
 	         return mv;
 	   	   }
 	   	   news.setNewsUrl(htmlPath);
 	   	   try {
			newsService.saveNews(news);
		} catch (Exception e) {
			e.printStackTrace();
		}
        } 
        
        return carouselIndex("1", "10", news, ac);
     }
  	
  	
  	@RequestMapping("/saveInfo")
    public  ModelAndView saveInfo(HttpServletRequest request,String ac,News news,String newsContent){ 
      String result = "";
	   String htmlPath = BaseConstant.fileUploadInfoPath + DateUtil.getCurrentMonth() + "/html/";
       if(news!=null){ 
    	   news.setId(StringUtil.getUUIDString());
    	   news.setCreateUserName(getUserSession().userNameCn);
    	   news.setType("1");//1:资讯
    	   news.setPublishTime(new Date());
	   	   result = saveAsHtmlAndTxt(request, newsContent, news, news.getId().toString(), htmlPath);
	   	  if(!"success".equals(result)){
	   		 ModelAndView mv = new ModelAndView("forward:/view/news/infoAdd.jsp");  
	         mv.addObject("news",news); 
	         mv.addObject("msg","保存异常!");
	         return mv;
	   	   }
	   	   news.setNewsUrl(htmlPath);
	   	   try {
			newsService.saveNews(news);
		} catch (Exception e) {
			e.printStackTrace();
		}
       } 
       News temp = new News();
       temp.setType("1");
       return carouselIndex("1", "10", temp, ac);
    } 
  	 /******************************************************************/
  	 /**
      * 客户端获取轮播列表
      */
     @ResponseBody
     @RequestMapping("/msCarouselList")
     public BaseJson msCarouselList(News news){
         BaseJson json = new BaseJson();
         List corouslList=new ArrayList();
         try {
        	 if(news==null){
        		 news = new News(); 
        	 }
        	 news.setIsOnline("1");//1  上线
        	 //news.setType("0");//0:轮播
        	 corouslList = newsService.queryCarouselList(news);
        	 if(corouslList==null||corouslList.size()==0){
        		 
        	 }else{
        		 for(int i=0;i<corouslList.size();i++){
        			 News temp = (News) corouslList.get(i);
        			 temp.setNewsUrl(temp.getNewsUrl()+"/"+temp.getId()+".html");
        		 }
        	 }
             json.setStatus(200);
             json.setJson(corouslList);
             
         }catch (Exception e){
             logger.error("获取轮播出错：",e);
             json.setStatus(500);
             json.setMessage("系统错误，请重新操作！");
         }
         return json;
     }
     /**
      * 客户端获取资讯列表
      */
     @ResponseBody
     @RequestMapping("/msInfoList")
     public BaseJson msInfoList(String pageNo,String pageSize,News news){
    	 BaseJson json = new BaseJson();
    	 List infoList=new ArrayList();
    	 if(StringUtil.isNull(pageNo)){
             pageNo = "1";
         }
         if(StringUtil.isNull(pageSize)){
             pageSize = "10";
         }
         PageHelper.startPage(Integer.parseInt(pageNo),Integer.parseInt(pageSize));
         Page<News> page = new Page<News>();
         
         try{
        	 if(news==null){
        		 news = new News(); 
        	 }
        	 news.setIsOnline("1");//1  上线
        	 news.setType("1");
             page = (Page<News>)newsService.queryNewsList(news);
             infoList = page.getResult();
             if(infoList==null||infoList.size()==0){
        		 
        	 }else{
        		 for(int i=0;i<infoList.size();i++){
        			 News temp = (News) infoList.get(i);
        			 temp.setNewsUrl(temp.getNewsUrl()+"/"+temp.getId()+".html");
        		 }
        	 }
             json.setStatus(200);
             json.setPageBt(page2PageBt(page));
             json.setJson(page);
         }catch (Exception e){
        	 logger.error("获取轮播出错：",e);
             json.setStatus(500);
             json.setMessage("系统错误，请重新操作！");
         }
         return json;
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
 			realpath = request.getSession().getServletContext().getRealPath(realpath);
 			logger.debug("上传路径.."+realpath);
 			File dirFile = new File(realpath);
 			if (!dirFile.isDirectory()) {// 目录月份目录不存在
 				dirFile.mkdirs();// 创建目录
 			}
 			// 建立文件输出流 
 			fos = new FileOutputStream(realpath + "/" + saveName);
 			// 建立文件上传流
 			fis = new FileInputStream(file);
 			byte[] buffer = new byte[1024];
 			int len = 0;
 			while ((len = fis.read(buffer)) > 0) {
 				fos.write(buffer, 0, len);
 			}
 		} catch (Exception e) {
 			logger.error("文件上传失败");
 			e.printStackTrace();
 			return "error";
 		} finally {
 			if (fis != null) {
 				try {
 					fis.close();
 				} catch (IOException e) {
 					logger.error("FileInputStream关闭失败");
 					e.printStackTrace();
 				}
 			}
 			if (fos != null) {
 				try {
 					fos.close();
 				} catch (IOException e) {
 					logger.error("FileOutputStream关闭失败");
 					e.printStackTrace();
 				}
 			}
 		}
 		return "success";
 	}
 	private String readHtmlByNewsId(HttpServletRequest request, String fileName, String htmlPath) throws Exception { 
		StringBuffer newContentStr = new StringBuffer(); 
		htmlPath = request.getSession().getServletContext().getRealPath(htmlPath);
		logger.debug("readHtmlByNewsId上传路径.."+htmlPath + "/" + fileName);
		// 获得文件句柄
		File news_file = new File(htmlPath + "/" + fileName);
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
	/**
	 * 删除某个文件夹下的所有文件夹和文件
	 * 
	 * @param delpath
	 *            String
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @return boolean
	 */
	private   boolean deletefile(String delpath) throws Exception {
		try {   
			logger.debug("deletefile路径.."+delpath);
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
						logger.info(delfile.getAbsolutePath() + "删除文件成功");
					} else if (delfile.isDirectory()) {
						deletefile(delpath + "\\" + filelist[i]);
					}
				}
				logger.info(file.getAbsolutePath() + "删除成功"); 
				file.delete();
			}

		} catch (FileNotFoundException e) {
			logger.error("deletefile() Exception:" + e.getMessage());
		}
		return true;
	}
 	/**
     * 保存文档
     * @param request
     * @param newsContent
     * @param news
     * @param news_id
     * @param htmlPath
     * @return
     */
	private String saveAsHtmlAndTxt(HttpServletRequest request, String newsContent, News news, String news_id,
			String htmlPath) { 
		String realpath = htmlPath;
		FileOutputStream txtOutputStream = null;
		realpath = request.getServletContext().getRealPath(htmlPath);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		logger.debug("saveAsHtmlAndTxt上传路径.."+realpath);
		File dirFile = new File(realpath);
		if (!dirFile.isDirectory()) {// 目录月份目录不存在
			dirFile.mkdirs();// 创建目录
		}
		byte[] buff = new byte[] {};
		String htmlName = news_id + ".html"; 
		String txtName = news_id + ".txt";
		FileOutputStream outputStream = null;
		// 生成静态网页 区分两种，第一种html展示 第二种增加单纯pdf展示
		StringBuffer newContentStr = new StringBuffer();
		newContentStr.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">")
					 .append("<html xmlns=\"http://www.w3.org/1999/xhtml\">").append("<head>")
					 .append("<meta http-equiv=Content-Type content=\'text/html;charset=utf-8;\'/>")
					 .append("<meta id='viewport' name='viewport' content='width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1;\'/>")
					 .append("<title>")
					 .append(news.getTitle()).append("</title>").append("</head>").append("<style>")
				     .append("img{width='100%';height=''}").append("</style>")
					 .append("<body  style='padding-left:10px;padding-right:10px;>'");
				// 拼接正文
				newContentStr.append("<div id='content'>");
				newContentStr.append(newsContent).append("</div>");
				// $('video').click(function(){
				newContentStr.append("<div class=\"clear\"></div>").append("</body>").append("<script type='text/javascript'>")
						.append("window.onload=function(){ ")
						.append("var imgs=document.getElementsByTagName('img');").append("for(var i=0;i<imgs.length;i++){")
						.append("var imgTh=imgs[i];").append("imgTh.style.width='100%';").append("imgTh.style.height='100%';")
						.append("} ").append("} ")

						.append("</script>").append("</html>");
		try { 
			buff = newContentStr.toString().getBytes("UTF-8");
			outputStream = new FileOutputStream(realpath + "/" + htmlName);
			outputStream.write(buff, 0, buff.length);
			buff = newsContent.getBytes("UTF-8");
			txtOutputStream = new FileOutputStream(realpath + "/" + txtName);
			txtOutputStream.write(buff, 0, buff.length);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return "error";
		} catch (IOException e) {
			e.printStackTrace();
			return "error";
		} finally { 
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					logger.error("outputStream关闭失败");
					e.printStackTrace();
				}
			}
		}
		return "success";
	}
}
