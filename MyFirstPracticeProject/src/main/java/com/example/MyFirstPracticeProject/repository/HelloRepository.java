package com.example.MyFirstPracticeProject.repository;

import com.example.MyFirstPracticeProject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HelloRepository extends JpaRepository<User,Integer> {
    boolean existsById(Integer id);
    List<User> findByNameLike(String name);
}
