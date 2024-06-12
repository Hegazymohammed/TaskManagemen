package com.example.taskmanagement.repositories;

import com.example.taskmanagement.entities.Task;
import com.example.taskmanagement.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public  interface   UserRepository extends JpaRepository<User, Task> {
    Optional<User> findUsersByEmail(String Email);

    Optional<User> findUsersById(int userId);
}
