package com.winhands.cshj.log.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.winhands.base.util.StringUtil;
import com.winhands.base.web.BaseController;
import com.winhands.cshj.log.entity.Log;
import com.winhands.cshj.log.service.LogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author guojun
 */
@Controller
@RequestMapping("/log")
public class LogController extends BaseController{
    @Autowired
    private LogService impl;

    private  final Logger logger = LoggerFactory.getLogger(this.getClass());
    private static String AC;

    @RequestMapping("index")
    public ModelAndView index(String userName, String startTime,String endTime,String pageNum,String pageSize,String ac){
        if(StringUtil.isNull(AC)){
            AC = ac;
        }
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("userName",userName);
        if(!StringUtil.isNull(startTime)){
            map.put("startTime",Timestamp.valueOf(startTime));
        }
        if(!StringUtil.isNull(endTime)){
            map.put("endTime",Timestamp.valueOf(endTime));
        }

        if(StringUtil.isNull(pageNum)){
            pageNum = "1";
        }
        if(StringUtil.isNull(pageSize)){
            pageSize = "10";
        }
        List<Log> list = new ArrayList<Log>();
        Page<Log> page  = new Page<Log>();
        PageHelper.startPage(Integer.parseInt(pageNum),Integer.parseInt(pageSize));
        try {
            page = (Page<Log>)impl.queryLogList(map);
        }catch (Exception e){
            logger.error("查询日志出错：",e);
        }
        ModelAndView mv = new ModelAndView("forward:/view/log/logIndex.jsp?ac="+AC);
        mv.addObject("list",page.getResult());
        mv.addObject("pageBt", page2PageBt(page));
        mv.addObject("startTime", startTime);
        mv.addObject("endTime", endTime);
        mv.addObject("userName", userName);
        return mv;
    }
}
