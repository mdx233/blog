package com.mdx.blog.dao;

import com.mdx.blog.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentDao {

    //根据时间倒序来排 注意：当参数有两个及以上时，再参数前加上@Param注解,否则报错
    List<Comment> findByBlogIdParentIdNull(@Param("blogId") Long blogId,@Param("blogParentId") Long blogParentId);
    //查询子评论
    List<Comment> findByParentCommentId(Long parentCommentId);
    //添加一个评论
    int saveComment(Comment comment);
    //删除一个评论
    int deleteComment(Long id);

}
