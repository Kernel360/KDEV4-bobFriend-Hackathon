package com.hackathon.bobFriend.gathering.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.hackathon.bobFriend.gathering.entity.GatherTalkFlag;
import com.hackathon.bobFriend.gathering.entity.GatheringEntity;
import com.hackathon.bobFriend.gathering.entity.GatheringState;
import com.hackathon.bobFriend.places.dto.PlaceDto;
import com.hackathon.bobFriend.users.dto.UserResponse;
import com.hackathon.bobFriend.users.entity.UserEntity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GatheringResponse {

    private Long id;
    private String title;
    private String content;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime gatheringAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime closingAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createdAt;

    private int maxParticipant;
    private GatheringState state;
    private String talkThema;
    private GatherTalkFlag talkFlag;
    private boolean isDeleted;

    private String userName;
    private int current_participant;
    private List<UserResponse> participantList = List.of();

    private PlaceDto.Response place;

    public static GatheringResponse of(GatheringEntity entity) {

        return GatheringResponse.builder()
            .id(entity.getId())
            .title(entity.getTitle())
            .content(entity.getContent())
            .gatheringAt(entity.getGatheringAt())
            .closingAt(entity.getClosingAt())
            .createdAt(entity.getCreatedAt())
            .maxParticipant(entity.getMaxParticipant())
            .state(entity.getState())
            .talkThema(entity.getTalkThema())
            .talkFlag(entity.getTalkFlag())
            .isDeleted(entity.isDeleted())
            .userName(entity.getUserName())
            .build();
    }
}
