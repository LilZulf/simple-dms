/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.kolektifhost.simple_dms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kolektifhost.simple_dms.entity.Posts;
import com.kolektifhost.simple_dms.projection.PostProjection;

/**
 *
 * @author najib
 */
public interface PostsRepository extends JpaRepository<Posts, Long> {
    @Query("SELECT p FROM Posts p WHERE p.is_active = true")
    List<Posts> findActivePosts();

    @Query("SELECT p FROM Posts p WHERE p.slug = ?1")
    Posts findBySlug(String slug);

    @Query("SELECT p FROM Posts p JOIN FETCH p.user WHERE p.id = ?1")
    Posts findByIdWithUser(Long id);

    @Query("SELECT p FROM Posts p JOIN FETCH p.user")
    List<PostProjection> findAllWithUser();
}
