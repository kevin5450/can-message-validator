package com.example.canvalidator.rule;

import com.example.canvalidator.domain.CanMessage;
import com.example.canvalidator.dto.RuleResultResponse;
import org.springframework.stereotype.Component;

@Component
public class DlcRangeRule implements ValidationRule {

    @Override
    public RuleResultResponse validate(CanMessage message) {
        int dlc = message.getDlc();

        if (dlc < 0 || dlc > 8) {
            return new RuleResultResponse(
                    "DLC_RANGE",
                    false,
                    "DLC is out of range. allowed=0~8, input=" + dlc
            );
        }

        return new RuleResultResponse(
                "DLC_RANGE",
                true,
                "DLC range validation passed"
        );
    }
}