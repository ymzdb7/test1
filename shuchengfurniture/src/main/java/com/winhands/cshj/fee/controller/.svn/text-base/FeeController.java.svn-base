package com.winhands.cshj.fee.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.winhands.base.util.BaseJson;
import com.winhands.base.util.BaseJson2;
import com.winhands.base.util.StringUtil;
import com.winhands.base.web.BaseController;
import com.winhands.cshj.fee.entity.MemberShipFee;
import com.winhands.cshj.fee.enums.StatEnum;
import com.winhands.cshj.fee.service.FeeService;
import com.winhands.cshj.log.entity.Log;
import com.winhands.cshj.log.service.LogService;

/**
 * @author guojun
 */
@Controller
@RequestMapping("/fee")
public class FeeController extends BaseController{
    @Autowired
    private FeeService impl;
    @Autowired
    private LogService logService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private static String AC;

    /**
     * 进入会员费首页
     * @param msf
     * @param ac
     * @return
     */
    @RequestMapping("/index")
    public ModelAndView index(MemberShipFee msf,String ac) {
        if(!StringUtil.isNull(ac)){
            AC = ac;
        }
        logger.info("查询会员年费信息");
        List<MemberShipFee> list = new ArrayList<MemberShipFee>();
        List<MemberShipFee> uList = new ArrayList<MemberShipFee>();
        long t = System.currentTimeMillis();
        try {
            list = impl.queryFeeList(msf);
            uList = impl.queryInUseFeeList(msf);
            for(MemberShipFee m : list){
                for(MemberShipFee um : uList){
                    if(um.getId().equals(m.getId())){  //使用中
                        m.setState(StatEnum.IN_USE.getStateInfo());
                    }
                }
            }
            for(MemberShipFee m : list){
                if(StringUtil.isNull(m.getState())){
                    if(m.getEffectiveTime().getTime() > t){  //未生效
                        m.setState(StatEnum.NOT_IN_EFFECT.getStateInfo());
                    }else{  //已失效
                        m.setState(StatEnum.OUT_DATE.getStateInfo());
                    }
                }
            }
        } catch (Exception e) {
            logger.error("查询会员年费信息出错：",e);
        }
        ModelAndView mv = new ModelAndView("forward:/view/fee/feeIndex.jsp?ac="+AC);
        mv.addObject("list",list);
        mv.addObject("type",msf.getType());
        return mv;

    }


    /**
     * 新增会费标准
     * @param msf
     * @return
     */
    @ResponseBody
    @RequestMapping("/addFee")
    public BaseJson addFee(MemberShipFee msf){
        logger.info("新增会费标准");
        BaseJson json = new BaseJson();
        msf.setId(StringUtil.getUUIDString());
        msf.setCreateUserId(getUserSession().userId);
        try {
            impl.saveFee(msf);
            json.setStatus(200);
        }catch (Exception e){
            logger.error("保存会费标准出错：",e);
        }
        try {
            logService.saveLog(new Log(StringUtil.getUUIDString(),"新增会费标准","参数设置",getUserSession().userId,getUserSession().userName));
        }catch (Exception e){

        }
       return json;
    }

    /**
     * 删除会费标准
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteFee")
    public BaseJson deleteFee(String id){
        BaseJson json = new BaseJson();
        try{
            impl.deleteFee(id);
            json.setStatus(200);
        }catch (Exception e){
            logger.error("删除会费标准出错：",e);
        }
        try {
            logService.saveLog(new Log(StringUtil.getUUIDString(),"删除会费标准","参数设置",getUserSession().userId,getUserSession().userName));
        }catch (Exception e){

        }
        return json;
    }



    /***************************************************************************客户端接口*******************************************************************************/

    /**
     * 获取使用中会费标准
     * @param msf
     */
    @ResponseBody
    @RequestMapping("/msGetMemberShipFee")
    public BaseJson2 msGetMemberShipFee(MemberShipFee msf){
        List<MemberShipFee> list = new ArrayList<MemberShipFee>();
        BaseJson2 json = new BaseJson2();
        try{
            list = impl.queryInUseFeeList(msf);
            json.setStatus(200);
        }catch (Exception e){
            logger.error("获取使用中会费标准出错：",e);
            json.setStatus(500);
        }
        json.setResultList(list);
        return json;
    }

}
