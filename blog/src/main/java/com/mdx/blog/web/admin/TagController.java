package com.mdx.blog.web.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mdx.blog.dao.TagDao;
import com.mdx.blog.pojo.Tag;
import com.mdx.blog.pojo.Type;
import com.mdx.blog.service.TagService;
import com.mdx.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    private TagService tagService;

    //分页查询分类列表
    @GetMapping("/tags")
    public String list(Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        //按照排序字段 倒序 排序
        String orderBy = "id desc";
        PageHelper.startPage(pageNum,3,orderBy);
        List<Tag> allTag = tagService.getAllTag();
        PageInfo<Tag> pageInfo = new PageInfo<>(allTag);

        //将分页结果封装到属性中
        model.addAttribute("pageInfo",pageInfo);
        return "admin/tags";
    }

    //分类发布页面
    @GetMapping("/tags/input")
    public String input(Model model){
        model.addAttribute("tag",new Tag());
        return "admin/tag-input";
    }

    //分类新增请求
    @GetMapping("/tags/{id}/input")
    public String editInput (@PathVariable("id") Long id, Model model){
        model.addAttribute("tag",tagService.getTag(id));
       return "admin/tag-input";
    }

    //提交新增分类的请求
    @PostMapping("/tags")
    public String post(@Valid Tag tag, BindingResult result, RedirectAttributes attributes){
        Tag tag1 = tagService.getTagByName(tag.getName());
        if(tag1 != null){
            result.rejectValue("name","nameError","不能添加重复标签");
        }
        if(result.hasErrors()){
            return "admin/tag-input";
        }

        int i =  tagService.saveTag(tag);
        if (i == 0){
            attributes.addFlashAttribute("message","新增失败");
        }else{
            attributes.addFlashAttribute("message","新增成功");
        }
        return "redirect:/admin/tags";
    }

    //提交修改分类的请求
    @PostMapping("/tags/{id}")
    public String editPost(@Validated Tag tag, BindingResult result, RedirectAttributes attributes){

        Tag tag1 = tagService.getTagByName(tag.getName());
        if(tag1 != null){
            result.rejectValue("name","nameError","不能添加重复标签");
        }
        if(result.hasErrors()){
            return "admin/tag-input";
        }

        int i =  tagService.updateTag(tag);
        if (i == 0){
            attributes.addFlashAttribute("message","更新失败");
        }else{
            attributes.addFlashAttribute("message","更新成功");
        }
        return "redirect:/admin/tags";
    }

    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable("id") Long id,RedirectAttributes attributes){
        int i = tagService.deleteTag(id);
        if (i == 0){
            attributes.addFlashAttribute("message","删除失败");
        }else{
            attributes.addFlashAttribute("message","删除成功");
        }
        return "redirect:/admin/tags";
    }

}
