package com.mdx.blog.service.impl;

import com.mdx.blog.dao.CommentDao;
import com.mdx.blog.pojo.Comment;
import com.mdx.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;

    //存放迭代找出的所有子代的集合
    private List<Comment> tempReplys = new ArrayList<>();

    /**
     * 显示所有评论
     * @param blogId
     * @return
     */
    @Override
    public List<Comment> listCommentByBlogId(Long blogId) {
        //先将所有顶级评论放入集合中
        List<Comment> comments =  commentDao.findByBlogIdParentIdNull(blogId,Long.parseLong("-1"));
        //遍历所有顶级评论查找是否有子级评论
        for (Comment parentComment: comments) {
            //获取顶级评论的id用户查找其自己评论
            Long parentId = parentComment.getId();
            //获取顶级评论用户名用于前端展示
            String parentNickName = parentComment.getNickname();
            //找出该顶级评论的子级评论
            List<Comment> childComments = commentDao.findByParentCommentId(parentId);
            //判断是否有子级评论
            if(childComments.size() > 0){
                //使用函数查找该子级评论是否有子级评论，并将所有子级评论合并为一个集合
                combineChildren(childComments,parentNickName);
                //在合并完成后将子级评论集合放入顶级评论的集合中
                parentComment.setReplayComment(tempReplys);
                //在这个顶级评论的所有子级评论存放完成后,创建一个新的集合用于存放下一个顶级节点的所有子级节点
                tempReplys = new ArrayList<>();
            }
        }

        return comments;
    }

    /**
     * 将所有子级评论存放到子级评论存放集合中
     * @param childComments
     */
    public void combineChildren(List<Comment> childComments,String parentNickName){
        for(Comment childComment : childComments){
            childComment.setParentNickname(parentNickName);
            //将其加入子级评论存放的集合中
            tempReplys.add(childComment);
            //递归出其所有子级评论
            recursively(childComment);
        }
    }

    /**
     * 迭代找出所有子级评论,并将其放入存放子级的集合中
     * @param parentComment
     */
    public void recursively(Comment parentComment){
        //查找该子级评论的子二级评论
        List<Comment> forChildComments = commentDao.findByParentCommentId(parentComment.getId());
        //判断该子级评论是否有子二级评论
        if(forChildComments.size()>0){
            //当该子评论存在其子二级评论时，遍历该子二级评论
            for (Comment childComment : forChildComments){
                //保存其父评论的评论人名称
                childComment.setParentNickname(parentComment.getNickname());
                //将该节点存入集合中
                tempReplys.add(childComment);
                //递归查看其是否有子级评论
                recursively(childComment);
            }
        }
    }

    /**
     * 新增评论
     * @param comment
     * @return
     */
    @Override
    public int saveComment(Comment comment) {
        comment.setCreateTime(new Date());
        return commentDao.saveComment(comment);
    }

    @Override
    public int deleteComment(Long id) {
        return commentDao.deleteComment(id);
    }
}
