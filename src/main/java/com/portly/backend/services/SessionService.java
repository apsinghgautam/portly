package com.portly.backend.services;


import com.portly.backend.entities.User;
import org.springframework.stereotype.Service;

@Service
public interface SessionService {

    void createSession(User user, String refreshToken);

    void validateSession(String refreshToken);

    String logout();
}
