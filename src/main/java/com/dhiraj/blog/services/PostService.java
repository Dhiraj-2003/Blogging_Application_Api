package com.dhiraj.blog.services;

import com.dhiraj.blog.entities.Post;
import com.dhiraj.blog.payloads.PostDto;

import java.util.List;

public interface PostService {
    //create
    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

    //update
    PostDto updatePost(PostDto postDto,Integer postId);
    //delete
    void deletePost(Integer postId);
    //get Single Post
    PostDto getPostById(Integer postId);
    //get All posts
    List<PostDto> getAllPosts(Integer pageNumber,Integer pageSize);
    //get Post by Category
    List<PostDto> getPostByCategory(Integer categoryId);
    //get Post by Users
    List<PostDto> getPostByUser(Integer userId);
    //search post by keyword
    List<PostDto> searchPosts(String keyword);
}
