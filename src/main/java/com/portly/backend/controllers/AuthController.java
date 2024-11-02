package com.portly.backend.controllers;

import com.portly.backend.dto.*;
import com.portly.backend.dto.input.ForgetPasswordDto;
import com.portly.backend.dto.input.LoginDto;
import com.portly.backend.dto.input.SignUpDto;
import com.portly.backend.services.impls.AuthServiceImpl;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin()
public class AuthController {

    private final AuthServiceImpl authService;

    @Value("${deploy.env}")
    private String deployEnv;

    @PostMapping("/signup")
    ResponseEntity<UserDto> signUP(@RequestBody SignUpDto signUpDto){
        return ResponseEntity.ok(authService.signup(signUpDto));
    }

    @PostMapping("/login")
    ResponseEntity<LoginResponseDto> login(@RequestBody LoginDto loginDto, HttpServletRequest request , HttpServletResponse response){
        LoginResponseDto loginResponseDto =  authService.login(loginDto);
        Cookie cookie = new Cookie("refreshToken", loginResponseDto.getRefreshToken());
        cookie.setHttpOnly(true);
        cookie.setSecure(deployEnv.equals("production"));
        response.addCookie(cookie);

        return ResponseEntity.ok(loginResponseDto);
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponseDto> refresh(HttpServletResponse response,
                                                    HttpServletRequest request){
        Cookie [] cookies = request.getCookies();

        String refreshToken = Arrays.stream(cookies)
                .filter(cookie -> "refreshToken".equals(cookie.getName()))
                .findFirst()
                .map(Cookie :: getValue)
                .orElseThrow(() ->
                        new AuthenticationServiceException("Refresh Token not found in cookie"));

        LoginResponseDto loginResponseDto = authService.refresh(refreshToken);

        return ResponseEntity.ok(loginResponseDto);

    }


    @PostMapping("/forgetPassword")
    public ResponseEntity<String> forgetPassword(@RequestBody ForgetPasswordDto forgetPasswordDto){
        return ResponseEntity.ok(authService.forgetPassword(forgetPasswordDto));
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<String> isUsernameAvailable(@PathVariable String username){
        return ResponseEntity.ok(authService.checkUsername(username));
    }



}
