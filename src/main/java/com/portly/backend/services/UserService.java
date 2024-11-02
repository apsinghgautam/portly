package com.portly.backend.services;

import com.portly.backend.dto.input.EditNameDto;
import com.portly.backend.dto.input.EditPasswordDto;
import com.portly.backend.dto.input.SignUpDto;
import com.portly.backend.dto.UserDto;
import com.portly.backend.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends UserDetailsService {

    User findUserById(Long userId);

    User createUser(SignUpDto signupDto);

    User findUserByEmail(String email);

    User getUserByEmail(String email);

    User getUserByUri(String uri);

    UserDto getMyProfile();

    String editPassword(EditPasswordDto editPasswordDto);

    String editName(EditNameDto editNameDto);

    User getCurrentUser();

    void updateUser(User user);
}
