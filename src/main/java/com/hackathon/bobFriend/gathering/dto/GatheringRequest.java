package com.hackathon.bobFriend.gathering.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hackathon.bobFriend.gathering.entity.GatherTalkFlag;
import com.hackathon.bobFriend.gathering.entity.GatheringEntity;
import com.hackathon.bobFriend.gathering.entity.GatheringState;
import com.hackathon.bobFriend.users.entity.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class GatheringRequest {

    @NotNull
    private String title;

    @NotNull
    private String content;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime gatheringAt;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime closingAt;

    private Timestamp createdAt;

    @NotNull
    private int maxParticipant;

    @NotNull
    private GatheringState state;

    private String talkThema;

    @NotNull
    private GatherTalkFlag talkFlag;

    private boolean isDeleted;

    public GatheringEntity toEntity(UserEntity user) {
        return GatheringEntity.builder()
            .title(this.title)
            .content(this.content)
            .gatheringAt(this.gatheringAt)
            .closingAt(this.closingAt)
            .maxParticipant(this.maxParticipant)
            .state(this.state)
            .talkThema(this.talkThema)
            .talkFlag(this.talkFlag)
            .user(user)
            .build();
    }
}
