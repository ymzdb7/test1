package com.winhands.cshj.integration.controller;


import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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
import com.winhands.base.util.StringUtil;
import com.winhands.base.web.BaseController;
import com.winhands.cshj.integration.entity.Integration;
import com.winhands.cshj.integration.entity.IntegrationMember;
import com.winhands.cshj.integration.service.IntegrationService;
import com.winhands.cshj.log.entity.Log;
import com.winhands.cshj.log.service.LogService;
import com.winhands.cshj.member.entity.Member;
import com.winhands.cshj.member.service.MemberService;


@Controller
@RequestMapping("/integration")
public class IntegrationController extends BaseController{
	private final Logger logger = LoggerFactory.getLogger(IntegrationController.class); 
	@Autowired
	private IntegrationService integrationService;
	@Autowired
	private MemberService memberService;
    @Autowired
    private LogService logService;
	
	//跳转到积分规则列表页面
	@RequestMapping("/index")
	public ModelAndView index(String pageNo, String pageSize, String searchId, Integration integration,String ac) {
		int pageSizeI = BaseConstant.pageSize;
		int pageNoI = 1;
		try {
			pageNoI = Integer.parseInt(pageNo);
			pageSizeI = Integer.parseInt(pageSize);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		Page<Integration> page = new Page<Integration>();
		PageHelper.startPage(pageNoI, pageSizeI);
		try {
			page = (Page<Integration>) integrationService.queryIntegrationList(integration);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ModelAndView mv = new ModelAndView("forward:/view/integration/ruleIndex.jsp");// redirect模式
		mv.addObject("pageList", page.getResult());
		mv.addObject("pageBt", page2PageBt(page));
		mv.addObject("ac", ac);
		return mv;
	}
	//跳转到积分日志列表页面
		@RequestMapping("/logIndex")
		public ModelAndView logIndex(String pageNo, String pageSize, String searchId, IntegrationMember integrationMember,String ac) {
			int pageSizeI = BaseConstant.pageSize;
			int pageNoI = 1;
			try {
				pageNoI = Integer.parseInt(pageNo);
				pageSizeI = Integer.parseInt(pageSize);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
			Page<IntegrationMember> page = new Page<IntegrationMember>();
			PageHelper.startPage(pageNoI, pageSizeI);
			try {
				page = (Page<IntegrationMember>) integrationService.queryIntegrationMemberList(integrationMember);
			} catch (Exception e) {
				e.printStackTrace();
			}
			ModelAndView mv = new ModelAndView("forward:/view/integration/logIndex.jsp");// redirect模式
			mv.addObject("pageList", page.getResult());
			mv.addObject("pageBt", page2PageBt(page));
			mv.addObject("ac", ac);
			return mv;
		}	
	 /**
     * 查询积分信息
     * @param pageNum
     * @param pageSize
     * @param m
     * @param ac
     * @return
     */
    @RequestMapping("/integIndex")
    public ModelAndView integIndex(String pageNo,String pageSize,Member m){
        if(StringUtil.isNull(pageNo)){
            pageNo = "1";
        }
        if(StringUtil.isNull(pageSize)){
            pageSize = "10";
        }
        PageHelper.startPage(Integer.parseInt(pageNo),Integer.parseInt(pageSize));
        Page<Member> page = new Page<Member>();
        try{
            page = (Page<Member>)memberService.queryMemberList(m);
        }catch (Exception e){
            logger.error("查询会员列表出错：",e);
        }
        ModelAndView mv = new ModelAndView("forward:/view/integration/integraMagIndex.jsp");
        mv.addObject("pagesBt",page2PageBt(page));
        mv.addObject("list",page.getResult());
        mv.addObject("member",m);
        return mv;
    }
	//跳转到新增积分规则页面
	@RequestMapping("/addRule")  
	public ModelAndView addRule(String ac){
		 ModelAndView mv=new ModelAndView("forward:/view/integration/addRule.jsp");
		 mv.addObject("ac", ac);
		 return mv;
	}
	
	//修改积分
	@ResponseBody
	@RequestMapping("/updateIntegration")
		public BaseJson updateIntegration(String memberId,String integration,String userName) {
		Log log;	
		Member member = new Member();
			member.setId(memberId);
			try {
				member=memberService.queryMemberById(memberId);
				member.setIntegration(integration);
				memberService.updateMember(member);
				log = new Log(StringUtil.getUUIDString(),"对会员【"+userName+"】账号的积分调整为："+integration,"积分信息管理",getUserSession().userId,getUserSession().userName);
				logService.saveLog(log);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return new BaseJson(200, "查询成功", "ok");
		}
	//获取积分规则
	// 客户端获取收藏列表
	@ResponseBody
	@RequestMapping("/msQueryRuleList")
	public BaseJson msQueryRuleList() {
		List ruleList = new ArrayList();
		Integration integration = new Integration();
		try {
			ruleList = integrationService.queryIntegrationList(integration);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new BaseJson(200, "查询成功", "", ruleList);
	}
	@RequestMapping("/saveRule")  
	public ModelAndView saveRule(Integration rule,String ac){
		 logger.info("进入目录管理保存{}"); 
		 String spare_1="";
		 try {
			if(rule==null){
			 }else{
				 rule.setId(StringUtil.getUUIDString());
				 rule.setCreateUserId(getUserSession().userId);
				 integrationService.saveIntegration(rule);
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
			 return index("1", "10", "", rule, ac);
	}
	/**
     * 导出会员信息
     *
     */
    @RequestMapping("/expExcelInteg")
    public void expExcelByClass(Member m, HttpServletResponse response) {
        StringBuilder sb = new StringBuilder("(");
        List jfList =new ArrayList();
        try {
            jfList=  memberService.queryMemberList(m);
            response.setContentType("application/octet-stream");
            String name = "积分信息报表";
            try {
                response.setHeader("Content-Disposition", "attachment;filename=" + new String(name.getBytes("GBK"), "ISO8859-1") + ".xls");
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet("积分信息报表");
            HSSFRow row = sheet.createRow((int) 0);
            String[] header = {"序号","手机号","会员名","用户名","会员类别","积分","区镇","单位名称"};
            HSSFCellStyle style = wb.createCellStyle();
            HSSFCellStyle style2 = wb.createCellStyle();
            // 设置居中样式
            style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 水平居中
            style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 垂直居中
            style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 垂直居中
            // 添加表格头
            for (int i = 0; i < header.length; i++) {
                HSSFCell cell = row.createCell(i);
                cell.setCellValue(header[i]);
                cell.setCellStyle(style);
            }
            row = sheet.createRow((int) 1); // 第二行
            String curVal = "";
            for (int i = 0; i < jfList.size(); i++) {
            	Member temp = (Member) jfList.get(i);
                row = sheet.createRow(i + 1);
                row.setHeight((short) 500);
                HSSFCell cell = row.createCell(0);
                cell.setCellStyle(style);
                cell = row.createCell(0);
                cell.setCellValue(i+1);
                cell.setCellStyle(style);
                cell = row.createCell(1);
                cell.setCellValue(temp.getPhoneNum());
                cell.setCellStyle(style);
                cell = row.createCell(2);
                cell.setCellValue(temp.getUserName());
                cell.setCellStyle(style);
                cell = row.createCell(3);
                cell.setCellValue(temp.getUserNameCn());
                cell.setCellStyle(style);
                cell = row.createCell(4);
                String type = "";
                if("0".equals(temp.getType())){
                	type = "注册用户";
                }else{
                	type = "会员用户";
                }
                cell.setCellValue(type);
                cell.setCellStyle(style);
                cell = row.createCell(5);
                cell.setCellValue(temp.getIntegration());
                cell.setCellStyle(style);
                cell = row.createCell(6);
                cell.setCellValue((StringUtil.isNull(temp.getOrgId2())?"":temp.getOrgId2())+(StringUtil.isNull(temp.getOrgId3())?"":temp.getOrgId3()));
                cell.setCellStyle(style);
                cell = row.createCell(7);
                cell.setCellValue(temp.getClassId());
                cell.setCellStyle(style);
            }
            sheet.autoSizeColumn((short) 0); // 调整第一列宽度
            sheet.autoSizeColumn((short) 1); // 调整第二列宽度
            sheet.autoSizeColumn((short) 2); // 调整第三列宽度
            sheet.autoSizeColumn((short) 3); // 调整第四列宽度
            sheet.autoSizeColumn((short) 4); // 调整第五列宽度
            sheet.autoSizeColumn((short) 5); // 调整第六列宽度
            sheet.autoSizeColumn((short) 6); // 调整第七列宽度
            sheet.autoSizeColumn((short) 7); // 调整第八列宽度
            
            
            ServletOutputStream out = response.getOutputStream();
            wb.write(out);
            out.flush();
            out.close();
        } catch (Exception e) {
            logger.error("获取积分信息出错：", e);
        }
    }
}
