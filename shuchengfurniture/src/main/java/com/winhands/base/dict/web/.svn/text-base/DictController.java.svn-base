package com.winhands.base.dict.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.winhands.base.dict.entity.Dict;
import com.winhands.base.dict.entity.DictRel;
import com.winhands.base.dict.service.DictService;
import com.winhands.base.util.BaseConstant;
import com.winhands.base.util.BaseJson;
import com.winhands.base.util.StringUtil;
import com.winhands.base.web.BaseController;
import com.winhands.base.web.entity.Tree;

@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("/dict")
public class DictController extends BaseController{
	private final Logger logger = LoggerFactory.getLogger(DictController.class);
	@Autowired
	private DictService dictService; 
	
	
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/index")  
	public ModelAndView index(String pageNo,String pageSize,String searchId,String searchName){
		 logger.info("进入字典管理首页pageNo:{},pageSize:{}",pageNo,pageSize);
		 int pageSizeI = BaseConstant.pageSize;
		 int pageNoI = 1;
         try {
        	 pageNoI = Integer.parseInt(pageNo);
        	 pageSizeI = Integer.parseInt(pageSize);
         } catch (Exception e) {
        	 logger.error(e.getMessage());
         }
		 Map<String,Object>  dictMap = new HashMap<String,Object>();
		 if(!StringUtil.isNull(searchId)){
			 dictMap.put("EQ_superDictId", searchId);
		 }
		 if(!StringUtil.isNull(searchName)){
			 dictMap.put("LIKE_dictName", searchName);
		 }
		 dictMap.put("NE_id", "4767492360897248315");
		 Page<Dict> page = dictService.findDictList(pageNoI,pageSizeI,dictMap,"orderId");
		 List<Dict> list = dictService.findAll(new Dict()); 
		 List<Tree> treeList = new ArrayList<Tree>(); 
		 for(Dict dicts:list){
			 Tree tree = new Tree();
			 tree.setId(dicts.getId());
			 tree.setName(dicts.getDictName());
			 tree.setpId(dicts.getSuperDictId());
			 tree.setUrl("../dict/index?ac=100011&searchId="+dicts.getId());
			 tree.setTarget("_self");
			 tree.setClick("");
			 tree.setChecked(dicts.getId().equals(searchId)?true:false);
			 tree.setOpen(dicts.getId().equals(searchId)?true:false);
			 treeList.add(tree);
		 }
		 //转换成json
		 String json =  JSON.toJSONString(treeList).replace("\"checked\"", "checked").replace("\"open\"", "open");
		 ModelAndView mv = new ModelAndView("forward:/view/dict/dictIndex.jsp");//redirect模式  
		 mv.addObject("treejson", json); 
		 mv.addObject("pageList",page.getContent());
		 mv.addObject("pageBt", page2PageBt(page)); 
		 mv.addObject("searchId",searchId);
         return mv;
	} 
	
	@RequestMapping("/add")  
	public ModelAndView add(String dictId){
		 dictId = StringUtil.isNull(dictId)?"1":dictId;
		 List<Dict> list = dictService.findAll(new Dict()); 
		 List<Tree> treeList = new ArrayList<Tree>(); 
		 for(Dict dicts:list){
			 Tree tree = new Tree();
			 tree.setId(dicts.getId());
			 tree.setName(dicts.getDictName());
			 tree.setpId(dicts.getSuperDictId());
//			 tree.setUrl("../dict/index?ac=100011&dictId="+dicts.getId().getDictId());
			 tree.setTarget("_self");
			 tree.setClick("");
			 tree.setChecked(dicts.getId().equals(dictId)?true:false);
			 tree.setOpen(dicts.getId().equals(dictId)?true:false);
			 treeList.add(tree);
		 }  
		 //转换成json
		 String json =  JSON.toJSONString(treeList).replace("\"checked\"", "checked").replace("\"open\"", "open");
		 
		 ModelAndView mv = new ModelAndView("forward:/view/dict/addDict.jsp");//redirect模式   
		 mv.addObject("treejson", json);  
		 mv.addObject("searchId", dictId); 
         return mv;
	}
	
