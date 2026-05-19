package com.example.canvalidator.rule;

import com.example.canvalidator.domain.CanMessage;
import com.example.canvalidator.dto.RuleResultResponse;
import org.springframework.stereotype.Component;

@Component
public class DlcDataLengthRule implements ValidationRule {

    @Override
    public RuleResultResponse validate(CanMessage message) {
        int actualLength = message.getDataBytes().size();

        if (message.getDlc() != actualLength) {
            return new RuleResultResponse(
                    "DLC_DATA_LENGTH",
                    false,
                    "DLC does not match actual data byte length. dlc="
                            + message.getDlc() + ", actual=" + actualLength
            );
        }

        return new RuleResultResponse(
                "DLC_DATA_LENGTH",
                true,
                "DLC matches actual data byte length"
        );
    }
}