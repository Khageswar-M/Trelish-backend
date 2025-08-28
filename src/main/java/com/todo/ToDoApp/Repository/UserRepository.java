package com.todo.ToDoApp.Repository;

import com.todo.ToDoApp.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User findByEmailAndPassword(String email, String password);
    User findByEmail(String email);
}
