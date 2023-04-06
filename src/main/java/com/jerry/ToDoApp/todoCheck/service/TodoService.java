package com.jerry.ToDoApp.todoCheck.service;

import com.jerry.ToDoApp.todoCheck.entity.Todo;
import com.jerry.ToDoApp.exception.BusinessLogicException;
import com.jerry.ToDoApp.exception.ExceptionCode;
import com.jerry.ToDoApp.todoCheck.repository.TodoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class TodoService {
    private final TodoRepository todoRepository;

    public Todo createTodo(Todo todo) {

        if (todo.isCompleted()) {
            todo.setCompleted(true);
        }
        return todoRepository.save(todo);
    }

    public Todo updateTodo(Todo todo) {
        Todo findTodo = findVerifiedTodo(todo.getTodoId());

        Optional.ofNullable(todo.getTitle())
                .ifPresent(findTodo::setTitle);
        Optional.of(todo.getTodoOrder())
                .ifPresent(findTodo::setTodoOrder);

        if (!todo.isCompleted()) {
            findTodo.setCompleted(false);
        }

        if (todo.isCompleted()) {
            findTodo.setCompleted(true);
        }

        return todoRepository.save(findTodo);
    }

    public Todo findTodo(long todoId) {
        return findVerifiedTodo(todoId);
    }

    public Page<Todo> findTodos(int page, int size) {
        return todoRepository.findAll(PageRequest.of(
                page, size, Sort.by("todoId").ascending()));
    }

    public void deleteTodo(long todoId) {
        todoRepository.delete(findVerifiedTodo(todoId));
    }

    private Todo findVerifiedTodo(long todoId) {
        Optional<Todo> todo = todoRepository.findById(todoId);
        return todo.orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.TODO_NOT_FOUND));
    }
}
