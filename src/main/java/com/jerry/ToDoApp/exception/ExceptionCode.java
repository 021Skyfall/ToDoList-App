package com.jerry.ToDoApp.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionCode {
    TODO_NOT_FOUND(404, "Todo List가 존재하지 않습니다.");

    private int status;
    private String message;
}
