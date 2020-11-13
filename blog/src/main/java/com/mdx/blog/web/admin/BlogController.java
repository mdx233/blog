package com.mdx.blog.web.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mdx.blog.pojo.Blog;
import com.mdx.blog.pojo.User;
import com.mdx.blog.queryvo.BlogQuery;
import com.mdx.blog.queryvo.SearchBlog;
import com.mdx.blog.queryvo.ShowBlog;
import com.mdx.blog.service.BlogService;
import com.mdx.blog.service.TagService;
import com.mdx.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;


    public void setTypeAndTag(Model model) {
        model.addAttribute("types", typeService.getAllType());
        model.addAttribute("tags", tagService.getAllTag());
    }

    //后台管理博客列表显示
    @GetMapping("/blogs")
    public String blogs(Model model, @RequestParam(defaultValue = "1",value = "pageNum")Integer pageNum){
        PageHelper.startPage(pageNum,3);
        List<BlogQuery> allBlog = blogService.getAllBlogQuery();
        PageInfo<BlogQuery> pageInfo = new PageInfo<>(allBlog);
        setTypeAndTag(model);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/blogs";
    }

    //后台管理博客列表显示
    @PostMapping("/blogs/search")
    public String search(SearchBlog searchBlog, Model model, @RequestParam(defaultValue = "1",value = "pageNum")Integer pageNum){
        //将recommend转换一下
        blogService.transformRecommend(searchBlog);
        PageHelper.startPage(pageNum,6);
        List<BlogQuery> blogBySearch = blogService.search(searchBlog);
        PageInfo<BlogQuery> pageInfo = new PageInfo<>(blogBySearch);
        setTypeAndTag(model);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("message","查询成功");
        return "admin/blogs :: bloglist";
    }

    //跳转到新增页面
    @GetMapping("/blogs/input")
    public String input(Model model){
        setTypeAndTag(model);
        model.addAttribute("blog",new Blog());
        return "admin/blogs-input";
    }

    //跳转到对应编辑页面
    @GetMapping("/blogs/{id}/input")
    public String editinput(@PathVariable("id") Long id, Model model){
        setTypeAndTag(model);
        model.addAttribute("blog",blogService.getBlog(id));
        return "admin/blogs-input";
    }

    //删除对应博客
    @GetMapping("/blogs/{id}/delete")
    public String delete(@PathVariable("id") Long id,RedirectAttributes attributes){
        int b = blogService.deleteBlog(id);
        if (b == 0){
            attributes.addFlashAttribute("message","操作失败");
        }else {
            attributes.addFlashAttribute("message","操作成功");
        }
        return "redirect:/admin/blogs";
    }

    //新增博客
    @PostMapping("/blogs")
    public String post(Blog blog, RedirectAttributes attributes, HttpSession session){
        if("".equals(blog.getFlag())){
            blog.setFlag("原创");
        }
        //添加用户信息
        blog.setUser((User) session.getAttribute("user"));
        //设置用户id
        blog.setUserId(blog.getUser().getId());
        //添加类型信息
        blog.setType(typeService.getType(blog.getType().getId()));
        //添加typeid
        blog.setTypeId(blog.getType().getId());
        //给blog类中的list<Tag>赋值
        blog.setTags(tagService.getTagByString(blog.getTagIds()));
        int b = blogService.saveBlog(blog);
        if (b == 0){
            attributes.addFlashAttribute("message","操作失败");
        }else {
            attributes.addFlashAttribute("message","操作成功");
        }

        return "redirect:/admin/blogs";
    }

    //修改博客时的操作
    @PostMapping("/blogs/{id}")
    public String update(@PathVariable("id")Long id, ShowBlog blog, RedirectAttributes attributes, HttpSession session){
        blog.setId(id);
        int b = blogService.updateBlog(blog);
        if (b == 0){
            attributes.addFlashAttribute("message","操作失败");
        }else {
            attributes.addFlashAttribute("message","操作成功");
        }

        return "redirect:/admin/blogs";
    }


}
