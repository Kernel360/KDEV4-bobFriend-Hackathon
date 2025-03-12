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

    public static List<GatheringResponse> of(List<GatheringEntity> entities) {
         return entities.stream()
            .map(GatheringResponse::of)
            .toList();

    }
}
