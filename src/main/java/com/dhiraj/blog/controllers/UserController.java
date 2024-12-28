package com.dhiraj.blog.controllers;

import com.dhiraj.blog.entities.User;
import com.dhiraj.blog.payloads.ApiResponse;
import com.dhiraj.blog.payloads.UserDto;
import com.dhiraj.blog.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    //Create User
    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
        UserDto createdUser = this.userService.createUser(userDto);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    //Update USer
    @PutMapping("/{uid}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable Integer uid){
        UserDto updatedUser = this.userService.updateUser(userDto,uid);
        return ResponseEntity.ok(updatedUser);
    }

    //Delete User
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{uid}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer uid){
        this.userService.deleteUser(uid);
        return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted Successfully",true),HttpStatus.OK);
    }

    //Get All Users
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return ResponseEntity.ok(this.userService.getAllUsers());
    }

    //Get User By ID
    @GetMapping("/{uid}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer uid){
        return ResponseEntity.ok(this.userService.getUserById(uid));
    }
}
