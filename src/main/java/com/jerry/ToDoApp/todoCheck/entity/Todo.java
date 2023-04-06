package com.jerry.ToDoApp.todoCheck.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long todoId;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(length = 100,nullable = false)
    private long todoOrder;

    @Column
    private boolean completed;
}
