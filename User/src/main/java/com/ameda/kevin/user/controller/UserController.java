package com.ameda.kevin.user.controller;

import com.ameda.kevin.user.config.MyUserDetailsService;
import com.ameda.kevin.user.config.Utility;
import com.ameda.kevin.user.config.Request;
import com.ameda.kevin.user.config.Response;
import com.ameda.kevin.user.entity.User;
import com.ameda.kevin.user.model.UserModel;
import com.ameda.kevin.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private Utility utility;
    @Autowired
    private MyUserDetailsService myUserDetailsService;

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
        final UserDetails userDetails=myUserDetailsService.loadUserByUsername(request.getUserId());
        final String token=utility.generateToken(userDetails);
        return new Response(token);
    }
}
