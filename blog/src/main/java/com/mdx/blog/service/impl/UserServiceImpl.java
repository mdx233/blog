package com.mdx.blog.service.impl;

import com.mdx.blog.dao.UserDao;
import com.mdx.blog.pojo.User;
import com.mdx.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User checkUser(String username, String password) {
        User user = userDao.findByUsernameAndPassword(username,password);
        return user;
    }
}
