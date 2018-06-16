package com.winhands.cshj.article.action;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.winhands.base.util.BaseConstant;
import com.winhands.base.util.BaseJson;
import com.winhands.base.util.PageBt;
import com.winhands.base.util.PingYinUtil;
import com.winhands.base.util.StringUtil;
import com.winhands.base.web.BaseController;
import com.winhands.cshj.article.entity.Directory;
import com.winhands.cshj.article.service.DirectoryService;


@Controller
@RequestMapping("/directory")
public class DirectoryController extends BaseController{
	private final Logger logger = LoggerFactory.getLogger(DirectoryController.class); 
	@Autowired
	private DirectoryService directoryService;
	//
	@RequestMapping("/dirIndex")  
	public ModelAndView dirIndex(String pageNo,String pageSize,String searchId,String searchName,String super_dir_id,String type){
		 logger.info("进入目录管理首页{}");
		  
		 int pageSizeI = BaseConstant.pageSize;
		 int pageNoI = 1;
         try {
        	 pageNoI = Integer.parseInt(pageNo);
        	 pageSizeI = Integer.parseInt(pageSize);
         } catch (Exception e) {
        	 logger.error(e.getMessage());
         }
         Map<String,Object>  conditions = new HashMap<String,Object>();
         if(StringUtil.isNull(searchName)){
         }else{
        	 conditions.put("dir_name", searchName);
         }
         if(type.equals("0")){//作者
        	 if(!StringUtil.isNull(super_dir_id)){
    			 conditions.put("super_dir_id", "1001");
    			 
    		 }
         }else if(type.equals("1")){//行业
        	 if(!StringUtil.isNull(super_dir_id)){
    			 conditions.put("super_dir_id", "1002");
    		 }
         }
		
		 Page <Directory> page2=new Page<Directory>();
		 PageHelper.startPage(pageNoI,pageSizeI);
		 page2=(Page<Directory>) directoryService.findDirListByPIdWeb(conditions);
		 ModelAndView mv=null;
		 if(type.equals("0")){//作者  /authorIndex.jsp?ac=500001
			 mv = new ModelAndView("forward:/view/dir/authorIndex.jsp?ac=500001");//redirect模式  
         }else if(type.equals("1")){//行业
        	  mv = new ModelAndView("forward:/view/dir/industryIndex.jsp?ac=500002");//redirect模式  
         }
		 mv.addObject("pageList",page2.getResult());
		 mv.addObject("pageBt", page2PageBt(page2)); 
		 mv.addObject("searchId",searchId);
		 mv.addObject("searchName",searchName);
		 mv.addObject("type", type);
		 mv.addObject("super_dir_id", super_dir_id);
         return mv;
	}
	//客户端获取作者列表
	@ResponseBody
	@RequestMapping("/msQueryDirAuthorList")  
	public BaseJson msQueryDirAuthorList(String type){
		Map<String,Object>  conditions = new HashMap<String,Object>();
		PageBt pageBt =new PageBt();
        if(type.equals("0")){//作者
   			 conditions.put("super_dir_id", "1001");
        }else if(type.equals("1")){//行业
   			 conditions.put("super_dir_id", "1002");
        }
        List dirList=new ArrayList();
        dirList=directoryService.findDirListByPId(conditions);
		 return new BaseJson(200, "查询成功", "",dirList,pageBt);
	}
	@RequestMapping("/addDir")  
	public ModelAndView add(String type,String super_dir_id){
		 ModelAndView mv=null;
		 if(type.equals("0")){//作者
			 mv = new ModelAndView("forward:/view/dir/addAuthor.jsp?ac=500001");//redirect模式  
         }else if(type.equals("1")){//行业
        	  mv = new ModelAndView("forward:/view/dir/addIndustry.jsp?ac=500002");//redirect模式  
         }
		 mv.addObject("type", type);
		 mv.addObject("super_dir_id", super_dir_id);
         return mv;
	}
	@RequestMapping("/saveDir")  
	public ModelAndView save(Directory directory){
		 logger.info("进入目录管理保存{}"); 
		 String spare_1="";
		 if(directory==null){
		 }else{
				 directory.setDir_id(StringUtil.getUUIDString());
				 directory.setCreate_date(new Date());
				 directory.setCreate_user_id(getUserSession().userId);
				 directory.setCreate_user_name(getUserSession().userName);
				 
				 if("1".equals(directory.getType())){//行业
					 spare_1= PingYinUtil.getFullSpell(directory.getDir_name());
					 directory.setSpare_1(spare_1);
					 spare_1=PingYinUtil.getFirstSpell(directory.getDir_name());
					 directory.setSpare_2(spare_1);
					 directory.setSuper_dir_id("1002");
				 }else if("0".equals(directory.getType())){//作者
					 spare_1= PingYinUtil.getFullSpell(directory.getDir_name());
					 directory.setSpare_1(spare_1);
					 spare_1=PingYinUtil.getFirstSpell(directory.getDir_name());
					 directory.setSpare_2(spare_1);
					 directory.setSuper_dir_id("1001");
				 }
			 directoryService.save(directory);
		 }
			 return dirIndex("1","10","1001","",directory.getSuper_dir_id(),directory.getType());
	}
	//根据目录id获取目录信息跳转到修改页面
	@RequestMapping("/toEditDir")  
	public ModelAndView toEditDir(String dir_id,String type){
		Directory dir=directoryService.findByDirId(dir_id);
		 ModelAndView mv=null;
		 if(dir==null){
			 
		 }else{
			 if(type.equals("0")){//作者
				 mv = new ModelAndView("forward:/view/dir/editAuthor.jsp?ac=500001");//redirect模式  
	         }else if(type.equals("1")){//行业
	        	  mv = new ModelAndView("forward:/view/dir/editIndustry.jsp?ac=500002");//redirect模式  
	         }
		 }
		 
		 mv.addObject("directory", dir);
         return mv;
	}
	//保存修改信息
	@RequestMapping("/saveEditDir")  
	public ModelAndView saveEditDir(Directory directory){
		String spare_1="";
		if(directory==null){
			
		}else{
			 directory.setCreate_date(new Date());
			 directory.setCreate_user_id(getUserSession().userId);
			 directory.setCreate_user_name(getUserSession().userName);
			 spare_1= PingYinUtil.getFullSpell(directory.getDir_name());
			 directory.setSpare_1(spare_1);
			 spare_1=PingYinUtil.getFirstSpell(directory.getDir_name());
			 directory.setSpare_2(spare_1);
			directoryService.save(directory);
		}
        return dirIndex("1","10","1001","",directory.getSuper_dir_id(),directory.getType());
	}
	//修改有效无效状态
	@ResponseBody
	@RequestMapping("/changeStatus")  
	public BaseJson changeStatus(String dir_id,String status){
		if(StringUtil.isNull(dir_id)){
			return new BaseJson(600,"参数无效","");
		}else{
			Directory dir=directoryService.findByDirId(dir_id);
			dir.setIs_valid("1".equals(status)?"0":"1");
			dir.setCreate_date(new Date());
			dir.setCreate_user_id(getUserSession().userId);
			dir.setCreate_user_name(getUserSession().userName);
			directoryService.save(dir);
			return new BaseJson(1,"变更状态成功!","");
		}
		
	}
	//删除信息
	@ResponseBody
	@RequestMapping("/remove")  
	public BaseJson remove(String dir_id){
		
		if(StringUtil.isNull(dir_id)){ 
			return new BaseJson(600,"参数无效","");
		}else{
			//删除从表相关信息
			directoryService.delDirectoryByDirId(dir_id);
			return new BaseJson(1,"删除成功!","");
		}
		
	}
}
