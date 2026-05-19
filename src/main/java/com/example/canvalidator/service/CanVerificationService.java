package com.example.canvalidator.service;

import com.example.canvalidator.domain.CanMessage;
import com.example.canvalidator.dto.CanBatchItemResponse;
import com.example.canvalidator.dto.CanBatchVerificationResponse;
import com.example.canvalidator.dto.CanVerificationResponse;
import com.example.canvalidator.dto.RuleResultResponse;
import com.example.canvalidator.parser.CanParser;
import com.example.canvalidator.rule.ValidationRule;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CanVerificationService {

    private final CanParser canParser;
    private final List<ValidationRule> validationRules;

    public CanVerificationService(CanParser canParser, List<ValidationRule> validationRules) {
        this.canParser = canParser;
        this.validationRules = validationRules;
    }

    public CanVerificationResponse verify(String rawFrame) {
        CanMessage message = canParser.parse(rawFrame);

        List<RuleResultResponse> results = validationRules.stream()
                .map(rule -> rule.validate(message))
                .toList();

        boolean valid = results.stream()
                .allMatch(RuleResultResponse::isValid);

        return new CanVerificationResponse(
                valid,
                message.getCanId(),
                message.getDlc(),
                message.getDataBytesAsHex(),
                results
        );
    }

    public CanBatchVerificationResponse verifyBatch(List<String> rawFrames) {
        if (rawFrames == null || rawFrames.isEmpty()) {
            throw new IllegalArgumentException("rawFrames must not be empty.");
        }

        List<CanBatchItemResponse> batchResults = new ArrayList<>();

        for (String rawFrame : rawFrames) {
            batchResults.add(verifySingleFrameForBatch(rawFrame));
        }

        int totalCount = batchResults.size();
        int validCount = (int) batchResults.stream()
                .filter(CanBatchItemResponse::isValid)
                .count();
        int invalidCount = totalCount - validCount;

        return new CanBatchVerificationResponse(
                totalCount,
                validCount,
                invalidCount,
                batchResults
        );
    }

    private CanBatchItemResponse verifySingleFrameForBatch(String rawFrame) {
        try {
            CanVerificationResponse response = verify(rawFrame);

            return new CanBatchItemResponse(
                    rawFrame,
                    response.isValid(),
                    response.getCanId(),
                    response.getDlc(),
                    response.getDataBytes(),
                    response.getResults()
            );
        } catch (IllegalArgumentException e) {
            List<RuleResultResponse> parseError = List.of(
                    new RuleResultResponse(
                            "PARSE_ERROR",
                            false,
                            e.getMessage()
                    )
            );

            return new CanBatchItemResponse(
                    rawFrame,
                    false,
                    null,
                    null,
                    List.of(),
                    parseError
            );
        }
    }
}