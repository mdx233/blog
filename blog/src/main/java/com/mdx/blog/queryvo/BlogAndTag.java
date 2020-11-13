package com.mdx.blog.queryvo;
/*
* 把博客和标签关系存到数据库中使用的类
*
* */
public class BlogAndTag {
    private Long tagId;
    private Long blogId;

    public BlogAndTag() {
    }

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }

    @Override
    public String toString() {
        return "BlogAndTag{" +
                "tagId=" + tagId +
                ", blogId=" + blogId +
                '}';
    }
}
