package com.mdx.blog.web.admin;

import com.mdx.blog.pojo.User;
import com.mdx.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/admin")
public class LoginController {
    @Autowired
    private UserService userService;

    @GetMapping
    public String loginPage(){
        return "admin/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes attributes){
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        User user = userService.checkUser(username,password);

        if (user != null) {
            user.setPassword(null);
            session.setAttribute("user",user);
            return "admin/index";
        }else {
            //注意：不能使用model方法，因为redirect重定向之后，会有新的请求域，model对象不是同一个
            attributes.addFlashAttribute("message","用户名或密码错误");
            return "redirect:/admin";
        }


    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/admin";
    }

}
