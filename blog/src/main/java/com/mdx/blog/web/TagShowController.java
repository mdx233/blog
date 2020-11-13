package com.mdx.blog.web;

import com.mdx.blog.pojo.Tag;
import com.mdx.blog.pojo.Type;
import com.mdx.blog.queryvo.FirstPageBlog;
import com.mdx.blog.service.BlogService;
import com.mdx.blog.service.TagService;
import com.mdx.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class TagShowController {

    @Autowired
    private TagService tagService;
    @Autowired
    private BlogService blogService;


    @GetMapping("/tags/{id}")
    public String getTagAndBlogSize(@PathVariable("id") Long id, Model model){
        List<Tag> Tags = tagService.getTypeAndBlogSize();
        //判断是否是从导航链接点击过来的
        if(id == -1){
            //如果是从导航链接点击过来的，就默认展示第一个分类的值
            id = Tags.get(0).getId();
        }
        List<FirstPageBlog> firstPageBlogs = blogService.getFirstPageBlogByTagId(id);
        model.addAttribute("tags",Tags);
        model.addAttribute("activeTagId", id);
        model.addAttribute("blogs",firstPageBlogs);
        return "tags";
    }

}
