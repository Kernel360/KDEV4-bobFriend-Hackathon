package com.hackathon.bobFriend.gathering.dto;

import com.hackathon.bobFriend.gathering.entity.GatherTalkFlag;
import com.hackathon.bobFriend.gathering.entity.GatheringEntity;
import com.hackathon.bobFriend.gathering.entity.GatheringState;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class GatheringListResponse {

    private List<GatheringResponse> responses;

    public static GatheringListResponse of(List<GatheringEntity> entities) {
        List<GatheringResponse> responses = entities.stream()
            .map(GatheringResponse::of)
            .toList();

        return GatheringListResponse.builder()
            .responses(responses)
            .build();
    }
}
