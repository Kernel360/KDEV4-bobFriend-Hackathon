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

    @Enumerated(EnumType.STRING)
    private PlaceCategory category;

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


}