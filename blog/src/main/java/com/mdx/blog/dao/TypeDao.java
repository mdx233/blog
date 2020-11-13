package com.mdx.blog.dao;

import com.mdx.blog.pojo.Type;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TypeDao {
    int saveType(Type type);

    Type getType(Long id);

    Type getTypeByName(String name);

    List<Type> getAllType();

    List<Type> getTypeAndBlog();

    int updateType(Type type);

    int deleteType(Long id);

    //用于查询分类页面需要使用的分类数据
    List<Type> getAllTypeAndBlogSize();
}
