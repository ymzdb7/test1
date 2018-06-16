package com.winhands.cshj.emp.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.winhands.base.org.entity.Org;
import com.winhands.base.org.service.OrgService;
import com.winhands.base.shiro.BaseRealm;
import com.winhands.base.user.entity.User;
import com.winhands.base.user.service.UserService;
import com.winhands.base.util.BaseConstant;
import com.winhands.base.util.BaseJson;
import com.winhands.base.util.Digests;
import com.winhands.base.util.Encodes;
import com.winhands.base.util.StringUtil;
import com.winhands.base.web.BaseController;
import com.winhands.base.web.entity.Tree;
import com.winhands.cshj.emp.entity.Emp;
import com.winhands.cshj.emp.service.EmpService;

@Controller
@RequestMapping("/emp")
public class EmpController extends BaseController{
	private final Logger logger = LoggerFactory.getLogger(EmpController.class); 
	@Autowired
	private EmpService empService;
	@Autowired
	private OrgService orgService;
	@Autowired
	private UserService userService; 
	//人员信息列表
	@RequestMapping("/empIndex")  
	public ModelAndView empIndex(String pageNo,String pageSize,String searchId,String searchName){
		 logger.info("进入雇佣人员管理首页{}");
		 int pageSizeI = BaseConstant.pageSize;
		 int pageNoI = 1;
         try {
        	 pageNoI = Integer.parseInt(pageNo);
        	 pageSizeI = Integer.parseInt(pageSize);
         } catch (Exception e) {
        	 logger.error(e.getMessage());
         }
         Map<String,Object>  conditions = new HashMap<String,Object>();
		 if(!StringUtil.isNull(searchName)){
			 conditions.put("LIKE_name", searchName);
		 }
		 Page<Emp> page = empService.findEmpList(pageNoI,pageSizeI,conditions,"orderId");
		 ModelAndView mv = new ModelAndView("forward:/view/emp/empIndex.jsp");//redirect模式  
		 mv.addObject("pageList",page.getContent());
		 mv.addObject("pageBt", page2PageBt(page)); 
		 mv.addObject("searchId",searchId);
		 mv.addObject("searchName",searchName);
         return mv;
	}
	//新增接口
	@ResponseBody
	@RequestMapping("/register")  
	public BaseJson registerEmp(Emp emp){
		//检查信息项
		if(emp==null){
			return new BaseJson(500,"注册信息不完整","error");
		}else if(StringUtil.isNull(emp.getPhone())
				 ||StringUtil.isNull(emp.getName())
				 ||StringUtil.isNull(emp.getOrg_id())
				 ||StringUtil.isNull(emp.getIdcard())
				 ||StringUtil.isNull(emp.getPassword())){
			return new BaseJson(500,"注册信息不完整","error");
		}
		//查找手机号、身份证ID是否重复
		Map<String,Object>  conditions = new HashMap<String,Object>();
		if(!StringUtil.isNull(emp.getPhone())){
			conditions.put("EQ_phone", emp.getPhone());
		}
		if(!StringUtil.isNull(emp.getIdcard())){
			conditions.put("OREQ_idcard()", emp.getIdcard());
		}
		List<Emp> list = empService.findByConditions(conditions);
		if(list!=null&&list.size()>0){
			return new BaseJson(500,"注册手机号或身份证号已被注册!","error");
		}
		empService.save(emp);
		return new BaseJson(1,"新增成功!","ok");
	}
//	//保存人员信息
//		@RequestMapping("/chefSave")  
//		public ModelAndView chefSave(Emp chef,HttpServletRequest request, MultipartFile pic,
//				String towns,String villages,String[]hc_date_start,String []hc_date_end){
//			String result="";
//			String filePath=BaseConstant.fileUploadPath+DateUtil.getCurrentMonth()+"/image/";
//			String chefId=StringUtil.getUUIDString();
//			String fileName=pic.getOriginalFilename();
//			String type=pic.getOriginalFilename().substring(fileName.indexOf(".")+1, fileName.length());
//			File file = null;
//			if(chef==null){
//				
//			}else{ 
//				//人员住址 address_village
//				//保存用户表人员信息
//				User user =new User();
//				String org_ids=chef.getAddress_village();
//				String[]orgIds=org_ids.split(",");
//				if(orgIds.length==3){
//					user.setOrgId(orgIds[2]);
//					user.setOrgId1(orgIds[0]);
//					user.setOrgId2(orgIds[1]);
//					user.setOrgId3(orgIds[2]);
//					chef.setOrg_id1(orgIds[0]);
//					chef.setOrg_id2(orgIds[1]);
//					chef.setOrg_id3(orgIds[2]);
//				}else if(orgIds.length==2){
//					user.setOrgId1(orgIds[0]);
//					user.setOrgId2(orgIds[1]);
//					user.setOrgId(orgIds[1]);
//					chef.setOrg_id1(orgIds[0]);
//					chef.setOrg_id2(orgIds[1]);
//				}
//				chef.setChef_id(chefId);
//				chef.setCreate_user_id(getUserSession().userId);
//				chef.setCreate_time(new Date());
//				chef.setOrderId("99");
//				if(pic!=null){
//			        CommonsMultipartFile cf= (CommonsMultipartFile)pic; 
//			        DiskFileItem fi = (DiskFileItem)cf.getFileItem(); 
//			        file = fi.getStoreLocation();
//			        chef.setIdpic(filePath+chefId+"."+type);
////			        chefService.save(chef);
//			        result=copyFile(request, filePath, chefId+"."+type, file);
//				}else{
////					chefService.save(chef);
//				}
//				user.setUserExtend1(chef.getAddress_town());
//				 user.setUserId(chefId); 
//				 user.setUserName(chef.getName());
//				 user.setUserNameCn(chef.getName());
//				 user.setUserType("3");//角色  ：人员
//				 user.setUserOpTime(DateUtil.getCurrentDate());
//				 user.setPasswordUpdate(DateUtil.getCurrentDate()); 
//				 user.setPassword("123456");
//				 user.setUserPhone(chef.getPhone());
//				 user.setOrgId(orgIds[2]);
//				 entryptPassword(user);
//				 userService.save(user);    
//			}
//			return emplIndex("1","10",chef.getChef_id(),chef.getName());
//		}
//		//修改页面
//		@RequestMapping("/chefEdit")  
//		public ModelAndView chefEdit(String pageNo,String pageSize,String id,String searchId,String searchName){
////			Emp chef=chefService.findById(id); 
//			List<Org> list = orgService.findAll();
////			 String json1 = list3TreeJson(list,chef.getOrg_id3(),"",false,false);//住址 
//			 ModelAndView mv = new ModelAndView("forward:/view/chef/chefEdit.jsp");//redirect模式  
////			 mv.addObject("treejson1", json1); 
////			 mv.addObject("chef", chef); 
//			 mv.addObject("pageNo", pageNo); 
//			 mv.addObject("pageSize", pageSize); 
//			 mv.addObject("searchId", searchId); 
//			 mv.addObject("searchName", searchName); 
//			  return mv;
//		}
//		//保存修改信息
//		@RequestMapping("/chefModify")  
//		public ModelAndView chefModify(Emp chef,String pageNo,String pageSize,String id,
//				String towns,String villages,String[]hc_date_start,String []hc_date_end){
//			User user=userService.findUserByUserId(chef.getChef_id());
//			//删除该id记录相关的t_chef_doc表信息
////			chefService.delChefByChefId(chef.getChef_id());
//			//保存到健康证有效期表
//			 
//			//保存活动区域信息表
//			String []townStrs=towns.split(",");
//			String []villagesStrs=villages.split(",");
//			 
//			String org_ids=chef.getAddress_village();
//			String[]orgIds=org_ids.split(",");
//			if(orgIds.length==3){
//				user.setOrgId(orgIds[2]);
//				user.setOrgId1(orgIds[0]);
//				user.setOrgId2(orgIds[1]);
//				user.setOrgId3(orgIds[2]);
//				chef.setOrg_id1(orgIds[0]);
//				chef.setOrg_id2(orgIds[1]);
//				chef.setOrg_id3(orgIds[2]);
//			}else if(orgIds.length==2){
//				user.setOrgId1(orgIds[0]);
//				user.setOrgId2(orgIds[1]);
//				user.setOrgId(orgIds[1]);
//				chef.setOrg_id1(orgIds[0]);
//				chef.setOrg_id2(orgIds[1]);
//			}
//			chef.setCreate_user_id(getUserSession().userId);
//			chef.setCreate_time(new Date());
//			//修改User表信息
//			 user.setUserName(chef.getName());
//			 user.setUserNameCn(chef.getName());
//			 user.setUserPhone(chef.getPhone());
//			 user.setOrgId(orgIds[2]);
//			 userService.save(user);
////			chefService.save(chef);
//			return emplIndex("1","10",chef.getChef_id(),chef.getName());
//		}
//		//人员管理档案汇总表
//		@RequestMapping("/chefRecordMsg")  
//		public ModelAndView chefRecordMsg(String pageNo,String pageSize,String town,String yearName){
//			 logger.info("进入人员档案管理首页{}");
//			 int pageSizeI = BaseConstant.pageSize;
//			 int pageNoI = 1;
//	         try {
//	        	 pageNoI = Integer.parseInt(pageNo);
//	        	 pageSizeI = Integer.parseInt(pageSize);
//	         } catch (Exception e) {
//	        	 logger.error(e.getMessage());
//	         }
//	         Map<String,Object>  chefMap = new HashMap<String,Object>();
//			 if(!StringUtil.isNull(town)){
//				 chefMap.put("town", town);
//			 }
//			 if(!StringUtil.isNull(yearName)){
//				 chefMap.put("year", yearName);
//			 }
//			 com.github.pagehelper.Page<Emp> page2=new com.github.pagehelper.Page<Emp>();
//			 PageHelper.startPage(pageNoI,pageSizeI);
////			 page2=(com.github.pagehelper.Page<Emp>) chefService.queryChefRecordList(chefMap);
//			 List <Emp>chefList=page2.getResult();
//			 if(chefList==null||chefList.size()==0){
//			 }else{
//				 for(int i=0;i<chefList.size();i++){
//					 Emp chefTemp=chefList.get(i);
//					 //计算年龄
//					 String idCard=chefTemp.getIdcard();
//					 String year=DateUtil.getCurrentYear();
//					 String dateMD=DateUtil.getCurrentDateString().substring(4, 8);
//					 String idYear=idCard.substring(6, 10);
//					 String idMd=idCard.substring(10, 14);
//					 int age=0;
//					 age=Integer.parseInt(year)-Integer.parseInt(idYear); //当前年-出生年
//					 //MMDD  
//					 int ageM=Integer.parseInt(dateMD)-Integer.parseInt(idMd);
//					 if(ageM<0){
//						 age=age-1;
//					 }
//					 chefTemp.setIdcard(String.valueOf(age));
//					 //获取最新的健康证有效期
//					 //根据人员id获取健康证记录列表 
//					List<Integer> numList = new ArrayList<Integer>();
//					Map<Integer, Integer> map = new HashMap<Integer, Integer>();
//					Map<Integer, Integer> map2 = new HashMap<Integer, Integer>();
//					SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd"); 
//				 }
//				 
//			 }
//			 ModelAndView mv = new ModelAndView("forward:/view/chef/chefFileMag.jsp");//redirect模式  
//			 mv.addObject("pageList",page2.getResult());
//			 mv.addObject("pageBt", page2PageBt(page2)); 
//			 mv.addObject("town", town);
//			 mv.addObject("yearName", yearName);
//	         return mv;
//		}
//		@ResponseBody
//		 @RequestMapping("/exportChef")
//		  public BaseJson exportChef(HttpServletRequest request, HttpServletResponse response,String pageNo,String pageSize,String town,String yearName){
//		 try  
//		   { 
//			 List<Emp> chefList = chefExport(town,yearName);
//		 	 // 第一步，创建一个webbook，对应一个Excel文件  
//	        HSSFWorkbook wb = new HSSFWorkbook();  
//	        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
//	        HSSFSheet sheet = wb.createSheet("人员档案汇总");  
//	        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
//	        HSSFRow row = sheet.createRow((int) 0);  
//	        // 第四步，创建单元格，并设置值表头 设置表头居中  
//	        HSSFCellStyle style = wb.createCellStyle();  
//	        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
//	        HSSFCell cell = row.createCell(0); 
//	        cell.setCellValue("姓名");  
//	        cell.setCellStyle(style);  
//	        cell = row.createCell(1); 
//	        cell.setCellValue("性别");  
//	        cell.setCellStyle(style);  
//	        cell = row.createCell(2);  
//	        cell.setCellValue("年龄");  
//	        cell.setCellStyle(style);  
//	        cell = row.createCell(3); 
//	        cell.setCellValue("地址");  
//	        cell.setCellStyle(style);  
//	        cell = row.createCell(4); 
//	        cell.setCellValue("联系电话");  
//	        cell.setCellStyle(style);
//	        cell = row.createCell(5);    
//	        cell.setCellValue("健康证有效期");  
//	        cell.setCellStyle(style);
//	        cell = row.createCell(6);    
//	        cell.setCellValue("培训记录");  
//	        cell.setCellStyle(style);
//	        cell = row.createCell(7);    
//	        cell.setCellValue("全年操办酒席次数");  
//	        cell.setCellStyle(style);
//	        cell = row.createCell(8);    
//	        cell.setCellValue("发生食物中毒次数");  
//	        cell.setCellStyle(style);
//	        cell = row.createCell(9);    
//	        for (int i = 0; i < chefList.size(); i++)  
//	        {  
//	            row = sheet.createRow((int) i + 1);  
//	            Emp temp = (Emp) chefList.get(i);  
//	            // 第四步，创建单元格，并设置值  
//	            row.createCell(0).setCellValue(temp.getName());
//	            if(temp.getSex().equals("0")){
//	            	 row.createCell(1).setCellValue("女");
//	            }else{
//	            	 row.createCell(1).setCellValue("男");
//	            }
//	            row.createCell(2).setCellValue(temp.getIdcard());
//	            row.createCell(3).setCellValue(temp.getAddress_town()+temp.getChef_address_detail());
//	            row.createCell(4).setCellValue(temp.getPhone());
//	            if(null==temp.getHc_startdate()||"".equals(temp.getHc_startdate())){
//	            	 row.createCell(5).setCellValue("");
//	            }else{
//	            	 row.createCell(5).setCellValue(temp.getHc_startdate()+"-"+temp.getHc_enddate());
//	            }
//	           
//	            row.createCell(6).setCellValue(temp.getIdpic());
//	            row.createCell(7).setCellValue(temp.getSpare_1());
//	            row.createCell(8).setCellValue(temp.getSpare_2());
//	        }  
//	     // 第六步，将文件存到指定位置  
//	        
//	        	response.setCharacterEncoding("UTF-8");
//				response.setBufferSize(2000);
//				response.setHeader("content-disposition","attachment;filename="+URLEncoder.encode("聚餐承办者管理档案.xls","UTF-8"));
//				OutputStream out = response.getOutputStream();
//	            wb.write(out);  
//	            out.close();  
//	        }  
//	        catch (Exception e)  
//	        {  
//	            e.printStackTrace();  
//	        }  
//			return new BaseJson(1,"导出成功","");
//		}  
		//导出的人员档案列表
//		public List chefExport(String town,String yearName){
//			 Map<String,Object>  chefMap = new HashMap<String,Object>();
//			 if(!StringUtil.isNull(town)){
//				 chefMap.put("town", town);
//			 }
//			 if(!StringUtil.isNull(yearName)){
//				 chefMap.put("year", yearName);
//			 }
//			 List <Emp>chefList= chefService.queryChefRecordList(chefMap);
//			 if(chefList==null||chefList.size()==0){
//			 }else{
//				 for(int i=0;i<chefList.size();i++){
//					 Emp chefTemp=chefList.get(i);
//					 //计算年龄
//					 String idCard=chefTemp.getIdcard();
//					 String year=DateUtil.getCurrentYear();
//					 String dateMD=DateUtil.getCurrentDateString().substring(4, 8);
//					 String idYear=idCard.substring(6, 10);
//					 String idMd=idCard.substring(10, 14);
//					 int age=0;
//					 age=Integer.parseInt(year)-Integer.parseInt(idYear); //当前年-出生年
//					 //MMDD  
//					 int ageM=Integer.parseInt(dateMD)-Integer.parseInt(idMd);
//					 if(ageM<0){
//						 age=age-1;
//					 }
//					 chefTemp.setIdcard(String.valueOf(age));
//					 //获取最新的健康证有效期
//					 //根据人员id获取健康证记录列表
//					 List<Integer> numList = new ArrayList<Integer>();
//					 Map<Integer, Integer> map = new HashMap<Integer, Integer>();
//					 Map<Integer, Integer> map2 = new HashMap<Integer, Integer>(); 
//				 }
//				 
//			 }
//			 return chefList;
//		}
//		//删除信息
//		@ResponseBody
//		@RequestMapping("/remove")  
//		public BaseJson remove(String id,HttpServletRequest request){
//			
//			if(StringUtil.isNull(id)){ 
//				return new BaseJson(600,"参数无效","");
//			}else{
//				Emp chef= chefService.findById(id);
//				User user=userService.findUserByUserId(chef.getChef_id());
//				String path=chef.getIdpic();
//				if(StringUtil.isNull(path)){
//					
//				}else{
//					path = request.getServletContext().getRealPath(path);
//		        	File file = new File(path);
//		        	if(file.exists()){
//		        		file.delete();
//		        	}
//				}
//				
//				//删除从表相关信息
//				userService.deleteByUserId(id);
//				chefService.delChefByChefId(id);
//				chefService.delChefById(id);
//				return new BaseJson(1,"删除成功!","");
//			}
//			
//		}
		public static Object getMaxValue(Map<Integer, Integer> map) {
			if (map == null) return null;
			Collection<Integer> c = map.values();
			Object[] obj = c.toArray();
			Arrays.sort(obj);
			return obj[obj.length-1];
			}
		/**
		 * 
		 * <b>Summary: </b>
		 *    copyFile 保存上传文件
		 * String
		 * @param path
		 * @param saveName
		 * @param file
		 * @return 
		 * copyFile
		 * Dec 6, 2013
		 */
		private String copyFile(HttpServletRequest request,String realpath,String saveName,File file){
			//数据流方式上传文件
			FileOutputStream fos = null;
	        FileInputStream fis = null;
	        try {
	        	realpath = request.getServletContext().getRealPath(realpath);
	        	File dirFile = new File(realpath);
	        	if(!dirFile.isDirectory()){//目录月份目录不存在 
	        		dirFile.mkdirs();//创建目录
	 			}
	        	// 建立文件输出流
	            File savefile = new File(new File(realpath), saveName);//创建要保存到文件
	            fos = new FileOutputStream   (realpath +"/"+ saveName);
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
		private String  list2TreeJson(List<Org> orgList,String searchId,String orgId,boolean urlUse,boolean selfChecked){
			List<Tree> treeList = new ArrayList<Tree>(); 
			for(Org org:orgList){
				 Tree tree = new Tree();
				 tree.setId(org.getOrgId());
				 tree.setName(org.getOrgName());
				 tree.setpId(org.getParentOrgId());
				 if(urlUse){
					 tree.setUrl("../user/index?ac=200021&searchId="+org.getOrgId());
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
		//初始化时
		private String  list4TreeJson(List<Org> orgList,String orgId,boolean urlUse,boolean selfChecked){
			List<Tree> treeList = new ArrayList<Tree>(); 
			for(Org org:orgList){
				 Tree tree = new Tree();
				 tree.setId(org.getOrgId());
				 tree.setName(org.getOrgName());
				 tree.setpId(org.getParentOrgId());
				 if(urlUse){
					 tree.setUrl("../user/index?ac=200021&searchId="+org.getOrgId());
				 } 
				 if(!selfChecked){
					 if(org.getOrgId().equals(orgId)){ 
					    tree.setChkDisabled(selfChecked);	 
					 }
				 }
				 tree.setTarget("_self");
				 tree.setClick("");
				 
				 if(tree.isChecked()){
					 
				 }else{
					 tree.setChecked(false);
					 tree.setOpen(false);
				 }
				 treeList.add(tree);
		    } 
		    //转换成json
			return  JSON.toJSONString(treeList).replace("\"checked\"", "checked").replace("\"open\"", "open");
		}
		//将父节点设置为不可选状态
		private String  list3TreeJson(List<Org> orgList,String searchId,String orgId,boolean urlUse,boolean selfChecked){
			List<Tree> treeList = new ArrayList<Tree>(); 
			for(Org org:orgList){
				//查询该节点是否有子节点
				
				 Tree tree = new Tree();
				 tree.setId(org.getOrgId());
				 tree.setName(org.getOrgName());
				 tree.setpId(org.getParentOrgId());
				 if(urlUse){
					 tree.setUrl("../user/index?ac=200021&searchId="+org.getOrgId());
				 } 
				 if(!selfChecked){
					 if(org.getOrgId().equals(orgId)){ 
					    tree.setChkDisabled(selfChecked);	 
					 }
				 }
				 List<Org>childList=orgService.findOrgByPId(org.getOrgId());
					if(childList==null||childList.size()==0){
						
					}else{
						tree.setChkDisabled(true);
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
		/**
		 * 密码加密
		 * @param user
		 */
		private void entryptPassword(User user) { 
			byte[] salt = Digests.generateSalt(BaseConstant.SALT_SIZE); 
			user.setSalt(Encodes.encodeHex(salt)); 
			byte[] hashPassword = Digests.sha1(user.getPassword().getBytes(), salt, BaseRealm.HASH_INTERATIONS);
			user.setPassword(Encodes.encodeHex(hashPassword));
		}
}
