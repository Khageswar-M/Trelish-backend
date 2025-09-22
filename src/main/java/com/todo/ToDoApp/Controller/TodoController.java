package com.todo.ToDoApp.Controller;

import com.todo.ToDoApp.DTO.TodoDto;
import com.todo.ToDoApp.DTO.UserDto;
import com.todo.ToDoApp.Services.TodoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin("*")
@Controller
@RestController
@RequestMapping("/api/todo")
public class TodoController {

    @Autowired
    private TodoServices todoServices;

    // Build Add TODO API
    @PostMapping("/users/{userId}/todos")
    public ResponseEntity<TodoDto> createTodo(@PathVariable String userId, @RequestBody TodoDto todoDto){
        TodoDto savedTodo = todoServices.createTodo(userId, todoDto);
        return new ResponseEntity<>(savedTodo, HttpStatus.CREATED);
    }


    //Build method for getting all todos
    @GetMapping
    public ResponseEntity<List<TodoDto>> getAllTodos(){
        List<TodoDto> todo = todoServices.getAllTodo();
        return ResponseEntity.ok(todo);
    }

    //Build REST API for getting todo by ID
    @GetMapping("/{id}")
    public ResponseEntity<TodoDto> getTodo(@PathVariable Long id){
        TodoDto todo = todoServices.getTodoById(id);
        return ResponseEntity.ok(todo);
    }

    //Build REST API for getting todos by UserId
    @GetMapping("/users/{userId}/todos")
    public ResponseEntity<List<TodoDto>> getTodoByUserId(@PathVariable String userId){
        List<TodoDto> todo = todoServices.getTodoByUserId(userId);
        return ResponseEntity.ok(todo);
    }

    //Build REST API for Update Todo
    @PutMapping("/{id}")
    public ResponseEntity<TodoDto> updatedTodo(@PathVariable("id") Long todoId,
                                               @RequestBody TodoDto todoDto){
        TodoDto updateTodo = todoServices.updateTodo(todoId, todoDto);
        return ResponseEntity.ok(updateTodo);
    }

    //BUILD REST API FOR DELETE TODO
    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeTodo(@PathVariable("id") Long todoId){
        todoServices.deleteTodo(todoId);
        return ResponseEntity.ok("Employee is deleted successfully");
    }


    @PostMapping("/login")
    public UserDto createUser(@RequestBody UserDto userDto){
        return todoServices.createUser(userDto);
    }

    @GetMapping("/get/userid")
    public ResponseEntity<String> getUserId(@RequestParam String email,
                                            @RequestParam String password){
        try{
            String userId = todoServices.getUserId(email, password);
            return ResponseEntity.ok(userId);
        }catch(RuntimeException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    //Create rest api to check the valid userName
    @GetMapping("/check")
    public ResponseEntity<Boolean> checkUserName(@RequestParam String user){
        if(user.length() < 5) return ResponseEntity.ok(false);
        boolean exist = todoServices.findUserName(user);
        return ResponseEntity.ok(exist);
    }
}
