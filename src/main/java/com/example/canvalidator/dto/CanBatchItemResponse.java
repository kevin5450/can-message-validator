package com.example.canvalidator.dto;

import java.util.List;

public class CanBatchItemResponse {

    private final String rawFrame;
    private final boolean valid;
    private final String canId;
    private final Integer dlc;
    private final List<String> dataBytes;
    private final List<RuleResultResponse> results;

    public CanBatchItemResponse(
            String rawFrame,
            boolean valid,
            String canId,
            Integer dlc,
            List<String> dataBytes,
            List<RuleResultResponse> results
    ) {
        this.rawFrame = rawFrame;
        this.valid = valid;
        this.canId = canId;
        this.dlc = dlc;
        this.dataBytes = dataBytes;
        this.results = results;
    }

    public String getRawFrame() {
        return rawFrame;
    }

    public boolean isValid() {
        return valid;
    }

    public String getCanId() {
        return canId;
    }

    public Integer getDlc() {
        return dlc;
    }

    public List<String> getDataBytes() {
        return dataBytes;
    }

    public List<RuleResultResponse> getResults() {
        return results;
    }
}
