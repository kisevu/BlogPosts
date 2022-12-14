package com.ameda.kevin.blog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogModel {
    private String postTitle;
    private Date postDate;
    private String postAuthorId;
}
