package com.mdx.blog.service;

import com.mdx.blog.pojo.Tag;
import com.mdx.blog.pojo.Type;

import java.util.List;

public interface TagService {
    int saveTag(Tag tag);

    Tag getTag(Long id);

    Tag getTagByName(String name);

    List<Tag> getAllTag();

    List<Tag> getTagAndBlog();

    int updateTag(Tag tag);

    int deleteTag(Long id);

    List<Tag> getTagByString(String id);

    List<Tag> getTypeAndBlogSize();
}
