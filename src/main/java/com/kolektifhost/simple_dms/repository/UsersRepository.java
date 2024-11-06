/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.kolektifhost.simple_dms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kolektifhost.simple_dms.entity.Users;

/**
 *
 * @author najib
 */
public interface UsersRepository extends JpaRepository<Users, Long> {
    Users findByUsername(String username);
}
