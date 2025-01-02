package com.dhiraj.blog.security;

import com.dhiraj.blog.entities.User;
import com.dhiraj.blog.exceptions.ResourceNotFoundException;
import com.dhiraj.blog.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByEmail(username).orElseThrow(()-> new ResourceNotFoundException("User"," email : "+username,0));
        return user;
    }
}
