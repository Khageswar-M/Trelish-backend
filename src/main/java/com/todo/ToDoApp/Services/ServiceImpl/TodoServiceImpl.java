package com.todo.ToDoApp.Services.ServiceImpl;

import com.todo.ToDoApp.DTO.TodoDto;
import com.todo.ToDoApp.DTO.UserDto;
import com.todo.ToDoApp.Entity.Todo;
import com.todo.ToDoApp.Entity.User;
import com.todo.ToDoApp.Exception.ResourceNotFoundException;
import com.todo.ToDoApp.Mapper.TodoMapper;
import com.todo.ToDoApp.Mapper.UserMapper;
import com.todo.ToDoApp.Repository.TodoRepository;
import com.todo.ToDoApp.Repository.UserRepository;
import com.todo.ToDoApp.Services.TodoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoServiceImpl implements TodoServices {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public TodoDto createTodo(String userId, TodoDto todoDto) {
        Todo todo = TodoMapper.mapToTodo(todoDto);
        todo.setUserId(userId);
        Todo savedTodo = todoRepository.save(todo);

        return TodoMapper.mapToTodoDto(savedTodo);
    }

    @Override
    public List<TodoDto> getAllTodo() {
        List<Todo> todos = todoRepository.findAll();
        return todos.stream().map((todo) -> TodoMapper.mapToTodoDto(todo))
                .collect(Collectors.toList());
    }

    @Override
    public TodoDto getTodoById(Long todoId) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new ResourceNotFoundException("TODO doesn't exist in this id "+todoId));
        return TodoMapper.mapToTodoDto(todo);
    }

    @Override
    public List<TodoDto> getTodoByUserId(String userId) {
        List<Todo> todo = todoRepository.findByUserId(userId);
        return todo.stream()
                .map(TodoMapper::mapToTodoDto)
                .collect(Collectors.toList());
    }

    @Override
    public TodoDto updateTodo(Long todoId, TodoDto updatedTodo) {
        Todo todo = todoRepository.findById(todoId)
                    .orElseThrow(() -> new ResourceNotFoundException("TODO doesn't exist in this Id "+todoId)
                    );
        todo.setTitle(updatedTodo.getTitle());
        todo.setDescription(updatedTodo.getDescription());
        todo.setStatus(updatedTodo.getStatus());
        todo.setPriority(updatedTodo.getPriority());
        todo.setUpdatedAt(LocalDateTime.now());
        todo.setDueDate(updatedTodo.getDueDate());
        Todo updatedTodoObj = todoRepository.save(todo);
        return TodoMapper.mapToTodoDto(updatedTodoObj);
    }

    @Override
    public void deleteTodo(Long todoId) {
        Todo todo = todoRepository.findById(todoId)
                    .orElseThrow(() -> new ResourceNotFoundException("TODO doesn't exist in this id "+todoId)
                    );
        todoRepository.deleteById(todoId);

    }


    @Override
    public UserDto createUser(UserDto userDto) {
        if(userRepository.findByEmail(userDto.getEmail()) != null){
            throw new RuntimeException("Email already exist!");
        }
        User user = UserMapper.mapToUser(userDto);
        User savedUser = userRepository.save(user);
        return UserMapper.mapToUserDto(savedUser);

    }

    @Override
    public String getUserId(String email, String password) {
        User user = userRepository.findByEmailAndPassword(email, password);

        if(user != null){
            return String.valueOf(user.getUserId());
        }else{
            throw new RuntimeException("Invalid email or password");
        }
    }

    @Override
    public boolean findUserName(String email) {
        return userRepository.findByEmail(email) != null;
    }


}
