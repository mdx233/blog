package com.mdx.blog.web;

import com.mdx.blog.dao.BlogDao;
import com.mdx.blog.pojo.Comment;
import com.mdx.blog.pojo.User;
import com.mdx.blog.service.BlogService;
import com.mdx.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private BlogService blogService;
    @Value("${comment.avatar}")
    private String avatar;

    //查询评论列表
    @GetMapping("/comments/{blogId}")
    public String comments  (@PathVariable("blogId") Long blogId, Model model){
        List<Comment> comments = commentService.listCommentByBlogId(blogId);
        model.addAttribute("comments",comments);
        return "blog :: commentList";
    }


    //新增评论
    @PostMapping("/comments")
    public String post(Comment comment, HttpSession session,Model model){
        Long blogId = comment.getBlogId();
        comment.setBlog(blogService.getBlog(blogId));
        User user = (User) session.getAttribute("user");

        if (user != null) {
            comment.setAvatar(user.getAvatar());
            comment.setAdminComment(1);
        }else {
            comment.setAvatar(avatar);
            comment.setAdminComment(0);
        }

        if (comment.getParentComment().getId() != null){
            comment.setParentCommentId(comment.getParentComment().getId());
        }

        commentService.saveComment(comment);
        List<Comment> comments = commentService.listCommentByBlogId(blogId);
        model.addAttribute("comments", comments);
        return "blog :: commentList";
    }

    //删除评论
    @PostMapping("/comment/delete")
    public String delete(Comment comment,Model model){
        commentService.deleteComment(comment.getId());
        List<Comment> comments = commentService.listCommentByBlogId(comment.getBlogId());
        model.addAttribute("comments", comments);
        return "blog :: commentList";
    }
}
