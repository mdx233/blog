package com.mdx.blog.dao;

import com.mdx.blog.pojo.Tag;
import com.mdx.blog.pojo.Type;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TagDao {
    int saveTag(Tag tag);

    Tag getTag(Long id);

    Tag getTagByName(String name);

    List<Tag> getAllTag();

    List<Tag> getTagAndBlog();

    int updateTag(Tag tag);

    int deleteTag(Long id);

    List<Tag> getTypeAndBlogSize();

}
