package com.hackathon.bobFriend.gathering.entity;

import lombok.Getter;

@Getter
public enum GatherTalkFlag {
    I("내향적"),
    E("외향적"),
    N("상관없음"),
    S("소식좌"),
    B("푸드파이터");/* mbti i e half and half */

    private final String talkFlag;

    GatherTalkFlag(String talkFlag) {
        this.talkFlag = talkFlag;
    }
}
