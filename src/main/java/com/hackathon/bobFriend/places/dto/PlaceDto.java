package com.hackathon.bobFriend.places.dto;

import com.hackathon.bobFriend.places.entity.PlaceCategory;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class PlaceDto {
    @Getter
    public static class Post {
        public String name;
        public String content;
        public String address;
        public PlaceCategory category;
    }

    @Getter
    public static class Patch {
        public long id;
        public String name;
        public String content;
        public String address;
        public String category;
//        public int likeCount;
    }

    @Setter
    @Builder
    public static class Response {
        public long id;
        public String name;
        public String content;
        public String address;
        public PlaceCategory category;
        public String user_name;
//        public int likeCount;
    }
}