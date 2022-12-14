package com.ameda.kevin.blog.config;

import com.ameda.kevin.blog.entity.Blog;
import com.ameda.kevin.blog.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class MyBlogUserDetailsService implements UserDetailsService {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private BlogRepository  blogRepository;
    @Override
    public UserDetails loadUserByUsername(String postId) throws UsernameNotFoundException {
       Blog blog=blogRepository.findByBlogPostId(postId);
       return new MyUserDetails(blog.getPostId(),blog.getPostTitle());
    }
}
