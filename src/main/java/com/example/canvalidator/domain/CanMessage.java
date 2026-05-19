package com.example.canvalidator.domain;

import java.util.List;

public class CanMessage {

    private final String canId;
    private final int dlc;
    private final List<Integer> dataBytes;

    public CanMessage(String canId, int dlc, List<Integer> dataBytes) {
        this.canId = canId;
        this.dlc = dlc;
        this.dataBytes = dataBytes;
    }

    public String getCanId() {
        return canId;
    }

    public int getDlc() {
        return dlc;
    }

    public List<Integer> getDataBytes() {
        return dataBytes;
    }

    public List<String> getDataBytesAsHex() {
        return dataBytes.stream()
                .map(value -> String.format("%02X", value))
                .toList();
    }
}
