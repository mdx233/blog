package com.mdx.blog.web.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mdx.blog.pojo.Type;
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
public class TypeController {

    @Autowired
    private TypeService typeService;

    //分页查询分类列表
    @GetMapping("/types")
    public String list(Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        //按照排序字段 倒序 排序
        String orderBy = "id desc";
        PageHelper.startPage(pageNum,3,orderBy);
        List<Type> allType = typeService.getAllType();
        PageInfo<Type> pageInfo = new PageInfo<>(allType);

        //将分页结果封装到属性中
        model.addAttribute("pageInfo",pageInfo);
        return "admin/types";
    }

    //分类发布页面
    @GetMapping("/types/input")
    public String input(Model model){
        model.addAttribute("type",new Type());
        return "admin/type-input";
    }

    //分类新增请求
    @GetMapping("/types/{id}/input")
    public String editInput (@PathVariable("id") Long id, Model model){
        model.addAttribute("type",typeService.getType(id));
       return "admin/type-input";
    }

    //提交新增分类的请求
    @PostMapping("/types")
    public String post(@Validated Type type, BindingResult result, RedirectAttributes attributes){
        Type type1 = typeService.getTypeByName(type.getName());
        if(type1 != null){
            result.rejectValue("name","nameError","不能添加重复分类");
        }
        if(result.hasErrors()){
            return "admin/type-input";
        }
        System.out.println(type.getName());
        int i =  typeService.saveType(type);
        if (i == 0){
            attributes.addFlashAttribute("message","新增失败");
        }else{
            attributes.addFlashAttribute("message","新增成功");
        }
        return "redirect:/admin/types";
    }

    //提交修改分类的请求
    @PostMapping("/types/{id}")
    public String editPost(@Validated Type type, BindingResult result, RedirectAttributes attributes){

        Type type1 = typeService.getTypeByName(type.getName());
        if(type1 != null){
            result.rejectValue("name","nameError","需要对名字做出修改");
        }
        if(result.hasErrors()){
            return "admin/type-input";
        }
        System.out.println(type);
        int i =  typeService.updateType(type);
        if (i == 0){
            attributes.addFlashAttribute("message","更新失败");
        }else{
            attributes.addFlashAttribute("message","更新成功");
        }
        return "redirect:/admin/types";
    }

    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable("id") Long id,RedirectAttributes attributes){
        int i = typeService.deleteType(id);
        if (i == 0){
            attributes.addFlashAttribute("message","删除失败");
        }else{
            attributes.addFlashAttribute("message","删除成功");
        }
        return "redirect:/admin/types";
    }

}
