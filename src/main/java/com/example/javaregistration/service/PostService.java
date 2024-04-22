package com.example.javaregistration.service;

import com.example.javaregistration.DTO.PostDTO;

import java.util.List;

public interface PostService {
    PostDTO createPost(PostDTO postDTO, Long userId);

    PostDTO updatePost(PostDTO postDTO, Long userId);

    void erasePost(Long userId, Long postId);

    PostDTO getPost(Long id);

/*
    List<PostDTO> getPostByNameOrContent(String text);
*/

    List<PostDTO> getPostsByUser(Long userId);

    List<PostDTO> getPosts();
}
