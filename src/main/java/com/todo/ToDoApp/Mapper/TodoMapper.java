package com.todo.ToDoApp.Mapper;

import com.todo.ToDoApp.DTO.TodoDto;
import com.todo.ToDoApp.Entity.Todo;

public class TodoMapper {
    public static TodoDto mapToTodoDto(Todo todo){
        return new TodoDto(
                todo.getId(),
                todo.getUserId(),
                todo.getTitle(),
                todo.getDescription(),
                todo.getStatus(),
                todo.getCreatedAt(),
                todo.getUpdatedAt(),
                todo.getDueDate(),
                todo.getPriority()
        );
    }

    public static Todo mapToTodo(TodoDto todoDto){
        Todo todo = new Todo();
        todo.setId(todoDto.getId());
        todo.setUserId(todoDto.getUserId());
        todo.setTitle(todoDto.getTitle());
        todo.setDescription(todoDto.getDescription());
        todo.setStatus(todoDto.getStatus());
        todo.setCreatedAt(todoDto.getCreatedAt());
        todo.setUpdatedAt(todoDto.getUpdatedAt());
        todo.setDueDate(todoDto.getDueDate());
        todo.setPriority(todoDto.getPriority());

        return todo;
    }

}
