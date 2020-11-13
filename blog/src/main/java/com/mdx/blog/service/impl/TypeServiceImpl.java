package com.mdx.blog.service.impl;

import com.mdx.blog.Exception.NotFoundException;
import com.mdx.blog.dao.TypeDao;
import com.mdx.blog.pojo.Type;
import com.mdx.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeDao typeDao;

    @Transactional
    @Override
    public int saveType(Type type) {
        return typeDao.saveType(type);
    }

    @Override
    public Type getType(Long id) {
        return typeDao.getType(id);
    }

    @Override
    public List<Type> getAllType() {
        return typeDao.getAllType();
    }

    @Override
    public Type getTypeByName(String name) {
        return typeDao.getTypeByName(name);
    }

    @Override
    public List<Type> getTypeAndBlog() {
        return typeDao.getTypeAndBlog();
    }

    @Transactional
    @Override
    public int updateType(Type type) {
        return typeDao.updateType(type);
    }

    @Transactional
    @Override
    public int deleteType(Long id) {
        return typeDao.deleteType(id);
    }

    @Override
    public List<Type> getTypeAndBlogSize() {
        return typeDao.getAllTypeAndBlogSize();
    }
}
