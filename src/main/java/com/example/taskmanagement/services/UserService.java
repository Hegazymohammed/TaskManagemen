package com.example.taskmanagement.services;

import com.example.taskmanagement.dtos.CreateUserDto;
import com.example.taskmanagement.entities.User;
import com.example.taskmanagement.exceptions.UserAlreadyExistException;
import com.example.taskmanagement.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addUser(CreateUserDto userRequest){
        Optional<User> user=userRepository.findUsersByEmail(userRequest.getEmail());
        System.out.println(userRequest.toString());
        if(user.isPresent())
            throw new UserAlreadyExistException("this account is already exist");
        else {
            User newUser = userRepository.save(new User(userRequest.getName(), userRequest.getEmail()));
            System.out.println(newUser.getId());
        }
    }

    public List<CreateUserDto> findAll() {
        List<User>users=userRepository.findAll();

        return users.stream().map(user-> new CreateUserDto(user.getUsername(),user.getEmail())).collect(Collectors.toList());
    }
}
