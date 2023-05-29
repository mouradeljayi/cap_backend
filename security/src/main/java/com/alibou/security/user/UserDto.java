package com.alibou.security.user;

import com.alibou.security.club.Club;
import lombok.Data;

import java.util.Set;

@Data
public class UserDto {
    private Integer id;
    private String firstname;
    private String lastname;
    private String email;
    private String role;
    private String password;
    private Set<Club> clubs;

}

