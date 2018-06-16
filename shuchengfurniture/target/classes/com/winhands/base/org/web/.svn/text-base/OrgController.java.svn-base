package com.winhands.base.org.web;

import java.util.ArrayList;
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
import com.winhands.base.dict.service.DictService;
import com.winhands.base.org.entity.Org;
import com.winhands.base.org.service.OrgService;
import com.winhands.base.user.entity.User;
import com.winhands.base.user.service.UserService;
import com.winhands.base.util.BaseConstant;
import com.winhands.base.util.BaseJson;
import com.winhands.base.util.DateUtil;
import com.winhands.base.util.StringUtil;
import com.winhands.base.web.BaseController;
import com.winhands.base.web.entity.Tree;
import com.winhands.cshj.member.entity.Member;
import com.winhands.cshj.member.service.MemberService;

@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("/org")
public class OrgController extends BaseController{
	private final Logger logger = LoggerFactory.getLogger(OrgController.class);
	@Autowired
	private OrgService orgService;
	@Autowired
	private DictService dictService;
	@Autowired
	private UserService userService;
	@Autowired
	private MemberService memberService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/index")  
	public ModelAndView index(String pageNo,String pageSize,String searchId,String searchName){
		 logger.info("进入机构管理首页pageNo:{},pageSize:{}",pageNo,pageSize);
		 int pageSizeI = BaseConstant.pageSize;
		 int pageNoI = 1;
         try {
        	 pageNoI = Integer.parseInt(pageNo);
        	 pageSizeI = Integer.parseInt(pageSize);
         } catch (Exception e) {
        	 logger.error(e.getMessage());
         }
		 Map<String,Object>  orgMap = new HashMap<String,Object>();
		 if(!StringUtil.isNull(searchId)){
			 orgMap.put("EQ_parentOrgId", searchId);
		 }
		 if(!StringUtil.isNull(searchName)){
			 orgMap.put("LIKE_orgName", searchName);
		 }
		 orgMap.put("NE_parentOrgId", "4767492360897248315");
		 Page<Org> page = orgService.findOrgList(pageNoI,pageSizeI,orgMap,"orderId");
		 List<Org> list = orgService.findAll(); 
		 
		 //机构单位
		 Dict dict = new Dict();
		 dict.setSuperDictId("5784926795765294658");
		 List<Dict> dictlist = dictService.findAll(dict);
		 
		 //转换成json
		 String json = list2TreeJson(list,searchId,"",true,true);  
		 
		 ModelAndView mv = new ModelAndView("forward:/view/org/orglist.jsp");//redirect模式  
		 mv.addObject("dictlist", dictlist);
		 mv.addObject("treejson", json); 
		 mv.addObject("pageList",page.getContent());
		 mv.addObject("pageBt", page2PageBt(page)); 
		 mv.addObject("searchId",searchId);
         return mv;
	} 
	
	@RequestMapping("/add")  
	public ModelAndView add(String orgId){
		 orgId = StringUtil.isNull(orgId)?"1":orgId;
		 List<Org> list = orgService.findAll(); 
		 //转换成json
		 String json = list2TreeJson(list,orgId,"",false,true);  
		 //机构单位类型
		 Dict dict = new Dict();
		 dict.setSuperDictId("5784926795765294658");
		 List<Dict> dictlist = dictService.findAll(dict);
		 ModelAndView mv = new ModelAndView("forward:/view/org/addOrg.jsp");//redirect模式   
		 mv.addObject("treejson", json);  
		 mv.addObject("searchId", orgId);
		 mv.addObject("dictlist", dictlist);
         return mv;
	}
	
