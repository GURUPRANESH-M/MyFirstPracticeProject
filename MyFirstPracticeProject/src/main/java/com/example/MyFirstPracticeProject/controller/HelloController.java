package com.example.MyFirstPracticeProject.controller;

import com.example.MyFirstPracticeProject.ExceptionHandler.UserNotFoundException;
import com.example.MyFirstPracticeProject.controller.model.User;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/")
public class HelloController {

    private List<User> users = new ArrayList<>(List.of(
            new User(1,"Gurupranesh"),
            new User(2,"Suriya"),
            new User(3,"Senthil"),
            new User(4,"Sarvesh")
    ));

//    @GetMapping("/hello")
//    public String hello(){
//        return "Hello Welcome to Spring Boot";
//    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok("Hello welcome to Spring Boot");
    }

    @GetMapping("/created")
    public ResponseEntity<String> created(){
        return ResponseEntity.status(HttpStatus.CREATED).
                contentType(MediaType.APPLICATION_JSON).
                body("Resource created");
    }

    @GetMapping("/getUsers")
    public List<User> getUsers(){
        return users;
    }

    @GetMapping("/greet")
    public String greet(@RequestParam String name){
        return "Welcome " + name;
    }

    @GetMapping("/getUser/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id){
        for(User u:users){
            if(id == u.getId()) return new ResponseEntity<>(u,HttpStatus.OK);
        }
        throw new UserNotFoundException("User not found");
    }

    @PostMapping("/addUser")
    public ResponseEntity<User> addUser(@Valid @RequestBody User u){
        users.add(u);
        return new ResponseEntity<>(u,HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteById/{id}")
    public List<User> deleteById(@PathVariable int id){
        Iterator<User> itr = users.iterator();
        boolean found = false;
        while(itr.hasNext()){
            User u = itr.next();
            if(u.getId() == id) {
                itr.remove();
                found = true;
                break;
            }
        }
        if(!found) throw new UserNotFoundException("User not found to be deleted");
        return users;
    }
}
