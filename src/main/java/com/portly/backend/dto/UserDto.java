package com.portly.backend.dto;

import com.portly.backend.entities.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class UserDto {


    private Long userId;

    private String email;

    private String uri;

    private List<Role> roles;

    private String name;

}
