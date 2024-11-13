/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kolektifhost.simple_dms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kolektifhost.simple_dms.dto.PostRequest;
import com.kolektifhost.simple_dms.entity.Posts;
import com.kolektifhost.simple_dms.entity.Users;
import com.kolektifhost.simple_dms.projection.PostProjection;
import com.kolektifhost.simple_dms.repository.PostsRepository;
import com.kolektifhost.simple_dms.repository.UsersRepository;

/**
 *
 * @author najib
 */
@Service
public class PostsService {

    @Autowired
    private PostsRepository postsRepository;

    @Autowired
    private UsersRepository usersRepository;

    /**
     * Retrieves all posts from the repository.
     *
     * @return a list of all posts
     */
    public List<Posts> findAll() {
        return postsRepository.findActivePosts();
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
    public Posts savePosts(PostRequest postsRequest) {
        Posts posts;

        if (postsRequest.getId() != null) {
            // Update existing post
            posts = postsRepository.findById(postsRequest.getId())
                    .orElseThrow(() -> new RuntimeException("Post not found"));
        } else {
            // Create new post
            posts = new Posts();
        }

        Users user = usersRepository.findById(postsRequest.getUser_id())
                .orElseThrow(() -> new RuntimeException("User not found"));
        posts.setUser(user);

        
        posts.setTitle(postsRequest.getTitle());
        posts.setContent(postsRequest.getContent());

        posts.setSlug(postsRequest.getSlug().orElse(""));
        posts.setParent_id(postsRequest.getParent_id().orElse(null));
        posts.setIs_active(postsRequest.getIs_active().orElse(true));

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

    /**
     * Soft deletes a post by setting its is_active field to false.
     *
     * @param posts the post to soft delete
     * @return the soft deleted post
     */
    public Posts softDeletePosts(Posts posts) {
        posts.setIs_active(false);
        return postsRepository.save(posts);
    }


    /**
     * Retrieves a post by its slug from the repository.
     * 
     * @param slug the slug of the post to retrieve
     * @return the post with the specified slug, or null if not found
     */
    public Posts getPostBySlug(String slug) {
        return postsRepository.findBySlug(slug);
    }

    /**
     * Retrieves a post by its ID from the repository, along with its associated user.
     * 
     * @param id the ID of the post to retrieve
     * @return the post with the specified ID, or null if not found
     */
    public Posts getPostByIdWithUser(Long id) {
        return postsRepository.findByIdWithUser(id);
    }

    /**
     * Retrieves all posts from the repository, along with their associated users.
     *
     * @return a list of all posts, each with their associated user
     */
    public List<PostProjection> findAllWithUser() {
        return postsRepository.findAllWithUser();
    }

}
