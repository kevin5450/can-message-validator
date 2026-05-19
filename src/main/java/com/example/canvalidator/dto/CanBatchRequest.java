package com.example.canvalidator.dto;

import java.util.List;

public class CanBatchRequest {

    private List<String> rawFrames;

    public List<String> getRawFrames() {
        return rawFrames;
    }

    public void setRawFrames(List<String> rawFrames) {
        this.rawFrames = rawFrames;
    }
}
