package com.example.MyFirstPracticeProject.model;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class User {

    @Min(value = 0, message = "value must be greated than 0")
    private int id;
    @NotBlank(message = "name is required")
    private String name;

    public int getId() {
        return id;
    }

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
