package com.example.taskmanagement.repositories;

import com.example.taskmanagement.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Integer> {
    @Query("select t from Task  t where t.userId.id=:id")
    List<Task> findTasksByUserid(int id);
}
