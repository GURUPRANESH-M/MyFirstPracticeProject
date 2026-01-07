package com.example.MyFirstPracticeProject.controller;

import com.example.MyFirstPracticeProject.ExceptionHandler.UserNotFoundException;
import com.example.MyFirstPracticeProject.model.User;
import com.example.MyFirstPracticeProject.service.HelloService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class HelloController {

//    private List<User> users = new ArrayList<>(List.of(
//            new User(1,"Gurupranesh"),
//            new User(2,"Suriya"),
//            new User(3,"Senthil"),
//            new User(4,"Sarvesh")
//    ));

    private HelloService service;

    public HelloController(HelloService service) {
        this.service = service;
    }
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
        return service.getUsers();
    }

    @GetMapping("/greet")
    public String greet(@RequestParam String name){
        return "Welcome " + name;
    }

    @GetMapping("/getUser/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id){
        User u = service.getUserById(id);
        return new ResponseEntity<>(u,HttpStatus.OK);
    }

    @PostMapping("/addUser")
    public ResponseEntity<User> addUser(@Valid @RequestBody User u){
        User t = service.addUser(u);
        return new ResponseEntity<>(t,HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<?> deleteById(@PathVariable int id){
        Optional<User> u = service.deleteById(id);
        return new ResponseEntity<>(u,HttpStatus.OK);
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<User> updateUserById(@Valid @RequestBody User u,@PathVariable int id){
        User t = service.updateUserById(u,id);
        return ResponseEntity.ok().body(t);
    }
}
