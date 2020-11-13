package com.mdx.blog.service;

import com.mdx.blog.pojo.User;

public interface UserService {
    User checkUser(String username , String password);
}
