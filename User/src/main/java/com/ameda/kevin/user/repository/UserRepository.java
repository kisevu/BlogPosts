package com.ameda.kevin.user.repository;

import com.ameda.kevin.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
    User findByUserId(String userId);

    org.springframework.security.core.userdetails.User findByLastName(String lastName);
}
