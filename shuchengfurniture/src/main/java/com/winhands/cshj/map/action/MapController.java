package com.winhands.cshj.map.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.winhands.base.web.BaseController;

/**
 * 
 */
@Controller
@RequestMapping("/map")
public class MapController extends BaseController{
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private static String AC;

    /**
     * 进入会员费首页
     * @param msf
     * @param ac
     * @return
     */
    @RequestMapping("/index")
    public ModelAndView index(String ac) {
        
        ModelAndView mv = new ModelAndView("forward:/view/map/map.jsp?ac="+AC);
        return mv;

    }


   

}
