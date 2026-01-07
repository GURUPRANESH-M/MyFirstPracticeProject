package com.example.MyFirstPracticeProject.repository;

import com.example.MyFirstPracticeProject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HelloRepository extends JpaRepository<User,Integer> {
    
}
