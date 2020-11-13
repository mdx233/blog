package com.mdx.blog.service;
import com.mdx.blog.pojo.Type;
import java.util.List;

public interface TypeService {
    int saveType(Type type);

    Type getType(Long id);

    List<Type> getAllType();

    Type getTypeByName(String name);

    List<Type> getTypeAndBlog();

    int updateType(Type type);

    int deleteType(Long id);

    List<Type> getTypeAndBlogSize();
}
