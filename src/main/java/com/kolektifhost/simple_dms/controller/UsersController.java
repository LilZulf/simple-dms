/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kolektifhost.simple_dms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kolektifhost.simple_dms.dto.ResponseData;
import com.kolektifhost.simple_dms.entity.Users;
import com.kolektifhost.simple_dms.service.UsersService;

/**
 *
 * @author najib
 */
@RestController
@RequestMapping("/api/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping
    public ResponseEntity<ResponseData<List<Users>>> findAll() {
        List<Users> users = usersService.findAll();
        return ResponseEntity.ok(new ResponseData<>(true, "Users retrieved successfully", 200, users));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<Users>> getUserById(@PathVariable Long id) {
        Users user = usersService.getById(id);
        if (user == null) {
            return ResponseEntity.ok(new ResponseData<>(false, "User not found", 404, null));
        }
        return ResponseEntity.ok(new ResponseData<>(true, "User retrieved successfully", 200, user));
    }

    @PostMapping
    public ResponseEntity<ResponseData<Users>> saveUser(Users user) {
        try {
            Users savedUser = usersService.saveUsers(user);
            return ResponseEntity.ok(new ResponseData<>(true, "User saved successfully", 200, savedUser));
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseData<>(false, e.getMessage(), 500, null));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseData<Users>> updateUser(Users user) {
        try {
            Users updatedUser = usersService.saveUsers(user);
            return ResponseEntity.ok(new ResponseData<>(true, "User updated successfully", 200, updatedUser));
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseData<>(false, e.getMessage(), 500, null));
        }
    }

    @DeleteMapping("/{id}") 
    public ResponseEntity<ResponseData<Users>> deleteUser(@PathVariable Long id) {
        try {
            Users deletedUser = usersService.deleteUsers(usersService.getById(id));
            return ResponseEntity.ok(new ResponseData<>(true, "User deleted successfully", 200, deletedUser));
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseData<>(false, e.getMessage(), 500, null));
        }
    }
}
