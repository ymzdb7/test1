package com.winhands.cshj.comment.service.impl;

import com.winhands.cshj.comment.entity.Comment;
import com.winhands.cshj.comment.entity.CommentLike;
import com.winhands.cshj.comment.entity.CommentMag;
import com.winhands.cshj.comment.repository.CommentDao;
import com.winhands.cshj.comment.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author guojun
 */
@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    private CommentDao dao;
    @Override
    public List<Comment> queryFirstLevelCommentList(Map<String,String> map) throws Exception {
        return dao.queryFirstLevelCommentList(map);
    }

    @Override
    public List<Comment> querySecondLevelCommentList(Map<String,String> map) throws Exception {
        return dao.querySecondLevelCommentList(map);
    }

    @Override
    public int saveComment(Comment comment) throws Exception {
        return dao.saveComment(comment);
    }

    @Override
    public int deleteCommentById(String id) throws Exception {
        return dao.deleteCommentById(id);
    }

    @Override
    public int saveLike(CommentLike like) throws Exception {
        return dao.saveLike(like);
    }

    @Override
    public Comment queryCommentById(Map<String,String> map) throws Exception {
        return dao.queryCommentById(map);
    }

	@Override
	public List<Comment> queryCommentList(Map<String, String> map)
			throws Exception {
		return dao.queryCommentList(map);
	}

	@Override
	public List<CommentMag> queryCommentMagList(CommentMag commentMag) throws Exception {
		return dao.queryCommentMagList(commentMag);
	}

	@Override
	public int deleteCommentByCreateUserId(String createUserId)
			throws Exception {
		return dao.deleteCommentByCreateUserId(createUserId);
	}
}