	@RequestMapping("/save")  
	public ModelAndView save(Dict dict){
		 logger.info("进入字典管理保存{}"); 
		 Dict dictT = dictService.findDictByDictIdAndSuperDictId(dict.getDictId(), dict.getSuperDictId());
		 if(dictT==null){
			 dict.setId(StringUtil.getUUIDString());
			 dict.setDictGrade("2");
			 dict.setCreateDate(new Date());
			 dict.setCreatePeople(getUserSession().userId);
			 dictService.save(dict); 
			 return index("1","10",dict.getSuperDictId(),"");
		 }else{
			 List<Dict> list = dictService.findAll(new Dict()); 
			 List<Tree> treeList = new ArrayList<Tree>(); 
			 for(Dict dicts:list){
				 Tree tree = new Tree();
				 tree.setId(dicts.getId());
				 tree.setName(dicts.getDictName());
				 tree.setpId(dicts.getSuperDictId());
//				 tree.setUrl("../dict/index?ac=100011&dictId="+dicts.getId().getDictId());
				 tree.setTarget("_self");
				 tree.setClick("");
				 tree.setChecked(dicts.getId().equals(dict.getSuperDictId())?true:false);
				 tree.setOpen(dicts.getId().equals(dict.getSuperDictId())?true:false);
				 treeList.add(tree);
			 }  
			 //转换成json
			 String json =  JSON.toJSONString(treeList).replace("\"checked\"", "checked").replace("\"open\"", "open");
			 ModelAndView mv = new ModelAndView("forward:/view/dict/addDict.jsp");//redirect模式   
			 mv.addObject("treejson", json); 
			 mv.addObject("dict", dict);
			 mv.addObject("msg","id重复，请确认!");
			 return mv;
		 }  
	}
	@ResponseBody
	@RequestMapping("/changeStatus")  
	public BaseJson changeStatus(String id,String status){
		if(StringUtil.isNull(id)){
			return new BaseJson(600,"参数无效","");
		}else{
			Dict dict = dictService.findById(id);
			dict.setIsInvalid("1".equals(status)?"0":"1");
			dict.setCreateDate(new Date());
			dict.setCreatePeople(getUserSession().userId);
			dictService.save(dict);
			return new BaseJson(1,"变更状态成功!","");
		}
		
	}
	 
	@RequestMapping("/edit")  
	public ModelAndView edit(String pageNo,String pageSize,String id,String searchId,String searchName){
		  Dict dict = dictService.findById(id); 
		  List<Dict> list = dictService.findAll(new Dict()); 
		  List<Tree> treeList = new ArrayList<Tree>(); 
		  for(Dict dicts:list){
				 Tree tree = new Tree();
				 tree.setId(dicts.getId());
				 tree.setName(dicts.getDictName());
				 tree.setpId(dicts.getSuperDictId());
//				 tree.setUrl("../dict/index?ac=100011&dictId="+dicts.getId().getDictId());
				 tree.setTarget("_self");
				 tree.setClick("");
				 tree.setChecked(dicts.getId().equals(dict.getSuperDictId())?true:false);
				 tree.setOpen(dicts.getId().equals(dict.getSuperDictId())?true:false);
				 treeList.add(tree);
		  }  
		  //转换成json
		  String json =  JSON.toJSONString(treeList).replace("\"checked\"", "checked").replace("\"open\"", "open");	  
		  ModelAndView mv = new ModelAndView("forward:/view/dict/editDict.jsp");//redirect模式   
		  mv.addObject("treejson", json); 
		  mv.addObject("dict", dict); 
		  mv.addObject("pageNo", pageNo); 
		  mv.addObject("pageSize", pageSize); 
		  mv.addObject("searchId", searchId); 
		  mv.addObject("searchName", searchName); 
		  return mv;
	}
	
	@RequestMapping("/update")  
	public ModelAndView update(Dict dict,String pageNo,String pageSize,String id,String searchId,String searchName){ 
		 dict.setDictGrade("2");
		 dict.setCreateDate(new Date());
		 dict.setCreatePeople(getUserSession().userId);
		 dictService.save(dict);   
		 return index(pageNo, pageSize, searchId, searchName);
	}
	//现场指导检查项目
	@ResponseBody
	@RequestMapping("/itemList")  
	public BaseJson itemList(){
		final List itemList=new ArrayList();
		Dict dict=new Dict();
		DictRel relDict=new DictRel();
		String[]itemIdstrs={"6457406921995103794","6085390042759084549","9026012284816278262","5872302039531584326","5363934687284663153","5089101229307282646"};
		for(int i=0;i<itemIdstrs.length;i++){
			relDict=queryDictById(itemIdstrs[i]);
			itemList.add(relDict);
		}
		 return new BaseJson(1,"查询成功","",itemList);
		
	}
	private DictRel queryDictById(String id){
		DictRel tempDict=new DictRel();
		Dict dict=dictService.findById(id);
		List dictList=new ArrayList();
		tempDict.setpId(dict.getId());
		tempDict.setpName(dict.getDictName());
		dictList=dictService.findDictListBySuperDictId(id);
		if(dictList!=null||dictList.size()==0){
			tempDict.setDictList(dictList);
		}
		return tempDict;
	}
	//现场指导检查项目 7638049855377590076
		@ResponseBody
		@RequestMapping("/partyTypeList")  
		public BaseJson partyTypeList(){
			List partyTypeList=new ArrayList();
			String id="7638049855377590076";
			partyTypeList=dictService.findDictListBySuperDictId(id);
			 return new BaseJson(1,"查询成功","",partyTypeList);
			
		}
	
}
