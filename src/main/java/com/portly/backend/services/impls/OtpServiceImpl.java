package com.portly.backend.services.impls;

import com.portly.backend.dto.input.SendOtpDto;
import com.portly.backend.dto.input.VerifyOtpDto;
import com.portly.backend.entities.Verification;
import com.portly.backend.entities.enums.VerificationType;
import com.portly.backend.repositories.VerificationRepository;
import com.portly.backend.services.EmailService;
import com.portly.backend.services.OtpService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.InvalidParameterException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Random;

@RequiredArgsConstructor
@Service
public class OtpServiceImpl implements OtpService {

    private final VerificationRepository verificationRepository;
    private final EmailService emailService;
    private final UserServiceImpl userService;

    @Override
    @Transactional
    public String sendOtp(SendOtpDto sendOtpDto) {
        Verification verification;
        if(sendOtpDto.getVerificationType().equals(VerificationType.EMAIL_VERIFICATION)){
            if(userService.getUserByEmail(sendOtpDto.getEmail()) != null)
                throw new RuntimeException("User is already registered with email "+ sendOtpDto.getEmail());

            verification = verificationRepository.findByEmail(sendOtpDto.getEmail()).orElse(
                    Verification.builder()
                            .email(sendOtpDto.getEmail())
                            .isVerified(false)
                            .build()
            );
        }else if(sendOtpDto.getVerificationType().equals(VerificationType.FORGET_PASSWORD)){
            if(userService.findUserByEmail(sendOtpDto.getEmail()) == null)
                throw new RuntimeException("User is not found with email "+ sendOtpDto.getEmail());

            verification = verificationRepository.findByEmail(sendOtpDto.getEmail()).orElseThrow(
                    () -> new InvalidParameterException("Unexpected Parameters..."));
            verification.setIsVerified(false);
            verification.setCreatedAt(LocalDateTime.now());
        }else{
            throw new RuntimeException("Invalid Verification Type");
        }

        verification.setOtp(createOtp());
        verification.setOtpIsValidAt(new Date(System.currentTimeMillis() + 60 * 1000 * 10));
        Verification savedVerification = verificationRepository.save(verification);

        emailService.sendEmail(savedVerification.getEmail(),"Profolio email verification",createMessage(savedVerification.getOtp()));
        return "Otp send successfully...";
    }

    @Override
    @Transactional
    public String verifyOtp(VerifyOtpDto verifyOtpDto) {
        Verification verification = verificationRepository.findByEmail(verifyOtpDto.getEmail())
                .orElseThrow(() -> new InvalidParameterException("Unexpected Parameters..."));
        if(verification.getOtpIsValidAt().before(new Date(System.currentTimeMillis())) || !verification.getOtp().equals(verifyOtpDto.getOtp())){
            throw new RuntimeException("Invalid OTP. ");
        }
        verification.setIsVerified(true);
        verificationRepository.save(verification);
        return "Otp verified successfully...";
    }


    private String createOtp() {
        Random random = new Random();
        int otp = random.nextInt(1000000); // Generates a number between 0 and 999999
        return String.format("%06d", otp);
    }

    private String createMessage(String otp){
        return "<div style='font-family: Arial, sans-serif;'>" +
                "<img src='cid:logoImage' alt='Profolio Logo' style='height:50px;'>" +
                "<p>Hello,</p>" +
                "<p>Welcome to <strong>PROFOLIO:BE PROFESSIONAL</strong>!</p>" +
                "<p>Your One-Time Password (OTP) is: <strong>" + otp + "</strong></p>" +
                "<p>This OTP is valid for 10 minutes. If you did not request this verification, please ignore this email.</p>" +
                "<br><p>Best regards,</p>" +
                "<p>The PROFOLIO:BE PROFESSIONAL Team</p>" +
                "</div>";
    }



}
