package com.ameda.kevin.user.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Request {
    private String userId;
    private String lastName;
}
