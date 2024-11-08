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

    @GetMapping
    public ResponseEntity<ResponseData<List<Posts>>> findAll() {
        List<Posts> posts = postsService.findAll();
        return ResponseEntity.ok(new ResponseData<>(true, "Users retrieved successfully", 200, posts));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<Posts>> getPostById(@PathVariable Long id) {
        Posts post = postsService.getById(id);
        if (post == null) {
            return ResponseEntity.ok(new ResponseData<>(false, "Post not found", 404, null));
        }
        return ResponseEntity.ok(new ResponseData<>(true, "Post retrieved successfully", 200, post));
    }

    @PostMapping
    public ResponseEntity<ResponseData<Posts>> savePost(@Valid @RequestBody PostRequest post) {
        try {
            Posts savedPost = postsService.savePosts(post);
            return ResponseEntity.ok(new ResponseData<>(true, "Post saved successfully", 200, savedPost));
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseData<>(false, e.getMessage(), 500, null));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseData<Posts>> updatePost(@Valid @RequestBody PostRequest post) {
        try {
            Posts updatedPost = postsService.savePosts(post);
            return ResponseEntity.ok(new ResponseData<>(true, "Post updated successfully", 200, updatedPost));
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseData<>(false, e.getMessage(), 500, null));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData<Posts>> deletePost(@PathVariable Long id) {
        try {
            Posts deletedPost = postsService.deletePosts(postsService.getById(id));
            return ResponseEntity.ok(new ResponseData<>(true, "Post deleted successfully", 200, deletedPost));
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseData<>(false, e.getMessage(), 500, null));
        }
    }

}