	@RequestMapping("/save")  
	public ModelAndView save(Org org){ 
		 logger.info("进入组织机构管理保存{}"); 
		 String org_name=org.getOrgName().replace(",","");
		 org.setOrgName(org_name);
		 org.setOrgId(StringUtil.getUUIDString()); 
		 org.setCreateDate(DateUtil.getCurrentDate());
		 org.setCreateUser(getUserSession().userId);
		 orgService.save(org);   
		 return index("1","10",org.getParentOrgId(),""); 
	}
	@ResponseBody
	@RequestMapping("/remove")  
	public BaseJson remove(String id,String status){
		try {
			if(StringUtil.isNull(id)){ 
				return new BaseJson(600,"参数无效","");
			}else{
				//删除之前要判断1.没有子单位，2.单位下没有人员
				List<Org> tempOrgList = orgService.findOrgByPId(id); 
				if(tempOrgList!=null&&tempOrgList.size()>0){ 
					return new BaseJson(600,"请先删除下属机构!","");
				}
				
				Map<String,Object>  userMap = new HashMap<String,Object>();
				userMap.put("orgId", id);
				List<User> tempUserList = userService.findUserList(userMap);
				Member tempMember = new Member();
				tempMember.setIsVaild("1");
				tempMember.setSpare1(id);
				List<Member> tempMemberList = memberService.queryMemberList(tempMember);
				
				if(tempUserList!=null&&tempUserList.size()>0&&tempMemberList!=null&&tempMemberList.size()>0){ 
					return new BaseJson(600,"请先删除下属人员!","");
				}
				orgService.deleteByOrgId(id); 
				return new BaseJson(1,"删除成功!",""); 
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new BaseJson(600,"删除失败!",""); 
		}
		
	}
	 
	@RequestMapping("/edit")  
	public ModelAndView edit(String pageNo,String pageSize,String id,String searchId,String searchName){
		  Org org = orgService.findByOrgId(id);
		  List<Org> list = orgService.findAll(); 
		  String json = list2TreeJson(list,org.getParentOrgId(),id,false,false);  
		  //机构单位类型
		  Dict dict = new Dict();
		  dict.setSuperDictId("5784926795765294658");
		  List<Dict> dictlist = dictService.findAll(dict);
		  ModelAndView mv = new ModelAndView("forward:/view/org/editOrg.jsp");//redirect模式   
		  mv.addObject("treejson", json); 
		  mv.addObject("dictlist", dictlist);
		  mv.addObject("org", org); 
		  mv.addObject("pageNo", pageNo); 
		  mv.addObject("pageSize", pageSize); 
		  mv.addObject("searchId", searchId); 
		  mv.addObject("searchName", searchName); 
		  return mv;
	}
	
	@RequestMapping("/update")  
	public ModelAndView update(Org org,String pageNo,String pageSize,String id,String searchId,String searchName){ 
		String [] org_names=org.getOrgName().split(",");
		org.setOrgName(org_names[0]);
		 orgService.save(org);   
		 return index(pageNo, pageSize, searchId, searchName);
	}
	@ResponseBody
	@RequestMapping("/msQueryOrg") 
	public BaseJson msQueryOrg(){
		 Map<String,Object>  orgMap = new HashMap<String,Object>();
		 List<Org> list=new ArrayList<Org>();
		 list= orgService.queryOrgListType2(orgMap);
		
		 return new BaseJson(200,"查询成功!","",list); 
	}
	//list json
	private String  list2TreeJson(List<Org> orgList,String searchId,String orgId,boolean urlUse,boolean selfChecked){
		List<Tree> treeList = new ArrayList<Tree>(); 
		for(Org org:orgList){
			 Tree tree = new Tree();
			 tree.setId(org.getOrgId());
			 tree.setName(org.getOrgName());
			 tree.setpId(org.getParentOrgId());
			 if(urlUse){
				 tree.setUrl("../org/index?ac=200020&searchId="+org.getOrgId());
			 } 
			 if(!selfChecked){
				 if(org.getOrgId().equals(orgId)){ 
				    tree.setChkDisabled(selfChecked);	 
				 }
			 }
			 tree.setTarget("_self");
			 tree.setClick("");
			 tree.setChecked(org.getOrgId().equals(searchId)?true:false);
			 tree.setOpen(org.getOrgId().equals(searchId)?true:false);
			 treeList.add(tree);
	    } 
	    //转换成json
		return  JSON.toJSONString(treeList).replace("\"checked\"", "checked").replace("\"open\"", "open");
	}
}
