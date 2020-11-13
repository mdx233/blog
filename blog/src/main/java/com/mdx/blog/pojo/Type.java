package com.mdx.blog.pojo;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

public class Type {

    private Long id;
    //后端校验
    @NotEmpty(message = "{Type.name.notnull}")
    private String name;

    //blog与tag为多对一
    private List<Blog> blogs = new ArrayList<>();

    public Type() {
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Type{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
