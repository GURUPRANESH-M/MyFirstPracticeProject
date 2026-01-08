package com.example.MyFirstPracticeProject.repository;

import com.example.MyFirstPracticeProject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HelloRepository extends JpaRepository<User,Integer> {

    List<User> findByName(String name);
    User findByEmail(String email);
    List<User> findByAge(int age);

    List<User> findByNameAndAge(String name,int age);
    List<User> findByNameOrAge(String name,int age);

    List<User> findByAgeBetween(int start, int end);

    List<User> findByAgeGreaterThanOrderByNameAsc(int age);

    List<User> findFirst3ByAgeGreaterThan(int age);













    boolean existsById(Integer id);
    List<User> findByNameLike(String name);
}
