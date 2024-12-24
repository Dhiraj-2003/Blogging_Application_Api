package com.dhiraj.blog.repositories;

import com.dhiraj.blog.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Integer> {

}
