package com.ameda.kevin.user.controller;

import com.ameda.kevin.user.entity.User;
import com.ameda.kevin.user.model.UserModel;
import com.ameda.kevin.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signUp")
    public void registerUser(@RequestBody UserModel userModel){
        userService.registerUser(userModel);
        log.info("successfully registered a user.");
    }
    @PostMapping("/login")
    public User sign_in(@RequestParam("userId") String userId){
        User user=userService.sign_in(userId);
        log.info("you are successfully logged in.");
        return user;
    }
    @GetMapping("/{userId}")
    public User getUser(@PathVariable("userId") String userId){
        return  userService.getUser(userId);
    }
    @DeleteMapping("/delete/{userId}")
    public void deleteUser(@PathVariable("userId") String userId){
        userService.deleteUser(userId);
    }
}
