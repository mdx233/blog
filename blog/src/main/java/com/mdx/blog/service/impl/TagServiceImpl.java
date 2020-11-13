package com.mdx.blog.service.impl;

import com.mdx.blog.dao.TagDao;
import com.mdx.blog.pojo.Tag;
import com.mdx.blog.pojo.Type;
import com.mdx.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagDao tagDao;

    @Override
    public int saveTag(Tag tag) {
        return tagDao.saveTag(tag);
    }

    @Override
    public Tag getTag(Long id) {
        return tagDao.getTag(id);
    }

    @Override
    public Tag getTagByName(String name) {
        return tagDao.getTagByName(name);
    }

    @Override
    public List<Tag> getAllTag() {
        return tagDao.getAllTag();
    }

    @Override
    public List<Tag> getTagAndBlog() {
        return tagDao.getTagAndBlog();
    }

    @Override
    public int updateTag(Tag tag) {
        return tagDao.updateTag(tag);
    }

    @Override
    public int deleteTag(Long id) {
        return tagDao.deleteTag(id);
    }

    @Override
    public List<Tag> getTagByString(String id) {
        List<Tag> tags = new ArrayList<>();
        List<Long> longs = convertToList(id);
        for(Long along : longs){
            tags.add(tagDao.getTag(along));
        }
        return tags;
    }

    @Override
    public List<Tag> getTypeAndBlogSize() {
        return tagDao.getTypeAndBlogSize();
    }

    private List<Long> convertToList(String id){
        List<Long> longs = new ArrayList<>();
        if(!"".equals(id) && id !=null){
            String[] idarray = id.split(",");
            for(String theid : idarray){
                longs.add(new Long(theid));
            }
        }
        return longs;
    }
}
