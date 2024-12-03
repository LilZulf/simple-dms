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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kolektifhost.simple_dms.dto.MaterialRequest;
import com.kolektifhost.simple_dms.dto.ResponseData;
import com.kolektifhost.simple_dms.entity.MaterialTypes;
import com.kolektifhost.simple_dms.entity.Materials;
import com.kolektifhost.simple_dms.entity.UnitOfMeasurements;
import com.kolektifhost.simple_dms.projection.MaterialProjection;
import com.kolektifhost.simple_dms.service.MaterialsService;

import jakarta.validation.Valid;

/**
 *
 * @author najib
 */
@RestController
@RequestMapping("api/materials")
public class MaterialsController {

    @Autowired
    private MaterialsService materialsService;

    @GetMapping
    public ResponseEntity<ResponseData<List<MaterialProjection>>> findAll() {
        List<MaterialProjection> materials = materialsService.findAll();
        return ResponseEntity.ok(new ResponseData<>(true, "Materials retrieved successfully", 200, materials));
    }

    @GetMapping("/types")
    public ResponseEntity<ResponseData<List<MaterialTypes>>> findAllMaterialTypes() {
        List<MaterialTypes> materialTypes = materialsService.findAllMaterialTypes();
        return ResponseEntity.ok(new ResponseData<>(true, "Material types retrieved successfully", 200, materialTypes));
    }

    @GetMapping("/unit-of-measurements")
    public ResponseEntity<ResponseData<List<UnitOfMeasurements>>> findAllUnitOfMeasurements() {
        List<UnitOfMeasurements> unitOfMeasurements = materialsService.findAllUnitOfMeasurements();
        return ResponseEntity.ok(new ResponseData<>(true, "Unit of measurements retrieved successfully", 200, unitOfMeasurements));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<MaterialProjection>> findByIdMaterial(@PathVariable Long id) {
        MaterialProjection material = materialsService.findByIdMaterial(id);
        return ResponseEntity.ok(new ResponseData<>(true, "Material retrieved successfully", 200, material));
    }

    @PostMapping
    public ResponseEntity<ResponseData<Materials>> saveMaterial(@Valid @RequestBody MaterialRequest material) {
        try {
            Materials savedMaterial = materialsService.saveMaterials(material);
            return ResponseEntity.ok(new ResponseData<>(true, "Material saved successfully", 200, savedMaterial));
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseData<>(false, e.getMessage(), 500, null));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseData<Materials>> updateMaterial(@PathVariable Long id, @Valid @RequestBody MaterialRequest material) {
        try {
            material.setId(id);
            Materials updatedMaterial = materialsService.saveMaterials(material);
            return ResponseEntity.ok(new ResponseData<>(true, "Material updated successfully", 200, updatedMaterial));
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseData<>(false, e.getMessage(), 500, null));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData<Materials>> deleteMaterial(@PathVariable Long id) {
        try {
            Materials deletedMaterial = materialsService.deleteMaterials(id);
            return ResponseEntity.ok(new ResponseData<>(true, "Material deleted successfully", 200, deletedMaterial));
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseData<>(false, e.getMessage(), 500, null));
        }
    }
}
