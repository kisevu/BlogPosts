package com.ameda.kevin.blog.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Blog {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(strategy = "uuid",name="uuid")
    @Column(updatable = false,nullable = false)
    @Size(max = 20)
    private String postId;
    @Size(max=40)
    private String postTitle;
    @Temporal(TemporalType.DATE)
    private Date postDate;
    private String postAuthorId;
}
