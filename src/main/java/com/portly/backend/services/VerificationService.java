package com.portly.backend.services;

import com.portly.backend.entities.Verification;
import com.portly.backend.repositories.VerificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;

@Service
@RequiredArgsConstructor
public class VerificationService {

    private final VerificationRepository verificationRepository;

    public Verification getVerificationByEmail(String email){
        return verificationRepository.findByEmail(email).orElseThrow(
                () -> new InvalidParameterException("Unexpected Parameters..."));
    }

}
