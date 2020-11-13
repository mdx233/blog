package com.mdx.blog.dao;

import com.mdx.blog.pojo.Blog;
import com.mdx.blog.queryvo.*;
import com.mdx.blog.service.BlogService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


import java.util.List;

@Mapper
@Repository
public interface BlogDao {
    List<BlogQuery> getAllBlogQuery();

    List<BlogQuery> getAllBlogArchive();

    Blog getBlog(Long id);

    int saveBlog(Blog blog);

    int updateBlog(ShowBlog showBlog);

    int deleteBlog(Long id);

    List<BlogQuery> searchByTitleOrTypeOrRecommend(SearchBlog searchBlog);

    int deleteBlogAndTag(Long blogId);

    int saveBlogAndTag(BlogAndTag blogAndTag);

    int updateViews(Long id);
    //首页博客展示
    List<FirstPageBlog> getAllFirstPageBlog();
    //根据类型id查找展示博客列表
    List<FirstPageBlog> getFirstPageBlogByTypeId(Long typeId);
    //获取推荐博客
    List<Blog> getAllRecommendBlog();
    //搜索博客
    List<FirstPageBlog> getSearchBlog(String query);
    //根据id获得对应博客
    DetailedBlog getDetailedBlog(Long id);
    //根据标签id查找展示博客列表
    List<FirstPageBlog> getFirstPageBlogByTagId(Long tagId);
}
