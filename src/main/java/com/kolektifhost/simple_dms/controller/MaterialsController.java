/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.kolektifhost.simple_dms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kolektifhost.simple_dms.dto.ResponseData;
import com.kolektifhost.simple_dms.entity.MaterialTypes;
import com.kolektifhost.simple_dms.entity.UnitOfMeasurements;
import com.kolektifhost.simple_dms.projection.MaterialProjection;
import com.kolektifhost.simple_dms.service.MaterialsService;

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
}
