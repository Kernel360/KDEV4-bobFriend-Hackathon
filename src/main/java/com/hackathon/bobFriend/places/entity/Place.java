package com.hackathon.bobFriend.places.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Setter
@Getter
@Entity
@Table(name = "places")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    // 유니크 키
    private String name;

    private String content;

    @NotNull
    private String address;

    private String category;

//    private int likeCount = 0;


//    @OneToMany
//    private Gathering gathering;

//    @OneToMany
//    private Like like;

//    private boolean isDeleted;
//
//    // 삭제됨 으로 상태 변경
//    public void changeTodeleted() {
//        this.isDeleted = true;
//    }

    public enum Category {
        KOR("한식"),
        JPN("일식"),
        CHN("중식"),
        WES("양식");

        private String category;

        Category(String category) {
            this.category = category;
        }
    }
}
