package com.example.canvalidator.dto;

public class RuleResultResponse {

    private final String ruleName;
    private final boolean valid;
    private final String message;

    public RuleResultResponse(String ruleName, boolean valid, String message) {
        this.ruleName = ruleName;
        this.valid = valid;
        this.message = message;
    }

    public String getRuleName() {
        return ruleName;
    }

    public boolean isValid() {
        return valid;
    }

    public String getMessage() {
        return message;
    }
}
