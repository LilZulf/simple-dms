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

import com.kolektifhost.simple_dms.dto.PostRequest;
import com.kolektifhost.simple_dms.dto.ResponseData;
import com.kolektifhost.simple_dms.entity.Posts;
import com.kolektifhost.simple_dms.projection.PostProjection;
import com.kolektifhost.simple_dms.service.PostsService;

import jakarta.validation.Valid;

/**
 *
 * @author najib
 */
@RestController
@RequestMapping("/api/posts")
public class PostsController {

    @Autowired
    private PostsService postsService;

    /**
     * Handles the HTTP GET request to retrieve all posts.
     *
     * @return a ResponseEntity containing a ResponseData object with a list of
     * all posts, a success status, message, and HTTP status code.
     */
    @GetMapping
    public ResponseEntity<ResponseData<List<PostProjection>>> findAll() {
        List<PostProjection> posts = postsService.findAllWithUser();
        return ResponseEntity.ok(new ResponseData<>(true, "Posts retrieved successfully", 200, posts));
    }

    /**
     * Handles the HTTP GET request to retrieve a post by its ID.
     * 
     * @param id the ID of the post to retrieve
     * @return a ResponseEntity containing a ResponseData object with the post
     *         with the specified ID, a success status, message, and HTTP status
     *         code. If the post is not found, a 404 status and a null post is
     *         returned.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<Posts>> getPostById(@PathVariable Long id) {
        Posts post = postsService.getById(id);
        if (post == null) {
            return ResponseEntity.ok(new ResponseData<>(false, "Post not found", 404, null));
        }
        return ResponseEntity.ok(new ResponseData<>(true, "Post retrieved successfully", 200, post));
    }

    /**
     * Handles the HTTP POST request to save a post.
     * 
     * @param post the body of the request containing the post to save
     * @return a ResponseEntity containing a ResponseData object with the saved
     *         post, a success status, message, and HTTP status code. If the
     *         post cannot be saved, a 500 status and a null post is returned.
     */
    @PostMapping
    public ResponseEntity<ResponseData<Posts>> savePost(@Valid @RequestBody PostRequest post) {
        try {
            Posts savedPost = postsService.savePosts(post);
            return ResponseEntity.ok(new ResponseData<>(true, "Post saved successfully", 200, savedPost));
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseData<>(false, e.getMessage(), 500, null));
        }
    }

    /**
     * Handles the HTTP PUT request to update a post.
     * 
     * @param id  the ID of the post to update
     * @param post the body of the request containing the post to update
     * @return a ResponseEntity containing a ResponseData object with the updated
     *         post, a success status, message, and HTTP status code. If the post
     *         cannot be updated, a 500 status and a null post is returned.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ResponseData<Posts>> updatePost(@PathVariable Long id, @Valid @RequestBody PostRequest post) {
        try {
            post.setId(id);
            Posts updatedPost = postsService.savePosts(post);
            return ResponseEntity.ok(new ResponseData<>(true, "Post updated successfully", 200, updatedPost));
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseData<>(false, e.getMessage(), 500, null));
        }
    }

    /**
     * Handles the HTTP DELETE request to delete a post.
     * 
     * @param id the ID of the post to delete
     * @return a ResponseEntity containing a ResponseData object with the deleted
     *         post, a success status, message, and HTTP status code. If the post
     *         cannot be deleted, a 500 status and a null post is returned.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData<Posts>> deletePost(@PathVariable Long id) {
        try {
            Posts deletedPost = postsService.softDeletePosts(postsService.getById(id));
            return ResponseEntity.ok(new ResponseData<>(true, "Post deleted successfully", 200, deletedPost));
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseData<>(false, e.getMessage(), 500, null));
        }
    }

}
