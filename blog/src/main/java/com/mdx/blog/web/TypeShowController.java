package com.mdx.blog.web;

import com.mdx.blog.pojo.Blog;
import com.mdx.blog.pojo.Type;
import com.mdx.blog.queryvo.FirstPageBlog;
import com.mdx.blog.service.BlogService;
import com.mdx.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TypeShowController {

    @Autowired
    private TypeService typeService;
    @Autowired
    private BlogService blogService;


    @GetMapping("/types/{id}")
    public String getTypeAndBlogSize(@PathVariable("id") Long id, Model model){
        List<Type> Types = typeService.getTypeAndBlogSize();
        //判断是否是从导航链接点击过来的
        if(id == -1){
            //如果是从导航链接点击过来的，就默认展示第一个分类的值
            id = Types.get(0).getId();
        }
        List<FirstPageBlog> firstPageBlogs = blogService.getFirstPageBlogByTypeId(id);
        model.addAttribute("types",Types);
        model.addAttribute("activeTypeId", id);
        model.addAttribute("blogs",firstPageBlogs);
        return "types";
    }

}
