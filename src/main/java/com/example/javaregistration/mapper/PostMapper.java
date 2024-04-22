package com.example.javaregistration.mapper;

import com.example.javaregistration.DTO.PostDTO;
import com.example.javaregistration.entity.Post;


import java.util.ArrayList;
import java.util.List;

public class PostMapper {
    private PostMapper(){

    }

    public static Post toModulePost(PostDTO postDTO){
        return Post.builder()
                .id(postDTO.getId())
                .title(postDTO.getTitle())
                .content(postDTO.getContent())
                .author(postDTO.getAuthor())
                .createdAt(postDTO.getCreatedAt())
                .updatedAt(postDTO.getUpdatedAt())
                .likesCount(postDTO.getLikesCount())
                .build();
    }

    public static PostDTO toDTOPost(Post post){
        return PostDTO.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .author(post.getAuthor())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .likesCount(post.getLikesCount())
                .build();
    }

    public static List<Post> toModulePostList(List<PostDTO> postDTOList){
        List<Post> list = new ArrayList<>();
        for (PostDTO postDTO : postDTOList) {
            list.add(toModulePost(postDTO));
        }
        return list;
    }

    public static List<PostDTO> toDTOPostList(List<Post> postList){
        List<PostDTO> dtoList = new ArrayList<>();
        for (Post post : postList) {
            dtoList.add(toDTOPost(post));
        }
        return dtoList;
    }
}
