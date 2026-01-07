package com.example.MyFirstPracticeProject.service;

import com.example.MyFirstPracticeProject.ExceptionHandler.UserNotFoundException;
import com.example.MyFirstPracticeProject.model.User;
import com.example.MyFirstPracticeProject.repository.HelloRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HelloService {

    private HelloRepository repo;

    public HelloService(HelloRepository repo) {
        this.repo = repo;
    }

    public List<User> getUsers() {
        List<User> users = repo.findAll();
        return users;
    }

    public Optional<User> getUserById(int id) {
        Optional<User> u = repo.findById(id);
        if(u != null) return u;
        throw new UserNotFoundException("user not found");
    }

    public void addUser(@Valid User u) {
        repo.save(u);
    }

    public ResponseEntity<?> deleteById(int id) {
        repo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
