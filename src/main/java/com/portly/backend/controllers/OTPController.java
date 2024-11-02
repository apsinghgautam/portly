package com.portly.backend.controllers;

import com.portly.backend.dto.input.SendOtpDto;
import com.portly.backend.dto.input.VerifyOtpDto;
import com.portly.backend.services.impls.OtpServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/otp")
@RequiredArgsConstructor
@CrossOrigin
public class OTPController {

    private final OtpServiceImpl otpService;

    @PostMapping("/send")
    public ResponseEntity<String> sendOtp(@RequestBody SendOtpDto sendOtpDto){
        return ResponseEntity.ok(otpService.sendOtp(sendOtpDto));
    }

    @PostMapping("/verify")
    public ResponseEntity<String> verifyOtp(@RequestBody VerifyOtpDto verifyOtpDto){
        return ResponseEntity.ok(otpService.verifyOtp(verifyOtpDto));
    }

}
