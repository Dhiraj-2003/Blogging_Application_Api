package com.dhiraj.blog.repositories;

import com.dhiraj.blog.entities.Category;
import com.dhiraj.blog.entities.Post;
import com.dhiraj.blog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Integer> {
    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);

    List<Post> findByTitleContaining(String keyword);
}
