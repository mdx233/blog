package com.mdx.blog.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Comment {
   private Long id;
   private String nickname;
   private String email;
   private String content;
   //头像
   private String avatar;
   private Date createTime;

   //comment与blog关系为多对一
    private Blog blog;

    //回复评论，一个评论可以有多个回复评论
    private List<Comment> replayComment = new ArrayList<>();
    //评论区上级评论，一个评论对应被回复的评论只有一个
    private Comment parentComment;


    private Long blogId;
    private Long parentCommentId;
    private String parentNickname;
    //标志位，判断当前评论是否为博主
    private int adminComment;


    public int getAdminComment() {
        return adminComment;
    }

    public void setAdminComment(int adminComment) {
        this.adminComment = adminComment;
    }

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }

    public Long getParentCommentId() {
        return parentCommentId;
    }

    public void setParentCommentId(Long parentCommentId) {
        this.parentCommentId = parentCommentId;
    }

    public String getParentNickname() {
        return parentNickname;
    }

    public void setParentNickname(String parentNickname) {
        this.parentNickname = parentNickname;
    }

    public Comment() {
    }

    public List<Comment> getReplayComment() {
        return replayComment;
    }

    public void setReplayComment(List<Comment> replayComment) {
        this.replayComment = replayComment;
    }

    public Comment getParentComment() {
        return parentComment;
    }

    public void setParentComment(Comment parentComment) {
        this.parentComment = parentComment;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", content='" + content + '\'' +
                ", avatar='" + avatar + '\'' +
                ", createTime=" + createTime +
                ", blog=" + blog +
                ", replayComment=" + replayComment +
                ", parentComment=" + parentComment +
                ", blogId=" + blogId +
                ", parentCommentId=" + parentCommentId +
                ", parentNickname='" + parentNickname + '\'' +
                ", adminComment=" + adminComment +
                '}';
    }
}
