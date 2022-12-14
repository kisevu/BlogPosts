package com.ameda.kevin.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {
    private String firstName;
    private String lastName;
    private int age;
}
