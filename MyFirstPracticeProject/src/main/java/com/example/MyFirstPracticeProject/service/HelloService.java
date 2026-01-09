package com.example.MyFirstPracticeProject.service;

import com.example.MyFirstPracticeProject.ExceptionHandler.UserNotFoundException;
import com.example.MyFirstPracticeProject.model.User;
import com.example.MyFirstPracticeProject.repository.HelloRepository;
import jakarta.validation.Valid;
import org.jspecify.annotations.Nullable;
import org.springframework.http.HttpStatusCode;
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

    public User getUserById(int id) {
        return repo.findById(id).orElseThrow(() -> new UserNotFoundException("user not found"));
    }

    public User addUser(@Valid User u) {
        return repo.save(u);
    }

    public Optional<User> deleteById(int id) {
        Optional<User> u = Optional.ofNullable(repo.findById(id).orElseThrow(() -> new UserNotFoundException("user not found to be deleted")));
        repo.deleteById(id);
        return u;
    }

    public User updateUserById(User u, int id) {
        Optional<User> t = Optional.ofNullable(repo.findById(id).orElseThrow(() -> new UserNotFoundException("user not found to be updated  ")));
        return repo.save(u);
    }

    public boolean checkUserExistsById(int id) {
        return repo.existsById(id);
    }

    public List<User> getNameStartsWith(String s) {
        return repo.findByNameLike("%"+s+"%");
    }

    public List<User> findByName(String name) {
        return repo.findByName(name);
    }

    public User findByEmail(String email) {
        return repo.findByEmail(email);
    }

    public List<User> findByAge(int age) {
        return repo.findByAge(age);
    }

    public List<User> getUsersByNameAndAge(String name, int age) {
        return repo.findByNameAndAge(name,age);
    }

    public List<User> getUsersByNameOrAge(String name, int age) {
        return repo.findByNameOrAge(name, age);
    }

    public List<User> getUsersByAgeBetweenStartAndEnd(int start, int end) {
        return repo.findByAgeBetween(start, end);
    }

    public List<User> getUsersByAgeInAsc(int age) {
        return repo.findByAgeGreaterThanOrderByNameAsc(age);
    }


    public List<User> get3UsersByAgeGreaterThan(int age) {
        return repo.findFirst3ByAgeGreaterThan(age);
    }

    public User getUsersByNameAndEmail(String name, String email) {
        return repo.findUserByNameAndEmail(name,email);
    }

    public List<User> getUsersStartsWithName(String name) {
        return repo.searchByName(name);
    }
}
