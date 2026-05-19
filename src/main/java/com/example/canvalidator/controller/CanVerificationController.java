package com.example.canvalidator.controller;

import com.example.canvalidator.dto.CanBatchRequest;
import com.example.canvalidator.dto.CanBatchVerificationResponse;
import com.example.canvalidator.dto.CanMessageRequest;
import com.example.canvalidator.dto.CanVerificationResponse;
import com.example.canvalidator.service.CanVerificationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/can")
public class CanVerificationController {

    private final CanVerificationService canVerificationService;

    public CanVerificationController(CanVerificationService canVerificationService) {
        this.canVerificationService = canVerificationService;
    }

    @PostMapping("/verify")
    public CanVerificationResponse verify(@RequestBody CanMessageRequest request) {
        return canVerificationService.verify(request.getRawFrame());
    }

    @PostMapping("/verify-batch")
    public CanBatchVerificationResponse verifyBatch(@RequestBody CanBatchRequest request) {
        return canVerificationService.verifyBatch(request.getRawFrames());
    }
}