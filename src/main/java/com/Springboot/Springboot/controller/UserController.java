/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Springboot.Springboot.controller;

import com.Springboot.Springboot.entity.User;
import com.Springboot.Springboot.exception.ResourceNotFoundException;
import com.Springboot.Springboot.repo.UserRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author hkorada
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserRepo userRepo;

    @GetMapping
    public List<User> getUsers() {
        System.out.println("hereh");
        return userRepo.findAll();
    }

    @GetMapping("{id}")
    public User getUserById(@PathVariable("id") long id) {
        return userRepo.findById(id)
                .orElseThrow(()
                        -> new ResourceNotFoundException(id + " doesn't exists in the sytem "));
    }

    @PostMapping
    public User insertUser(@RequestBody User user) {
        return userRepo.save(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@RequestBody User user, @PathVariable("id") long id) {
        User existingUser = userRepo.findById(id).orElseThrow(()
                -> new ResourceNotFoundException(id + " doesn't exists in the sytem "));
        existingUser.setUserName(user.getUserName());
        existingUser.setPassword(user.getPassword());
        existingUser.setEmail(user.getEmail());
        return userRepo.save(existingUser);
    }

}
