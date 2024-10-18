package com.dhiraj.blog.repositories;

import com.dhiraj.blog.entities.Category;
import com.dhiraj.blog.entities.Post;
import com.dhiraj.blog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Integer> {
    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);
}
