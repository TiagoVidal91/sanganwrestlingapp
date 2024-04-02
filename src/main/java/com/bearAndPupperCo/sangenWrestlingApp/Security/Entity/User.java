package com.bearAndPupperCo.sangenWrestlingApp.Security.Entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "platform_user",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "user_id")
        private Long id;

        private String username;

        private String email;

        private String password;

        private LocalDate creatingDate;

        @ManyToMany
        @JoinTable(name = "user_roles",
                joinColumns = @JoinColumn(name = "user_id"),
                inverseJoinColumns = @JoinColumn(name = "role_id"))
        private Set<Role> roles = new HashSet<>();

        public User(String username, String email, String password, LocalDate creatingDate) {
                this.username = username;
                this.email = email;
                this.password = password;
                this.creatingDate = creatingDate;
        }
}
