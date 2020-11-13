package com.mdx.blog.service;

import com.mdx.blog.pojo.Blog;
import com.mdx.blog.queryvo.*;

import java.util.List;

public interface BlogService {
    List<BlogQuery> getAllBlogQuery();

    List<BlogQuery> getAllBlogArchive();

    Blog getBlog(Long id);

    int saveBlog(Blog blog);

    int updateBlog(ShowBlog showBlog);

    int deleteBlog(Long id);

    List<BlogQuery> search(SearchBlog searchBlog);

    void transformRecommend(SearchBlog searchBlog);

    List<FirstPageBlog> getAllFirstPageBlog();

    List<Blog> getALLRecommendBlog();

    List<FirstPageBlog> getSearchBlog(String query);

    DetailedBlog getDetailedBlog(Long id);

    List<FirstPageBlog> getFirstPageBlogByTypeId(Long id);

    List<FirstPageBlog> getFirstPageBlogByTagId(Long id);

}
