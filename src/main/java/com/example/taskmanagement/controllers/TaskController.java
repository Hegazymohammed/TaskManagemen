package com.example.taskmanagement.controllers;

import com.example.taskmanagement.dtos.AllTasksResponse;
import com.example.taskmanagement.dtos.CreateTaskDto;
import com.example.taskmanagement.entities.Task;
import com.example.taskmanagement.entities.User;
import com.example.taskmanagement.exceptions.UserNotFoundException;
import com.example.taskmanagement.repositories.TaskRepository;
import com.example.taskmanagement.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path ="/task/")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(  TaskService taskService) {
        this.taskService = taskService;


    }
    @PostMapping("{userId}")
    ResponseEntity<String> addTask(@PathVariable int userId, @RequestBody CreateTaskDto taskRequest) throws IOException {


       String link= taskService.createTask(userId,taskRequest); // Save the actual task object

        return ResponseEntity.status(HttpStatus.OK).body(link) ;
    }
    @PutMapping("{userId}/{taskId}")
    ResponseEntity<String> updateTask(@PathVariable int userId, @PathVariable int taskId,@RequestBody CreateTaskDto dto) throws IOException {


        String link= taskService.updateTask(userId,taskId,dto); // Save the actual task object

        return ResponseEntity.status(HttpStatus.OK).body(link) ;
    }

    @DeleteMapping ("{userId}/{taskId}")
    ResponseEntity<?>  deleteTask(@PathVariable int userId, @PathVariable int taskId  ) throws IOException {
        taskService.deleteTask(userId,taskId); // Save the actual task object

        return ResponseEntity.status(HttpStatus.OK).build() ;
    }
    @GetMapping("{userId}/")
    ResponseEntity<List<AllTasksResponse>>   findAll(@PathVariable int userId ) throws IOException {
         List<AllTasksResponse>tasks=taskService.findAll(); // Save the actual task object

        return ResponseEntity.status(HttpStatus.OK).body(tasks)  ;
    }

}
