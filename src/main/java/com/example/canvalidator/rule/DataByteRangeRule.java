package com.example.canvalidator.rule;

import com.example.canvalidator.domain.CanMessage;
import com.example.canvalidator.dto.RuleResultResponse;
import org.springframework.stereotype.Component;

@Component
public class DataByteRangeRule implements ValidationRule {

    @Override
    public RuleResultResponse validate(CanMessage message) {
        for (Integer dataByte : message.getDataBytes()) {
            if (dataByte < 0x00 || dataByte > 0xFF) {
                return new RuleResultResponse(
                        "DATA_BYTE_RANGE",
                        false,
                        "Data byte is out of range. allowed=0x00~0xFF, input=0x"
                                + Integer.toHexString(dataByte).toUpperCase()
                );
            }
        }

        return new RuleResultResponse(
                "DATA_BYTE_RANGE",
                true,
                "Data byte range validation passed"
        );
    }
}