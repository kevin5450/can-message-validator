package com.example.canvalidator.rule;

import com.example.canvalidator.domain.CanMessage;
import com.example.canvalidator.dto.RuleResultResponse;

public interface ValidationRule {
    RuleResultResponse validate(CanMessage message);
}
