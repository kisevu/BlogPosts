package com.ameda.kevin.blog.controller;

import com.ameda.kevin.blog.entity.Blog;
import com.ameda.kevin.blog.model.BlogModel;
import com.ameda.kevin.blog.service.BlogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blogs")
@Slf4j
public class BlogController {

    @Autowired
    private BlogService blogService;

    @PostMapping("/create-blog")
    public void createBlog(@RequestBody BlogModel blogModel){
        blogService.createBlog(blogModel);
        log.info("blog successfully created.");
    }

    @GetMapping("/read/{postId}")
    public Blog getBlog(@PathVariable("postId") String postId){
        return blogService.getBlog(postId);
    }
    @GetMapping("/read-blog/{postAuthorId}")
    public List<Blog> readBlog(String postAuthorId){
        return blogService.readBlogs(postAuthorId);
    }
    @PatchMapping("/{postId}")
    public Blog updateBlog(@RequestParam("postTitle") String postTitle,
                           @RequestParam("postAuthorId") String postAuthorId,
                           @PathVariable("postId") String postId){
        return blogService.updateBlog(postTitle,postAuthorId,postId);
    }
    @DeleteMapping("/delete/{postId}")
    public void deleteBlog(@RequestParam("postAuthorId") String postAuthorId,
                           @PathVariable("postId") String postId){
        blogService.deleteBlog(postAuthorId,postId);
        return;
    }
}
