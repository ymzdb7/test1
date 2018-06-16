package com.winhands.cshj.comment.service;

import com.winhands.cshj.comment.entity.Comment;
import com.winhands.cshj.comment.entity.CommentLike;
import com.winhands.cshj.comment.entity.CommentMag;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author guojun
 */
@Component
public interface CommentService {
    List<Comment> queryFirstLevelCommentList(Map<String,String> map) throws Exception;
    List<Comment> querySecondLevelCommentList(Map<String,String> map) throws Exception;
    int saveComment(Comment comment) throws Exception;
    int deleteCommentById(String id) throws Exception;
    int deleteCommentByCreateUserId(String createUserId) throws Exception;
    int saveLike(CommentLike like) throws Exception;
    Comment queryCommentById(Map<String,String> map) throws Exception;
    List<Comment> queryCommentList(Map<String,String> map) throws Exception;
    
    List<CommentMag> queryCommentMagList(CommentMag commentMag) throws Exception;
}
