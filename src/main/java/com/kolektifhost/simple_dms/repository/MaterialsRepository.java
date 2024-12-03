/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.kolektifhost.simple_dms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kolektifhost.simple_dms.entity.Materials;
import com.kolektifhost.simple_dms.projection.MaterialProjection;

/**
 *
 * @author najib
 */
public interface MaterialsRepository extends JpaRepository<Materials, Long> {

    @Query("SELECT m FROM Materials m JOIN FETCH m.materialType JOIN FETCH m.unitOfMeasurement WHERE m.isActive = true")
    List<MaterialProjection> findAllActive();
}
