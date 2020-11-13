package com.mdx.blog.queryvo;

import com.mdx.blog.pojo.Type;

import java.util.Date;
/*
* 博客列表显示数据时使用的类
* */
public class BlogQuery {
    private Long id;
    private String title;
    private Date updateTime;
    private Integer recommend;
    private Long typeId;
    private Type type;
    private boolean published;

    public BlogQuery() {
    }

    public boolean isPublished() {
        return published;
    }
    public void setPublished(boolean published) {
        this.published = published;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getRecommend() {
        return recommend;
    }

    public void setRecommend(Integer recommend) {
        this.recommend = recommend;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "BlogQuery{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", updateTime=" + updateTime +
                ", recommend=" + recommend +
                ", typeId=" + typeId +
                ", type=" + type +
                ", published=" + published +
                '}';
    }

}
