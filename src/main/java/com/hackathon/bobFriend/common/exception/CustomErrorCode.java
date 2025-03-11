package com.hackathon.bobFriend.common.exception;

import lombok.Getter;

@Getter
public enum CustomErrorCode {

    //Gathering
    GATHERING_NOT_FOUND("GA001", "해당 아이디의 밥 친구 게시글이 없습니다.");

    private final String code;
    private final String errorMessage;

    CustomErrorCode(String code, String errorMessage) {
        this.code = code;
        this.errorMessage = errorMessage;
    }
}