package com.mdx.blog.config;

import com.mdx.blog.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册自定义拦截器
        registry.addInterceptor(new LoginInterceptor())
                //设置拦截路径
                .addPathPatterns("/admin/**")
                //添加需要排除的路径
                .excludePathPatterns(new String[]{"/admin","/admin/login"});
    }
}
