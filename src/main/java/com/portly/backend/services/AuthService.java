package com.portly.backend.services;


import com.portly.backend.dto.*;
import com.portly.backend.dto.input.ForgetPasswordDto;
import com.portly.backend.dto.input.LoginDto;
import com.portly.backend.dto.input.SignUpDto;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {

    UserDto signup(SignUpDto signupDto);

    LoginResponseDto login(LoginDto loginDto);

    String forgetPassword(ForgetPasswordDto forgetPasswordDto);

    LoginResponseDto refresh(String refreshToken);

    String checkUsername(String username);
}
