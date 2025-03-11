package com.hackathon.bobFriend.gatherings.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hackathon.bobFriend.gatherings.entity.GatheringState;
import com.hackathon.bobFriend.gatherings.entity.GatheringTalkFlag;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class GatheringPostRequest {

    @NotNull
    private String title;

    @NotNull
    private String content;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime gathering_at;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime closing_at;

    @NotNull
    private int max_participant;

    @NotNull
    private GatheringState state;

    private String talk_thema;
    private GatheringTalkFlag talkFlag;
}
