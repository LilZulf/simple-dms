/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.kolektifhost.simple_dms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kolektifhost.simple_dms.entity.Roles;

/**
 *
 * @author najib
 */
public interface RolesRepository extends JpaRepository<Roles, Long> {
    Optional<Roles> findByName(String name); 
}
