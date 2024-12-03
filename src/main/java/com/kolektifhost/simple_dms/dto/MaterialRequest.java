/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.kolektifhost.simple_dms.dto;

import java.util.Optional;

import jakarta.validation.constraints.NotBlank;

/**
 *
 * @author najib
 */
public class MaterialRequest {
    @NotBlank
    private String name;

    @NotBlank 
    private String code;

    private Optional<String> description = Optional.empty();

    
    private Long material_type_id;

    private Long unit_of_measurement_id;

    private Long id;

    /**
     * @return String return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return String return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return Optional<String> return the description
     */
    public Optional<String> getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(Optional<String> description) {
        this.description = description;
    }

    /**
     * @return Long return the material_type_id
     */
    public Long getMaterial_type_id() {
        return material_type_id;
    }

    /**
     * @param material_type_id the material_type_id to set
     */
    public void setMaterial_type_id(Long material_type_id) {
        this.material_type_id = material_type_id;
    }

    /**
     * @return Long return the unit_of_measurement_id
     */
    public Long getUnit_of_measurement_id() {
        return unit_of_measurement_id;
    }

    /**
     * @param unit_of_measurement_id the unit_of_measurement_id to set
     */
    public void setUnit_of_measurement_id(Long unit_of_measurement_id) {
        this.unit_of_measurement_id = unit_of_measurement_id;
    }


    /**
     * @return Long return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

}
