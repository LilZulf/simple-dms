/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.kolektifhost.simple_dms.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

/**
 *
 * @author najib
 */

@Entity
@Table(name = "materials", uniqueConstraints={@UniqueConstraint(columnNames={"code"})})
@EntityListeners(AuditingEntityListener.class)
public class Materials {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code", unique = true, nullable = false, length=20)
    private String code;

    @Column(name = "name", nullable = false, length=100)
    private String name;

    @Column(name = "description", nullable = false, length=255)
    private String description;

    @Column(nullable = false)
    private boolean is_active = true; 

    @ManyToOne
    @JoinColumn(name = "material_type_id")
    private MaterialTypes materialType;

    @ManyToOne
    @JoinColumn(name = "unit_of_measurement_id")
    private UnitOfMeasurements unitOfMeasurement;

     // Audit Fields
    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt; // Waktu dibuat

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt; // Waktu diperbarui

    public Materials() {
        
    }

    public Materials(Long id, String code, String name, String description, boolean is_active) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.description = description;
        this.is_active = is_active;
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
     * @return String return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return boolean return the is_active
     */
    public boolean getis_active() {
        return is_active;
    }

    /**
     * @param is_active the is_active to set
     */
    public void setis_active(boolean is_active) {
        this.is_active = is_active;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }


    /**
     * @return MaterialTypes return the materialType
     */
    public MaterialTypes getMaterialType() {
        return materialType;
    }

    /**
     * @param materialType the materialType to set
     */
    public void setMaterialType(MaterialTypes materialType) {
        this.materialType = materialType;
    }


    /**
     * @return UnitOfMeasurements return the unitOfMeasurement
     */
    public UnitOfMeasurements getUnitOfMeasurements() {
        return unitOfMeasurement;
    }

    /**
     * @param unitOfMeasurement the unitOfMeasurement to set
     */
    public void setUnitOfMeasurements(UnitOfMeasurements unitOfMeasurement) {
        this.unitOfMeasurement = unitOfMeasurement;
    }

}
