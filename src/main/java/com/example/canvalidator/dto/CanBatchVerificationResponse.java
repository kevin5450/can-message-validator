package com.example.canvalidator.dto;

import java.util.List;

public class CanBatchVerificationResponse {

    private final int totalCount;
    private final int validCount;
    private final int invalidCount;
    private final List<CanBatchItemResponse> results;

    public CanBatchVerificationResponse(
            int totalCount,
            int validCount,
            int invalidCount,
            List<CanBatchItemResponse> results
    ) {
        this.totalCount = totalCount;
        this.validCount = validCount;
        this.invalidCount = invalidCount;
        this.results = results;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public int getValidCount() {
        return validCount;
    }

    public int getInvalidCount() {
        return invalidCount;
    }

    public List<CanBatchItemResponse> getResults() {
        return results;
    }
}
