package com.example.javaregistration.controller;

import com.example.javaregistration.DTO.PostDTO;
import com.example.javaregistration.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.example.javaregistration.constant.UserConstant.X_SHARER_USER_ID;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    @PostMapping
    public PostDTO createPost(@Valid @RequestBody final PostDTO postDTO, @RequestHeader(X_SHARER_USER_ID) final Long userId){
        return postService.createPost(postDTO, userId);
    }

    @PatchMapping("/{postId}")
    public PostDTO updatePost(@PathVariable final Long postId, @Valid @RequestBody final PostDTO postDTO){
        return postService.updatePost(postDTO, postId);
    }

    @GetMapping("/{postId}")
    public PostDTO getPost(@PathVariable final Long postId){
        return postService.getPost(postId);
    }



    /*@GetMapping("/search")
    public List<PostDTO> getPostsByNameOrDesc(@RequestParam final String text){
        return postService.getPostByNameOrContent(text);
    }*/

    /*
        @PreAuthorize("#post.userId == authentication.principal.username or hasAuthority('ADMIN')")
    */
    @DeleteMapping("/{postId}/{userId}")
    public void deletePost(@PathVariable final Long userId, @PathVariable final Long postId) {
        postService.erasePost(userId, postId);
    }



    @GetMapping("/{userId}")
    public List<PostDTO> getPostByUserId(@PathVariable final Long userId) {
        return postService.getPostsByUser(userId);
    }

    @GetMapping
    public List<PostDTO> getPosts() {
        return postService.getPosts();
    }
}
