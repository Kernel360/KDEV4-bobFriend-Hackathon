package com.hackathon.bobFriend.gathering.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="gatherings")
public class GatheringEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private LocalDateTime gatheringAt;

    @Column(nullable = false)
    private LocalDateTime closingAt;

    @CreationTimestamp
    private Timestamp createdAt;

    @Column(nullable = false)
    private int maxParticipant;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private GatheringState state;

    private String talkThema;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private GatherTalkFlag talkFlag;

    private boolean isDeleted;

    private Long userId;

    private Long placeId;

    private String userName;
}
