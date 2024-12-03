/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.kolektifhost.simple_dms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kolektifhost.simple_dms.entity.MaterialTypes;
import com.kolektifhost.simple_dms.entity.UnitOfMeasurements;
import com.kolektifhost.simple_dms.projection.MaterialProjection;
import com.kolektifhost.simple_dms.repository.MaterialTypesRepository;
import com.kolektifhost.simple_dms.repository.MaterialsRepository;
import com.kolektifhost.simple_dms.repository.UnitOfMeasurementsRepository;

/**
 *
 * @author najib
 */
@Service
public class MaterialsService {
    @Autowired
    MaterialsRepository materialsRepository;

    @Autowired
    MaterialTypesRepository materialTypesRepository;

    @Autowired 
    UnitOfMeasurementsRepository unitOfMeasurementsRepository;

    public List<MaterialProjection> findAll() {
        return materialsRepository.findAllActive();
    }

    public List<MaterialTypes> findAllMaterialTypes() {
        return materialTypesRepository.findAll();
    }

    public List<UnitOfMeasurements> findAllUnitOfMeasurements() {
        return unitOfMeasurementsRepository.findAll();
    }

}
