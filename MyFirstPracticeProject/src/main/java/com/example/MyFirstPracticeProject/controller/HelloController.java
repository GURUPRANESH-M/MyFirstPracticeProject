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
        return ResponseEntity.status(HttpStatus.OK).
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

    @GetMapping("/existsUser/{id}")
    public boolean checkUserExistsById(@PathVariable int id){
        return service.checkUserExistsById(id);
    }

    @GetMapping("/getUsersWithName")
    public ResponseEntity<List<User>> getNameStartsWith(@RequestParam String s){
        return new ResponseEntity<>(service.getNameStartsWith(s),HttpStatus.OK);
    }

    @GetMapping("/getUsersByName")
    public ResponseEntity<List<User>> getUsersByName(@RequestParam String name){
        return new ResponseEntity<>(service.findByName(name),HttpStatus.OK);
    }

    @GetMapping("/getUsersByEmail")
    public ResponseEntity<User> getUsersByEmail(@RequestParam String email){
        return new ResponseEntity<>(service.findByEmail(email),HttpStatus.OK);
    }

    @GetMapping("/getUsersByAge")
    public ResponseEntity<List<User>> getUsersByAge(@RequestParam int age){
        return new ResponseEntity<>(service.findByAge(age),HttpStatus.OK);
    }

    @GetMapping("/name-and-age")
    public ResponseEntity<List<User>> getUsersByNameAndAge(String name,int age){
        return ResponseEntity.ok().body(service.getUsersByNameAndAge(name,age));
    }

    @GetMapping("/name-or-age")
    public ResponseEntity<List<User>> getUsersByNameOrAge(String name,int age){
        return ResponseEntity.ok().body(service.getUsersByNameOrAge(name,age));
    }

    @GetMapping("/age-between-start-and-end")
    public ResponseEntity<List<User>> getUsersByAgeBetweenStartAndEnd(@RequestParam int start,@RequestParam int end){
        return ResponseEntity.ok().body(service.getUsersByAgeBetweenStartAndEnd(start,end));

    }

    @GetMapping("/age-greater-than-order-by-name-asc")
    public ResponseEntity<List<User>> getUsersByAgeInAsc(@RequestParam int age){
        return new ResponseEntity<>(service.getUsersByAgeInAsc(age),HttpStatus.OK);

    }

    @GetMapping("/find-first-3-by-age-greater-than")
    public ResponseEntity<List<User>> get3UsersByAgeGreaterThan(int age){
        return new ResponseEntity<List<User>>(service.get3UsersByAgeGreaterThan(age),HttpStatus.OK);
    }
}
