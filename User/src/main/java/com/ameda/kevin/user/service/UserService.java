package com.ameda.kevin.user.service;

import com.ameda.kevin.user.entity.User;
import com.ameda.kevin.user.model.UserModel;
import com.ameda.kevin.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Modifying
    public void registerUser(UserModel userModel) {
        User user=User.builder()
                .firstName(userModel.getFirstName())
                .lastName(userModel.getLastName())
                .age(userModel.getAge())
                .accountStatus(false)
                .numberOfFailedLogins(0)
                .numberOfPostsMade(0)
                .numberOfSuccessfulLogins(0)
                .build();
        if(user!=null){
            log.info("user is equal to null.");
            userRepository.save(user);
            return;
        }
        log.info("user is null.");
        return;
    }

    public User sign_in(String userId) {
        User user=userRepository.findByUserId(userId);
        if(user.getUserId()==userId){
            log.info("They match "+ user.getLastName()+ ", you're logged in.");
            return user;
        }
        log.info("They didn't match, you are not logged in");
        return null;
    }

    public User getUser(String userId) {
        return userRepository.findByUserId(userId);
    }

    @Modifying
    public void deleteUser(String userId) {
        User user=userRepository.findByUserId(userId);
        if(user!=null){
            userRepository.delete(user);
            log.info("User deleted succcesfully.");
            return;
        }
    }
}
