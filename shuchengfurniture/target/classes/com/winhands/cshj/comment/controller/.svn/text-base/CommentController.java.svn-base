package com.winhands.cshj.comment.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.winhands.base.util.BaseConstant;
import com.winhands.base.util.BaseJson;
import com.winhands.base.util.BaseJson2;
import com.winhands.base.util.StringUtil;
import com.winhands.base.web.BaseController;
import com.winhands.cshj.comment.entity.Comment;
import com.winhands.cshj.comment.entity.CommentLike;
import com.winhands.cshj.comment.entity.CommentMag;
import com.winhands.cshj.comment.service.CommentService;
import com.winhands.cshj.integration.entity.Integration;
import com.winhands.cshj.integration.entity.IntegrationMember;
import com.winhands.cshj.integration.service.IntegrationService;
import com.winhands.cshj.log.entity.Log;
import com.winhands.cshj.log.service.LogService;
import com.winhands.cshj.member.entity.Member;
import com.winhands.cshj.member.service.MemberService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author guojun
 */
@Controller
@RequestMapping("/comment")
public class CommentController extends BaseController{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CommentService impl;
    @Autowired
    private LogService logService;
    @Autowired
	private IntegrationService integrationService;
    @Autowired
	private MemberService memberService;
    public static String AC;
    
    
    
    
    /**
     * 查询评论并跳转到评论页
     * @param ac
     * @param pageNum
     * @param pageSize
     * @param articleId
     * @return
     */
    @RequestMapping("/magIndex")
    public ModelAndView magIndex(String ac,String pageNum,String pageSize,CommentMag comment,String order_type,String file_type){
        if(!StringUtil.isNull(ac)){
            AC = ac;
        }
        if(StringUtil.isNull(pageNum)){
            pageNum = "1";
        }
        if(StringUtil.isNull(pageSize)){
            pageSize = "10";
        }
        Page<CommentMag> page = new Page<CommentMag>();
        try {
            PageHelper.startPage(Integer.parseInt(pageNum),Integer.parseInt(pageSize)); 
              page = (Page<CommentMag>)impl.queryCommentMagList(comment);
        }catch (Exception e){
            logger.error("查询评论出错：",e);
        }
        
        ModelAndView mv = new ModelAndView("forward:/view/comment/commentIndex.jsp?ac="+AC);
        mv.addObject("list",page.getResult());
        mv.addObject("pageBt",page2PageBt(page));
        mv.addObject("comment",comment); 
        mv.addObject("ac",ac);
        return mv;
    }
    
    /**
     * 查询评论并跳转到评论页
     * @param ac
     * @param pageNum
     * @param pageSize
     * @param articleId
     * @return
     */
    @RequestMapping("/index")
    public ModelAndView index(String ac,String pageNum,String pageSize,String articleId,String order_type,String file_type){
        if(!StringUtil.isNull(ac)){
            AC = ac;
        }
        if(StringUtil.isNull(pageNum)){
            pageNum = "1";
        }
        if(StringUtil.isNull(pageSize)){
            pageSize = "10";
        }
        Page<Comment> page = new Page<Comment>();
        try {
            PageHelper.startPage(Integer.parseInt(pageNum),Integer.parseInt(pageSize));
            Map<String,String> map = new HashMap<String, String>();
            map.put("articleId",articleId);
            if(getUserSession()!=null){
                map.put("memberId",getUserSession().userId);
            }
            page = (Page<Comment>)impl.queryFirstLevelCommentList(map);
        }catch (Exception e){
            logger.error("查询评论出错：",e);
        }
        ArrayList<Comment> list = (ArrayList<Comment>)page.getResult();
        for(Comment c : list){
            c.setReplyList(getSecondLevelComment(c.getId()));
        }
        ModelAndView mv = new ModelAndView("forward:/view/articleFile/comment.jsp?ac="+AC);
        mv.addObject("list",list);
        mv.addObject("pageBt",page2PageBt(page));
        mv.addObject("articleId",articleId);
        mv.addObject("order_type",order_type);
        mv.addObject("file_type",file_type);
        mv.addObject("ac",ac);
        return mv;
    }

