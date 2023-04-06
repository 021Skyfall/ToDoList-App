package com.jerry.ToDoApp.todoCheck.controller;

import com.jerry.ToDoApp.todoCheck.dto.MultiResponseDto;
import com.jerry.ToDoApp.todoCheck.dto.TodoPatchDto;
import com.jerry.ToDoApp.todoCheck.dto.TodoPostDto;
import com.jerry.ToDoApp.todoCheck.entity.Todo;
import com.jerry.ToDoApp.todoCheck.mapper.TodoMapper;
import com.jerry.ToDoApp.todoCheck.service.TodoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.constraints.Positive;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/todos")
@AllArgsConstructor
@Validated
@Slf4j
public class TodoController {
    private final TodoService todoService;
    private final TodoMapper mapper;

    // 리스트 등록
    @PostMapping
    public ResponseEntity postTodo(@Validated @RequestBody TodoPostDto todoPostDto) {

        Todo createTodo = todoService.createTodo(mapper.todoPostDtoToTodo(todoPostDto));

        return new ResponseEntity<>(mapper.todoToTodoResponseDto(createTodo), HttpStatus.CREATED);
    }

    // 리스트 수정
    @PatchMapping("/{todo-id}")
    public ResponseEntity patchTodo(@PathVariable("todo-id") @Positive long todoId,
                                    @Validated @RequestBody TodoPatchDto todoPatchDto) {

        todoPatchDto.setTodoId(todoId);

        Todo updateTodo = todoService.updateTodo(mapper.todoPatchDtoToTodo(todoPatchDto));


        return new ResponseEntity<>(mapper.todoToTodoResponseDto(updateTodo),HttpStatus.OK);
    }

    // 리스트 한 건 조회
    @GetMapping("/{todo-id}")
    public ResponseEntity getTodo(@PathVariable("todo-id") @Positive long todoId) {
        Todo todo = todoService.findTodo(todoId);

        return new ResponseEntity<>(mapper.todoToTodoResponseDto(todo),HttpStatus.OK);
    }

    // 리스트 전체 조회 (페이지네이션)
    @GetMapping
    public ResponseEntity getTodos(@Positive @RequestParam int page,
                                   @Positive @RequestParam int size) {
        Page<Todo> todoPage = todoService.findTodos(page -1 , size);
        List<Todo> todos = todoPage.getContent();

        return new ResponseEntity<>(new MultiResponseDto<>(mapper.todosToTodoResponseDto(todos),todoPage),
                HttpStatus.OK);
    }

    // 리스트 삭제
    @DeleteMapping("/{todo-id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTodo(@PathVariable("todo-id") @Positive long todoId) {
        todoService.deleteTodo(todoId);
    }
}
