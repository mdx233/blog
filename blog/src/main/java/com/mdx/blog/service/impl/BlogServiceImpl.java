package com.mdx.blog.service.impl;

import com.mdx.blog.Exception.NotFoundException;
import com.mdx.blog.dao.BlogDao;
import com.mdx.blog.pojo.Blog;
import com.mdx.blog.pojo.Tag;
import com.mdx.blog.queryvo.*;
import com.mdx.blog.service.BlogService;
import com.mdx.blog.service.TagService;
import com.mdx.blog.util.MarkdownUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogDao blogDao;
    @Autowired
    private TagService tagService;

    @Override
    public List<BlogQuery> getAllBlogQuery() {
        return blogDao.getAllBlogQuery();
    }

    @Override
    public List<BlogQuery> getAllBlogArchive() {
        return blogDao.getAllBlogArchive();
    }

    @Override
    public Blog getBlog(Long id) {
        return blogDao.getBlog(id);
    }

    @Transactional
    @Override
    public int saveBlog(Blog blog) {
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setViews(0);
        int result = blogDao.saveBlog(blog);
        //将标签的数据保存到博客与标签的关联表中
        List<Tag> tags = blog.getTags();
        BlogAndTag blogAndTag = new BlogAndTag();
        for(Tag tag : tags){
            blogAndTag.setBlogId(blog.getId());
            blogAndTag.setTagId(tag.getId());
            blogDao.saveBlogAndTag(blogAndTag);
        }
        return result;
    }

    @Transactional
    @Override
    public int updateBlog(ShowBlog showBlog) {
        Blog blog1 = blogDao.getBlog(showBlog.getId());
        blogDao.deleteBlogAndTag(showBlog.getId());
        if(blog1 == null){
            throw new NotFoundException("该博客不存在！");
        }
        //将标签的数据保存到博客与标签的关联表中
        List<Tag> tags = tagService.getTagByString(blog1.getTagIds());
        BlogAndTag blogAndTag = new BlogAndTag();
        for(Tag tag : tags){
            blogAndTag.setBlogId(blog1.getId());
            blogAndTag.setTagId(tag.getId());
            blogDao.saveBlogAndTag(blogAndTag);
        }
        showBlog.setUpdateTime(new Date());
        return blogDao.updateBlog(showBlog);
    }

    @Transactional
    @Override
    public int deleteBlog(Long id) {
        //在删除博客时，删除对应博客于标签关联表中的数据
        blogDao.deleteBlogAndTag(id);
        return blogDao.deleteBlog(id);
    }

    @Override
    public List<BlogQuery> search(SearchBlog searchBlog) {
        return blogDao.searchByTitleOrTypeOrRecommend(searchBlog);
    }

    @Override
    public void transformRecommend(SearchBlog searchBlog) {
        if (!"".equals(searchBlog.getRecommend()) && null != searchBlog.getRecommend()){
            searchBlog.setRecommend2(1);
        }
    }

    @Override
    public List<FirstPageBlog> getAllFirstPageBlog() {
        return blogDao.getAllFirstPageBlog();
    }

    @Override
    public List<Blog> getALLRecommendBlog() {
        return blogDao.getAllRecommendBlog();
    }

    @Override
    public List<FirstPageBlog> getSearchBlog(String query) {
        return blogDao.getSearchBlog(query);
    }

    @Override
    public DetailedBlog getDetailedBlog(Long id) {
        DetailedBlog blog = blogDao.getDetailedBlog(id);
        String content = blog.getContent();
        //将Markdown文本内容转换为HTML文本

        blog.setContent(MarkdownUtils.markdownToHtmlExtensions(content));
        //当一个博客被访问时，访问量增加
        blogDao.updateViews(id);

        return blog;
    }

    @Override
    public List<FirstPageBlog> getFirstPageBlogByTypeId(Long id) {
        return blogDao.getFirstPageBlogByTypeId(id);
    }

    @Override
    public List<FirstPageBlog> getFirstPageBlogByTagId(Long id) {
        return blogDao.getFirstPageBlogByTagId(id);
    }


}
