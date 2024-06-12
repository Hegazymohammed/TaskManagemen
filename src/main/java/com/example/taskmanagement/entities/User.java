package com.example.taskmanagement.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Table(name = "users")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String email;
    @OneToMany(mappedBy = "userId",cascade = CascadeType.ALL)
    private List<Task> tasks;

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }
}
