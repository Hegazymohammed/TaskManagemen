package com.example.taskmanagement.controllers;

import com.example.taskmanagement.dtos.CreateUserDto;
import com.example.taskmanagement.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/")
    private ResponseEntity<?>addUser(@RequestBody CreateUserDto userRequest){
           userService.addUser(userRequest);

            return ResponseEntity.status(HttpStatus.OK).build();
    }
    @GetMapping("/")
    private List<CreateUserDto> findAll(){
        return userService.findAll();
    }


}
