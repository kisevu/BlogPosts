package com.ameda.kevin.blog.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String userId;
    private String firstName;
    private String lastName;
    private int age;
    private boolean accountStatus=false;
    private int numberOfSuccessfulLogins;
    private int numberOfFailedLogins;
    private int numberOfPostsMade;
}
