package com.example.canvalidator.dto;

import java.util.List;

public class CanVerificationResponse {

    private final boolean valid;
    private final String canId;
    private final int dlc;
    private final List<String> dataBytes;
    private final List<RuleResultResponse> results;

    public CanVerificationResponse(
            boolean valid,
            String canId,
            int dlc,
            List<String> dataBytes,
            List<RuleResultResponse> results
    ) {
        this.valid = valid;
        this.canId = canId;
        this.dlc = dlc;
        this.dataBytes = dataBytes;
        this.results = results;
    }

    public boolean isValid() {
        return valid;
    }

    public String getCanId() {
        return canId;
    }

    public int getDlc() {
        return dlc;
    }

    public List<String> getDataBytes() {
        return dataBytes;
    }

    public List<RuleResultResponse> getResults() {
        return results;
    }
}
