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
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 *
 * @author najib
 */
@Entity
@Table(name = "company_adresses")
@EntityListeners(AuditingEntityListener.class)
public class CompanyAdresses {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Companies companyId;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name="address_type", nullable = false)
    private String addressType;

    @Column(name = "city", nullable = true)
    private String city;

    @Column(name = "country", nullable = true)
    private String country;

    @Column(name = "state", nullable = true)
    private String state;

    @Column(name = "zip_code", nullable = true)
    private String zipCode;

    @Column(name = "is_active", nullable = false)
    private boolean is_active = true;
    
    @CreatedDate
    @Column(name = "createdAt", nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updatedAt", nullable = false)
    private LocalDateTime updatedAt;

    public CompanyAdresses() {
    }

    public CompanyAdresses(Long id, Companies companyId, String address, String addressType, String city, String country, String state, String zipCode, boolean is_active) {
        this.id = id;
        this.companyId = companyId;
        this.address = address;
        this.addressType = addressType;
        this.city = city;
        this.country = country;
        this.state = state;
        this.zipCode = zipCode;
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
     * @return Long return the companyId
     */
    public Companies getCompanyId() {
        return companyId;
    }

    /**
     * @param companyId the companyId to set
     */
    public void setCompanyId(Companies companyId) {
        this.companyId = companyId;
    }

    /**
     * @return String return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return String return the addressType
     */
    public String getAddressType() {
        return addressType;
    }

    /**
     * @param addressType the addressType to set
     */
    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    /**
     * @return String return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return String return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return String return the zipCode
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * @param zipCode the zipCode to set
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
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

    /**
     * @return LocalDateTime return the createdAt
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * @param createdAt the createdAt to set
     */
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }


    /**
     * @return LocalDateTime return the updatedAt
     */
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    /**
     * @param updatedAt the updatedAt to set
     */
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }


    /**
     * @return String return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

}
