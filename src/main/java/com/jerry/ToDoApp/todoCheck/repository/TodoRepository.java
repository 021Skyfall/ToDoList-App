package com.jerry.ToDoApp.todoCheck.repository;

import com.jerry.ToDoApp.todoCheck.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