    /**
     * 删除评论
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteComment")
    public BaseJson deleteComment(String id){
        BaseJson json = new BaseJson();
        try {
            impl.deleteCommentById(id);
            json.setStatus(200);
        }catch (Exception e){
            logger.error("删除评论出错：",e);
            json.setStatus(500);
            json.setMessage("系统错误！");
        }
        try {
            logService.saveLog(new Log(StringUtil.getUUIDString(),"删除评论","内容管理",getUserSession().userId,getUserSession().userName));
        }catch (Exception e){
            logger.error("保存日志出错：",e);
        }
        return json;
    }

    public Page<Comment> getFirstLevelComment(int pageNum,int pageSize,String articleId){
        Page<Comment> page = new Page<Comment>();
        try {
            PageHelper.startPage(pageNum,pageSize);
            Map<String,String> map = new HashMap<String, String>();
            map.put("articleId",articleId);
            if(getUserSession()!=null){
                map.put("memberId",getUserSession().userId);
            }
            page = (Page<Comment>)impl.queryFirstLevelCommentList(map);
        }catch (Exception e){
            logger.error("查询评论出错：",e);
        }
        return page;
    }

    public List<Comment> getSecondLevelComment(String parentId){
        List<Comment> list = new ArrayList<Comment>();
        try {
            Map<String,String> map = new HashMap<String, String>();
            map.put("parentId",parentId);
            if(getUserSession()!=null){
                map.put("memberId",getUserSession().userId);
            }
            list = impl.querySecondLevelCommentList(map);
        }catch (Exception e){
            logger.error("查询评论出错：",e);
        }
        return list;
    }

    /******************************************************************客户端接口*****************************************************************/
    /**
     * 新增评论
     * @param comment
     * @return
     */
    @ResponseBody
    @RequestMapping("/msSaveComment")
    public BaseJson msSaveComment(Comment comment){
        BaseJson json = new BaseJson();
       try {
    	//查询评论用户是否对该文章进行评论过，若为首次评论，按照积分规则加积分
    	List commentList = new ArrayList();
    	Map<String,String> map = new HashMap<String, String>();
        map.put("createUserId",getUserSession().userId);
        map.put("articleId",comment.getArticleId());
        commentList = impl.queryCommentList(map);
        if(commentList==null||commentList.size()==0){//加积分
    		//插入记录到积分日志表中
    		IntegrationMember inter = new IntegrationMember();
    		inter.setId(StringUtil.getUUIDString());
    		inter.setMemberId(getUserSession().userId);
    		inter.setArticleId(comment.getArticleId());
    		Integration gz = new Integration();
    		try {
    			gz = integrationService.queryIntegrationById(BaseConstant.JFPLID);
    		} catch (Exception e1) {
    			e1.printStackTrace();
    		}
    		inter.setIntegrationId(gz.getId());//答题规则id
    		inter.setScore(gz.getScore());
    		inter.setCreateDate(new Date());

    		try {
    			integrationService.saveIntegrationMember(inter);
    			//根据用户id获取用户信息  查积分
    			Member member = memberService.queryMemberById(getUserSession().userId);
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
        comment.setCreateUserId(getUserSession().userId);
        comment.setCreateUserName(getUserSession().userName);
        comment.setId(StringUtil.getUUIDString());
        impl.saveComment(comment);
        json.setStatus(200);
        }catch (Exception e){
            logger.error("新增评论出错：",e);
            json.setStatus(500);
            json.setMessage("系统错误，请重新操作！");
        }
        return json;
    }

    /**
     * 客户端根据单条评论id获取回复列表
     * @param parentId
     * @return
     */
    @ResponseBody
    @RequestMapping("/msGetReply")
    public BaseJson2 msGetReply(String parentId){
        BaseJson2 json = new BaseJson2();
        List<Comment> list = new ArrayList<Comment>();
        Comment c = new Comment();
        try {
            Map<String,String> map = new HashMap<String, String>();
            map.put("parentId",parentId);
            map.put("memberId",getUserSession().userId);
            list = impl.querySecondLevelCommentList(map);
            c = impl.queryCommentById(map);
            c.setReplyList(list);
            json.setStatus(200);
        }catch (Exception e){
            logger.error("根据id获取回复出错：",e);
            json.setStatus(500);
            json.setMessage("系统出错，请重试");
        }
        List<Comment> list2 = new ArrayList<Comment>();
        list2.add(c);
        json.setResultList(list2);
        return json;
    }

    /**
     * 客户端获取评论列表
     * @param pageNum
     * @param pageSize
     * @param articleId
     * @return
     */
    @ResponseBody
    @RequestMapping("/msGetComment")
    public BaseJson2 msGetComment(String pageNum,String pageSize,String articleId){
        BaseJson2 json = new BaseJson2();
        if(StringUtil.isNull(pageNum)){
            pageNum = "1";
        }
        if(StringUtil.isNull(pageSize)){
            pageSize = "10";
        }
        Page<Comment> page = new Page<Comment>();
        try{
            PageHelper.startPage(Integer.parseInt(pageNum),Integer.parseInt(pageSize));
            page = getFirstLevelComment(Integer.parseInt(pageNum),Integer.parseInt(pageSize),articleId);
            ArrayList<Comment> list = (ArrayList<Comment>)page.getResult();
            for(Comment c : list){
                c.setReplyList(getSecondLevelComment(c.getId()));
            }
            json.setStatus(200);
            json.setResultList(list);
            json.setPageNum(page.getPageNum());
            json.setPageSize(page.getPageSize());
            json.setPages(page.getPages());
            json.setTotal(page.getTotal());
        }catch(Exception e){
            logger.error("查询评论出错：",e);
            json.setStatus(500);
            json.setMessage("系统错误！");
        }

        return json;
    }

    /**
     * 保存评论点赞
     * @param commentId
     * @return
     */
    @ResponseBody
    @RequestMapping("/msSaveLike")
    public BaseJson msSaveLike(String commentId){
        CommentLike like = new CommentLike();
        like.setId(StringUtil.getUUIDString());
        like.setCreateUserId(getUserSession().userId);
        like.setCommentId(commentId);
        BaseJson json = new BaseJson();
        try{
            impl.saveLike(like);
            json.setStatus(200);
        }catch (Exception e){
            logger.error("保存点赞出错：",e);
            json.setStatus(500);
            json.setMessage("系统错误，请重新操作！");
        }
            return json;
    }
}
