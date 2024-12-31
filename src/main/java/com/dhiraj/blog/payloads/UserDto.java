package com.dhiraj.blog.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

    @NotEmpty
    @Size(min = 2,message = "username must be of min 2 characters !!!")
    private String name;

    @Email(message = "Email address is not valid !!!")
    private String email;

    @NotEmpty
    @Size(min = 3,max = 10,message = "Password must be 3 char and max 10 char.")
    private String password;

    @NotEmpty
    private String about;

    private Set<RoleDto> roles = new HashSet<>();
}
