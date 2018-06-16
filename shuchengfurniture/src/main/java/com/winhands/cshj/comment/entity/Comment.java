package com.winhands.cshj.comment.entity;

import java.util.List;

/**
 * @author guojun
 */
public class Comment {
    private String id;
    private String content;
    private String createUserId;
    private String parentId;
    private String articleId;
    private String createTime;
    private String createUserName;
    private String repliedUserId;   //被回复人id
    private String repliedUserName;
    private String createUserAvatar;  //评论人头像地址
    private List<Comment> replyList;   //回复list
    private int likeSum;  //点赞数
    private String hasLiked;  //是否点赞

    public String getHasLiked() {
        return hasLiked;
    }

    public void setHasLiked(String hasLiked) {
        this.hasLiked = hasLiked;
    }

    public int getLikeSum() {
        return likeSum;
    }

    public void setLikeSum(int likeSum) {
        this.likeSum = likeSum;
    }

    public String getCreateUserAvatar() {
        return createUserAvatar;
    }

    public void setCreateUserAvatar(String createUserAvatar) {
        this.createUserAvatar = createUserAvatar;
    }

    public List<Comment> getReplyList() {
        return replyList;
    }

    public void setReplyList(List<Comment> replyList) {
        this.replyList = replyList;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public String getRepliedUserId() {
        return repliedUserId;
    }

    public void setRepliedUserId(String repliedUserId) {
        this.repliedUserId = repliedUserId;
    }

    public String getRepliedUserName() {
        return repliedUserName;
    }

    public void setRepliedUserName(String repliedUserName) {
        this.repliedUserName = repliedUserName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
