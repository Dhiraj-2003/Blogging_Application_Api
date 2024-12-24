package com.dhiraj.blog.services;

import com.dhiraj.blog.payloads.CommentDto;

public interface CommentService {

    CommentDto createComment(CommentDto commentDto,Integer postId);

    void deleteComment(Integer commentId);


}
