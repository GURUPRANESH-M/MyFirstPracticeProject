package com.example.MyFirstPracticeProject.repository;

import com.example.MyFirstPracticeProject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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

//    @Query("select u from User u where u.name=?1 and u.email=?2")
//    User findUserByNameAndEmail(String name,String email);

    @Query("select u from User u where u.name= :name and u.email=:email")
    User findUserByNameAndEmail(@Param("name") String name,@Param("email") String email);

    @Query("select u from User u where u.name like :s% order by u.name asc")
    List<User> searchByName(@Param("s") String name);

    boolean existsById(Integer id);
    List<User> findByNameLike(String name);
}
