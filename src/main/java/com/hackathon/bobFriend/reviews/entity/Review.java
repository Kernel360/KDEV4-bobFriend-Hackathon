package com.hackathon.bobFriend.reviews.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reviews")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    // id: BIGINT, 기본 키, 자동 증가
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // user_id: BIGINT, NOT NULL
    @Column(name = "user_id", nullable = false)
    private Long userId;

    // gathering_id: BIGINT, NOT NULL
    @Column(name = "gathering_id", nullable = false)
    private Long gatheringId;

    // content: VARCHAR(300)
    @Column(nullable = false, length = 300)
    private String content;

    // emotion: ENUM("AGAIN","GOOD","SOSO","BAD")
    // EnumType.STRING을 사용하여 문자열 형태로 저장
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Emotion emotion;

    // is_public: BOOLEAN, 기본값 TRUE
    @Column(name = "is_public", nullable = false, columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean isPublic = true;
}