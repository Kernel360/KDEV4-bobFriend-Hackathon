package com.hackathon.bobFriend.gathering.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hackathon.bobFriend.gathering.entity.GatherTalkFlag;
import com.hackathon.bobFriend.gathering.entity.GatheringEntity;
import com.hackathon.bobFriend.gathering.entity.GatheringState;
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
public class GatheringResponse {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime gatheringAt;
    private LocalDateTime closingAt;
    private Timestamp createdAt;
    private int maxParticipant;
    private GatheringState state;
    private String talkThema;
    private GatherTalkFlag talkFlag;
    private boolean isDeleted;

    public static GatheringResponse of(GatheringEntity entity) {
        return GatheringResponse.builder()
            .id(entity.getId())
            .title(entity.getTitle())
            .content(entity.getContent())
            .gatheringAt(entity.getGatheringAt())
            .closingAt(entity.getClosingAt())
            .maxParticipant(entity.getMaxParticipant())
            .state(entity.getState())
            .talkThema(entity.getTalkThema())
            .talkFlag(entity.getTalkFlag())
            .build();
    }
}
