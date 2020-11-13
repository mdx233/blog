package com.mdx.blog.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mdx.blog.Exception.NotFoundException;
import com.mdx.blog.pojo.Blog;
import com.mdx.blog.pojo.Tag;
import com.mdx.blog.pojo.Type;
import com.mdx.blog.queryvo.DetailedBlog;
import com.mdx.blog.queryvo.FirstPageBlog;
import com.mdx.blog.service.BlogService;
import com.mdx.blog.service.TagService;
import com.mdx.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

//前台显示博客首页
@Controller
public class indexController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TagService tagService;

    @Autowired
    private TypeService typeService;


    @RequestMapping("/")
    public String index1(Model model, @RequestParam(defaultValue = "1",value = "pageNum")Integer pageNum){
        PageHelper.startPage(pageNum,3);
        List<FirstPageBlog> firstPageBlog = blogService.getAllFirstPageBlog();
        PageInfo<FirstPageBlog> pageInfo = new PageInfo<>(firstPageBlog);
        model.addAttribute("pageInfo",pageInfo);
        //获取标签列表
        List<Tag> allTag = tagService.getTagAndBlog();
        //获取分类列表
        List<Type> allType = typeService.getTypeAndBlog();
        //获取推荐博客列表
        List<Blog> recommendBlog = blogService.getALLRecommendBlog();
        //将值添加到request域中
        model.addAttribute("types",allType);
        model.addAttribute("tags",allTag);
        model.addAttribute("recommendBlog",recommendBlog);

        return "index";
    }

    @RequestMapping("/search")
    public String search(Model model,
                         String query,
                         @RequestParam(defaultValue = "1",value = "pageNum")Integer pageNum){
        PageHelper.startPage(pageNum,100);
        List<FirstPageBlog> serachBlog = blogService.getSearchBlog(query);
        if (serachBlog.size() == 0) {
            model.addAttribute("message","无法搜索到相关博客");
        }
        PageInfo<FirstPageBlog> pageInfo = new PageInfo<>(serachBlog);
        model.addAttribute("pageInfo",pageInfo);
        //获取标签列表
        List<Tag> allTag = tagService.getTagAndBlog();
        //获取分类列表
        List<Type> allType = typeService.getTypeAndBlog();
        //获取推荐博客列表
        List<Blog> recommendBlog = blogService.getALLRecommendBlog();
        //将值添加到request域中
        model.addAttribute("types",allType);
        model.addAttribute("tags",allTag);
        model.addAttribute("recommendBlog",recommendBlog);
        model.addAttribute("query",query);
        return "search";
    }

    @GetMapping("/blog/{id}")
    public String blog(@PathVariable("id") Long id,Model model){
        DetailedBlog blog = blogService.getDetailedBlog(id);
        model.addAttribute("blog",blog);

        return "blog";
    }


    @GetMapping("/about")
    public String about(){
        return "about";
    }


    @GetMapping("/footer")
    public String footer(Model model){
        //获取推荐博客列表
        List<Blog> recommendBlog = blogService.getALLRecommendBlog();
        recommendBlog = recommendBlog.subList(0,3);
        model.addAttribute("foot",recommendBlog);
        return "index :: footerList";
    }

}
