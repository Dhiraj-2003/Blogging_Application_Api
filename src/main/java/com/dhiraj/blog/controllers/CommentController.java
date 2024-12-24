package com.dhiraj.blog.controllers;


import com.dhiraj.blog.payloads.ApiResponse;
import com.dhiraj.blog.payloads.CommentDto;
import com.dhiraj.blog.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/post/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, @PathVariable Integer postId){
        CommentDto commentCreated = this.commentService.createComment(commentDto,postId);
        return new ResponseEntity<CommentDto>(commentCreated, HttpStatus.CREATED);
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId){
        this.commentService.deleteComment(commentId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Comment Deleted Successfully",true), HttpStatus.OK);
    }
}
