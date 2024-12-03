/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kolektifhost.simple_dms.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kolektifhost.simple_dms.dto.MaterialRequest;
import com.kolektifhost.simple_dms.entity.MaterialTypes;
import com.kolektifhost.simple_dms.entity.Materials;
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

    public MaterialProjection findByIdMaterial(Long id) {
        return materialsRepository.findByIdMaterial(id);
    }

    public Materials saveMaterials(MaterialRequest materialsRequest) {
        Materials material;

        if (materialsRequest.getId() != null) {
            material = materialsRepository.findById(materialsRequest.getId()).orElseThrow(() -> new RuntimeException("Material not found"));
            material.setUpdatedAt(LocalDateTime.now());
        } else {
            material = new Materials();
            material.setCreatedAt(LocalDateTime.now());
            material.setUpdatedAt(LocalDateTime.now());
        }

        MaterialTypes materialType = materialTypesRepository.findById(materialsRequest.getMaterial_type_id()).orElseThrow(() -> new RuntimeException("Material type not found"));
        UnitOfMeasurements unitOfMeasurement = unitOfMeasurementsRepository.findById(materialsRequest.getUnit_of_measurement_id()).orElseThrow(() -> new RuntimeException("Unit of measurement not found"));
        material.setMaterialType(materialType);
        material.setUnitOfMeasurements(unitOfMeasurement);
        material.setCode(materialsRequest.getCode());
        material.setName(materialsRequest.getName());
        material.setDescription(materialsRequest.getDescription().orElse(""));
        material.setIsActive(true);

        return materialsRepository.save(material);
    }

    public Materials deleteMaterials(Long id) {
        Materials material = materialsRepository.findById(id).get();
        material.setIsActive(false);
        return materialsRepository.save(material);
    }

}
