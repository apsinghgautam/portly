package com.portly.backend.services;


import com.portly.backend.dto.input.SendOtpDto;
import com.portly.backend.dto.input.VerifyOtpDto;

public interface OtpService {
    String sendOtp(SendOtpDto sendOtpDto);

    String verifyOtp(VerifyOtpDto verifyOtpDto);

}
