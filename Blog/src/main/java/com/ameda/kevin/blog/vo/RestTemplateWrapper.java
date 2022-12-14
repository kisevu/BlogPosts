package com.ameda.kevin.blog.vo;

import com.ameda.kevin.blog.entity.Blog;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestTemplateWrapper {
    private User user;
    private Blog blog;
}
