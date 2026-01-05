package com.example.otp;

import com.sendly.Sendly;
import com.sendly.models.CheckVerificationRequest;
import com.sendly.models.CheckVerificationResponse;
import com.sendly.models.SendVerificationRequest;
import com.sendly.models.SendVerificationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class OtpController {
    
    @Autowired
    private Sendly sendlyClient;
    
    @PostMapping("/send-otp")
    public ResponseEntity<?> sendOtp(@RequestBody Map<String, String> request) {
        try {
            String phone = request.get("phone");
            
            if (phone == null || phone.isEmpty()) {
                return ResponseEntity.badRequest()
                    .body(Map.of("error", "Phone number is required"));
            }
            
            SendVerificationResponse response = sendlyClient.verify().send(
                SendVerificationRequest.builder()
                    .to(phone)
                    .build()
            );
            
            return ResponseEntity.ok(Map.of(
                "success", true,
                "verificationId", response.getId(),
                "message", "OTP sent successfully"
            ));
            
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                .body(Map.of("error", e.getMessage()));
        }
    }
    
    @PostMapping("/verify-otp")
    public ResponseEntity<?> verifyOtp(@RequestBody Map<String, String> request) {
        try {
            String verificationId = request.get("verificationId");
            String code = request.get("code");
            
            if (verificationId == null || code == null) {
                return ResponseEntity.badRequest()
                    .body(Map.of("error", "Verification ID and code are required"));
            }
            
            CheckVerificationResponse result = sendlyClient.verify().check(
                verificationId,
                CheckVerificationRequest.builder()
                    .code(code)
                    .build()
            );
            
            if ("verified".equals(result.getStatus())) {
                return ResponseEntity.ok(Map.of(
                    "success", true,
                    "message", "Phone number verified successfully"
                ));
            } else {
                return ResponseEntity.badRequest()
                    .body(Map.of("error", "Invalid verification code"));
            }
            
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                .body(Map.of("error", e.getMessage()));
        }
    }
}
