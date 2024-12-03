/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.kolektifhost.simple_dms.projection;

import java.time.LocalDateTime;

/**
 *
 * @author najib
 */
public interface MaterialProjection {
    
    public Long getId();
    public String getName();
    public String getCode();
    public String getDescription();
    public LocalDateTime getCreatedAt();
    public LocalDateTime getUpdatedAt();
    public MaterialTypeProjection getMaterialType();
    public UomProjection getUnitOfMeasurement();

}
