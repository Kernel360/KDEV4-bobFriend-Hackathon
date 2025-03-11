package com.hackathon.bobFriend.places.entity;

import lombok.Getter;

@Getter
public enum PlaceCategory {

    KOR("한식"),
    JPN("일식"),
    CHN("중식"),
    WES("양식"),
    ETC("기타");

    private final String category;

    PlaceCategory(String category) {
        this.category = category;
    }

}
