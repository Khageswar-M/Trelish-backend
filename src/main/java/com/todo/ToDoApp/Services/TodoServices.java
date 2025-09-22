package com.todo.ToDoApp.Services;

import com.todo.ToDoApp.DTO.TodoDto;
import com.todo.ToDoApp.DTO.UserDto;
import com.todo.ToDoApp.Entity.User;

import java.util.List;

public interface TodoServices {

    TodoDto createTodo(String userId, TodoDto todoDto);

    List<TodoDto> getAllTodo();

    TodoDto getTodoById(Long todoId);

    List<TodoDto> getTodoByUserId(String userId);

    TodoDto updateTodo(Long todoId, TodoDto updatedTodo);

    void deleteTodo(Long todoId);

    UserDto createUser(UserDto userDto);

    String getUserId(String email, String password);

    boolean findUserName(String email);
}
