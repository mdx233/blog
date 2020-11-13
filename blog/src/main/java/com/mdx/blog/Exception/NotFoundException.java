package com.mdx.blog.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//该注解指定了该异常对应的状态,如果出现该异常网页出现对应状态
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException{

    public NotFoundException(String s) {
        super(s);
    }
}
