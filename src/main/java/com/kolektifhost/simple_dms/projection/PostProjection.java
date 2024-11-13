/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.kolektifhost.simple_dms.projection;

/**
 *
 * @author najib
 */
public interface PostProjection {

    Long getId();

    String getTitle();

    String getContent();

    UserProjection getUser();  // Use the UserProjection interface
}
