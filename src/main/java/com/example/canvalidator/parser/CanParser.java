package com.example.canvalidator.parser;

import com.example.canvalidator.domain.CanMessage;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CanParser {

    public CanMessage parse(String rawFrame) {
        if (rawFrame == null || rawFrame.trim().isEmpty()) {
            throw new IllegalArgumentException("rawFrame must not be empty.");
        }

        String[] tokens = rawFrame.trim().split("\\s+");

        if (tokens.length < 2) {
            throw new IllegalArgumentException("CAN frame must include at least CAN ID and DLC.");
        }

        String canId = tokens[0].toUpperCase();
        validateHex(canId, "CAN ID");

        int dlc;
        try {
            dlc = Integer.parseInt(tokens[1]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("DLC must be an integer.");
        }

        List<Integer> dataBytes = new ArrayList<>();

        for (int i = 2; i < tokens.length; i++) {
            String dataToken = tokens[i].toUpperCase();
            validateHex(dataToken, "Data Byte");
            dataBytes.add(Integer.parseInt(dataToken, 16));
        }

        return new CanMessage(canId, dlc, dataBytes);
    }

    private void validateHex(String value, String fieldName) {
        if (!value.matches("^[0-9A-Fa-f]+$")) {
            throw new IllegalArgumentException(fieldName + " must be hexadecimal. value=" + value);
        }
    }
}