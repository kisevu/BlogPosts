package com.ameda.kevin.blog.service;

import com.ameda.kevin.blog.entity.Blog;
import com.ameda.kevin.blog.model.BlogModel;
import com.ameda.kevin.blog.repository.BlogRepository;
import com.ameda.kevin.blog.vo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Slf4j
@Transactional
public class BlogService {
    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Modifying
    public void createBlog(BlogModel blogModel) {
        Blog blog=Blog.builder()
                .postTitle(blogModel.getPostTitle())
                .postDate(blogModel.getPostDate())
                .postAuthorId(blogModel.getPostAuthorId())
                .build();
        User user=restTemplate.getForObject("http://localhost:8082/api/users"+
                blog.getPostAuthorId(),User.class);
        if(user!=null && user.isAccountStatus()){
            blogRepository.save(blog);
            log.info("Blog created successfully.");
            return;
        }
        log.info("login in to create a blog post.");
        return;
    }
    public Blog getBlog(String postId) {
        return blogRepository.findByBlogPostId(postId);
    }
    public List<Blog> readBlogs(String postAuthorId) {
        User user=restTemplate.getForObject("http://localhost:8082/api/users"+
                postAuthorId,User.class);
        if(user.isAccountStatus()){
            log.info("while author is logged in.");
            return blogRepository.findAll();
        }
        log.info("please login in first to see the blog posts.");
        return null;
    }

    @Modifying
    public Blog updateBlog(String postTitle, String postAuthorId, String postId) {
        User user=restTemplate.getForObject("http://localhost:8082/api/users"+
                postAuthorId,User.class);
        if(user.isAccountStatus()){
            log.info("if you are signed in then proceed.");
            Blog blog=blogRepository.findByBlogPostId(postId);
            blog.setPostTitle(postTitle);
            blogRepository.save(blog);
            return blog;
        }
        log.info("Sign in first to proceed.");
        return null;
    }

    @Modifying
    public void deleteBlog(String postAuthorId,String postId) {
        Blog blog=blogRepository.findByBlogPostId(postId);
        User user=restTemplate.getForObject("http://localhost:8082/api/users"+
                postAuthorId,User.class);
        if(user.isAccountStatus()){
            blogRepository.delete(blog);
            log.info("Blog post successfully deleted.");
            return;
        }
        log.info("log in first");
        return;
    }
}
