package com.jerry.ToDoApp.todoCheck.mapper;

import com.jerry.ToDoApp.todoCheck.dto.TodoPatchDto;
import com.jerry.ToDoApp.todoCheck.dto.TodoPostDto;
import com.jerry.ToDoApp.todoCheck.dto.TodoResponseDto;
import com.jerry.ToDoApp.todoCheck.entity.Todo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TodoMapper {
    Todo todoPostDtoToTodo(TodoPostDto todoPostDto);
    Todo todoPatchDtoToTodo(TodoPatchDto todoPatchDto);
    TodoResponseDto todoToTodoResponseDto(Todo todo);
    List<TodoResponseDto> todosToTodoResponseDto(List<Todo> todos);
}
