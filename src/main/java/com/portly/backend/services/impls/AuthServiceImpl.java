package com.portly.backend.services.impls;

import com.portly.backend.dto.*;
import com.portly.backend.dto.input.ForgetPasswordDto;
import com.portly.backend.dto.input.LoginDto;
import com.portly.backend.dto.input.SignUpDto;
import com.portly.backend.entities.User;
import com.portly.backend.services.AuthService;
import com.portly.backend.services.JwtService;
import com.portly.backend.services.VerificationService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final ModelMapper modelMapper;
    private final AuthenticationManager authenticationManager;
    private final UserServiceImpl userService;
    private final JwtService jwtService;
    private final SessionServiceImpl sessionService;
    private final PortfolioServiceImpl portfolioService;
    private final VerificationService verificationService;
    private final PasswordEncoder passwordEncoder;



    @Override
    @Transactional
    public UserDto signup(SignUpDto signupDto) {
        if(!verificationService.getVerificationByEmail(signupDto.getEmail()).getIsVerified()){
            throw new AuthenticationServiceException("Email is not Verified.");
        }
        User user = userService.createUser(signupDto);
        portfolioService.createPortfolio(user);
        return modelMapper.map(user, UserDto.class);
    }



    @Override
    public LoginResponseDto login(LoginDto loginDto) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword())
        );

        User user = (User) authentication.getPrincipal();

        String accessToken =  jwtService.generateAccessToken(user);
        String refreshToken =  jwtService.generateRefreshToken(user);

        sessionService.createSession(user, refreshToken);

        return new LoginResponseDto(user.getId(), accessToken, refreshToken, authentication.isAuthenticated());

    }

    @Override
    public String forgetPassword(ForgetPasswordDto forgetPasswordDto) {
        User user = userService.findUserByEmail(forgetPasswordDto.getEmail());
        if(!verificationService.getVerificationByEmail(user.getEmail()).getIsVerified()){
            throw new RuntimeException("Unauthorized access");
        }
        user.setPassword(passwordEncoder.encode(forgetPasswordDto.getPassword()));
        userService.updateUser(user);
        return "Password updated successfully.";
    }

    @Override
    public LoginResponseDto refresh(String refreshToken) {
        Long userId = jwtService.getUserIdFromToken(refreshToken);
        sessionService.validateSession(refreshToken);
        User user = userService.findUserById(userId);

        String accessToken =  jwtService.generateAccessToken(user);
        return new LoginResponseDto(userId, accessToken, refreshToken, true);
    }

    @Override
    public String checkUsername(String username) {
        User user = userService.getUserByUri(username);
        if(user != null) throw new RuntimeException("Username is already in use.");
        return "Username is available";
    }

}
