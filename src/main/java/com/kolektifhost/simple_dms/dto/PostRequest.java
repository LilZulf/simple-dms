/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.kolektifhost.simple_dms.dto;

import java.util.Optional;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


/**
 *
 * @author najib
 */
public class PostRequest {
    @NotBlank(message = "Title is required")
    @Size(min = 5, message = "Title must be at least 5 characters long")
    private String title;

    @NotBlank(message = "Content is required")
    @Size(min = 5, message = "Content must be at least 5 characters long")
    private String content;

    private Optional<String> slug = Optional.empty();

    private Long user_id;

    private Optional<Long> parent_id = Optional.empty();

    private Optional<Boolean> is_active = Optional.empty();

    private Long id;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



   

    /**
     * @return String return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return Optional<String> return the slug
     */
    public Optional<String> getSlug() {
        return slug;
    }

    /**
     * @param slug the slug to set
     */
    public void setSlug(Optional<String> slug) {
        this.slug = slug;
    }

    /**
     * @return Long return the user_id
     */
    public Long getUser_id() {
        return user_id;
    }

    /**
     * @param user_id the user_id to set
     */
    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    /**
     * @return Optional<Long> return the parent_id
     */
    public Optional<Long> getParent_id() {
        return parent_id;
    }

    /**
     * @param parent_id the parent_id to set
     */
    public void setParent_id(Optional<Long> parent_id) {
        this.parent_id = parent_id;
    }

    /**
     * @return Optional<Boolean> return the is_active
     */
    public Optional<Boolean> getIs_active() {
        return is_active;
    }

    /**
     * @param is_active the is_active to set
     */
    public void setIs_active(Optional<Boolean> is_active) {
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

}
