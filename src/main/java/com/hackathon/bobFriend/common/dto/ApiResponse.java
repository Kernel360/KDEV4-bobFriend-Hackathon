package com.hackathon.bobFriend.common.dto;

import lombok.Getter;

@Getter
public class ApiResponse {
    private final int statusCode;
    private final String statusMessage;

    public ApiResponse(int statusCode, String statusMessage) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }
}
