package com.example.javaregistration.service.impl;

import com.example.javaregistration.DTO.PostDTO;
import com.example.javaregistration.entity.Post;
import com.example.javaregistration.entity.User;
import com.example.javaregistration.exception.BadRequestException;
import com.example.javaregistration.exception.NotFoundException;
import com.example.javaregistration.mapper.PostMapper;
import com.example.javaregistration.repository.PostRepository;
import com.example.javaregistration.repository.UserRepository;
import com.example.javaregistration.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    PostRepository postRepository;
    UserRepository userRepository;
    PostMapper postMapper;

    @Override
    public PostDTO createPost(PostDTO postDTO, final Long userId) {
        log.debug("Update post with ID: {}", postDTO.getId());
        User author = userRepository.findById(userId).orElse(null);
        postDTO.setAuthor(author);

        Post post = postRepository.save(postMapper.toModulePost(postDTO));
        log.info("Post created with ID {}", post.getId());
        return postMapper.toDTOPost(post);
    }

    @Override
    public PostDTO updatePost(PostDTO postDTO, final Long userId) {
        log.debug("Updating post ID {}", postDTO.getId());
        Post post = findPostAndValidateCreator(postDTO.getId(), userId);

        if (postDTO.getTitle() != null) {
            post.setTitle(postDTO.getTitle());
        }
        if (postDTO.getContent() != null) {
            post.setContent(postDTO.getContent());
        }

        Post updatedPost = postRepository.save(post);
        log.info("Post with ID {} update", updatedPost.getId());
        return postMapper.toDTOPost(updatedPost);
    }

    @Override
    public void erasePost(Long userId, Long postId) {
        log.debug("Deleting post with ID {}", postId);
        findPostAndValidateCreator(postId, userId);

        postRepository.deleteById(postId);
        log.info("Post with ID {} deleted", postId);
    }

    @Override
    public PostDTO getPost(Long id) {
        log.debug("Getting post with ID {}", id);
        Post post = postRepository.findById(id).orElseThrow(() -> new NotFoundException("Post not found"));
        return postMapper.toDTOPost(post);
    }

   /* @Override
    public List<PostDTO> getPostByNameOrContent(String text) {
        return postMapper.toDTOPostList(postRepository.findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(text, text));
    }*/

    @Override
    public List<PostDTO> getPostsByUser(Long userId) {
        return postMapper.toDTOPostList(postRepository.findByAuthorId(userId));
    }

    @Override
    public List<PostDTO> getPosts() {
        return postMapper.toDTOPostList(postRepository.findAll());
    }

    private Post findPostAndValidateCreator(final Long postId, final Long userId) {
        final Post post = postRepository.findById(postId).orElse(null);
        if (!post.getAuthor().getId().equals(userId)) {
            throw new BadRequestException("User is not the owner of the post");
        }
        return post;
    }
}
