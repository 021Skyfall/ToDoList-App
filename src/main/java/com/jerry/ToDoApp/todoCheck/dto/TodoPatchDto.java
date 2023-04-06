package com.jerry.ToDoApp.todoCheck.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

@Getter
public class TodoPatchDto {
    @Setter
    @Positive
    private Long todoId;

    @Pattern(regexp = "^[가-힣]*$",
            message = "한글만 입력 가능합니다.")
    private String title;

    @Positive(message = "횟수는 1회 이상 입니다.")
    private Long todoOrder;

    private Boolean completed;
}
