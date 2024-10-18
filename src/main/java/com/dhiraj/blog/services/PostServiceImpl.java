package com.dhiraj.blog.services;

import com.dhiraj.blog.entities.Category;
import com.dhiraj.blog.entities.Post;
import com.dhiraj.blog.entities.User;
import com.dhiraj.blog.exceptions.ResourceNotFoundException;
import com.dhiraj.blog.payloads.PostDto;
import com.dhiraj.blog.repositories.CategoryRepository;
import com.dhiraj.blog.repositories.PostRepository;
import com.dhiraj.blog.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService{

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public PostDto createPost(PostDto postDto,Integer userId, Integer categoryId) {
        User user =  userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","User ID",userId));
        Category category = categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Category Id",categoryId));
        Post post = modelMapper.map(postDto, Post.class);
        post.setAddedDate(new Date());
        post.setImageName("Default.png");
        post.setUser(user);
        post.setCategory(category);

        Post updatedPost = postRepository.save(post);
        return modelMapper.map(updatedPost,PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post = postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","postId",postId));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());

        Post updated = postRepository.save(post);
        return modelMapper.map(updated,PostDto.class);
    }

    @Override
    public void deletePost(Integer postId) {
        Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException(
                "Post","postId",postId
        ));
        postRepository.delete(post);
    }

    @Override
    public PostDto getPostById(Integer postId) {
        Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","Post_Id",postId));

        PostDto postDto = modelMapper.map(post,PostDto.class);
        return postDto;
    }

    @Override
    public List<PostDto> getAllPosts() {
        List<Post> allPosts = postRepository.findAll();
        List<PostDto> postDtos = allPosts.stream().map((post)->modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> getPostByCategory(Integer categoryId) {
        Category cat = this.categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Category_ID",categoryId));
        List<Post> posts = this.postRepository.findByCategory(cat);
        List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> getPostByUser(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","user_ID",userId));
        List<Post> posts = postRepository.findByUser(user);
        List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {
        return null;
    }
}
