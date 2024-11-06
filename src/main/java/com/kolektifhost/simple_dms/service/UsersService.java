/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.kolektifhost.simple_dms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kolektifhost.simple_dms.entity.Users;
import com.kolektifhost.simple_dms.repository.UsersRepository;

/**
 *
 * @author najib
 */
@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;
    
    public List<Users> findAll() {
        return usersRepository.findAll();
    }
    
    public Users getById(Long id) {
        return usersRepository.findById(id).orElse(null);
    }

    public Users saveUsers(Users user) {
        return usersRepository.save(user);
    }

    public Users deleteUsers(Users user) {
        usersRepository.delete(user);
        return user;
    }

}
