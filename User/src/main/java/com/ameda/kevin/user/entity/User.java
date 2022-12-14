package com.ameda.kevin.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(strategy = "uuid",name="uuid")
    @Column(updatable = false,nullable = false)
    @Size(max = 20)
    private String userId;
    private String firstName;
    private String lastName;
    private int age;
    private boolean accountStatus=false;
    private int numberOfSuccessfulLogins;
    private int numberOfFailedLogins;
    private int numberOfPostsMade;
}
