/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kolektifhost.simple_dms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kolektifhost.simple_dms.entity.Posts;
import com.kolektifhost.simple_dms.repository.PostsRepository;

/**
 *
 * @author najib
 */
@Service
public class PostsService {

    @Autowired
    private PostsRepository postsRepository;

    /**
     * Retrieves all posts from the repository.
     *
     * @return a list of all posts
     */
    public List<Posts> findAll() {
        return postsRepository.findAll();
    }

    /**
     * Retrieves a post by its ID.
     *
     * @param id the ID of the post to retrieve
     * @return the post with the specified ID, or null if not found
     */
    public Posts getById(Long id) {
        return postsRepository.findById(id).orElse(null);
    }

    /**
     * Saves a post to the repository.
     *
     * @param posts the post to save
     * @return the saved post
     */
    public Posts savePosts(Posts posts) {
        return postsRepository.save(posts);
    }

    /**
     * Deletes a post from the repository.
     *
     * @param posts the post to delete
     */
    public Posts deletePosts(Posts posts) {
        postsRepository.delete(posts);
        return posts;
    }

}
