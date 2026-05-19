package com.example.canvalidator.rule;

import com.example.canvalidator.domain.CanMessage;
import com.example.canvalidator.dto.RuleResultResponse;
import org.springframework.stereotype.Component;

@Component
public class CanIdRangeRule implements ValidationRule {

    private static final int STANDARD_CAN_MAX_ID = 0x7FF;

    @Override
    public RuleResultResponse validate(CanMessage message) {
        int id = Integer.parseInt(message.getCanId(), 16);

        if (id < 0 || id > STANDARD_CAN_MAX_ID) {
            return new RuleResultResponse(
                    "CAN_ID_RANGE",
                    false,
                    "CAN ID is out of standard range. allowed=0x000~0x7FF, input=0x" + message.getCanId()
            );
        }

        return new RuleResultResponse(
                "CAN_ID_RANGE",
                true,
                "Standard CAN ID range validation passed"
        );
    }
}