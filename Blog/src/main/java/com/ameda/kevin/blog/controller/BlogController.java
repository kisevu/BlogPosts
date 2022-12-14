package com.ameda.kevin.blog.controller;

import com.ameda.kevin.blog.config.MyBlogUserDetailsService;
import com.ameda.kevin.blog.config.Request;
import com.ameda.kevin.blog.config.Response;
import com.ameda.kevin.blog.config.Utility;
import com.ameda.kevin.blog.entity.Blog;
import com.ameda.kevin.blog.model.BlogModel;
import com.ameda.kevin.blog.service.BlogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blogs")
@Slf4j
public class BlogController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private Utility utility;
    @Autowired
    private MyBlogUserDetailsService myBlogUserDetailsService;
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
    @PostMapping("/auth")
    public Response auth(@RequestBody Request request) throws Exception{
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUserId(),
                            request.getLastName())
            );
        }catch (BadCredentialsException ex){
            throw new Exception("Invalid credentials",ex);
        }
        final UserDetails userDetails=myBlogUserDetailsService.loadUserByUsername(request.getUserId());
        final String token=utility.generateToken(userDetails);
        return new Response(token);
    }
}
