package com.hackathon.bobFriend.gathering.controller;

import com.hackathon.bobFriend.common.dto.ApiResponse;
import com.hackathon.bobFriend.gathering.dto.GatheringListResponse;
import com.hackathon.bobFriend.gathering.dto.GatheringRequest;
import com.hackathon.bobFriend.gathering.dto.GatheringResponse;
import com.hackathon.bobFriend.gathering.entity.GatheringEntity;
import com.hackathon.bobFriend.gathering.service.GatheringService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bobfriend")
@RequiredArgsConstructor
public class GatheringController {

    private final GatheringService service;

    @PostMapping("/gatherings")
    public ResponseEntity<ApiResponse> createGathering(@Valid @RequestBody GatheringRequest request) {
        service.create(request);
        return ResponseEntity.ok().body(new ApiResponse(HttpStatus.CREATED.value(), "밥 친구 게시글이 생성되었습니다."));
    }

    @GetMapping("/gatherings/{gathering_id}")
    public ResponseEntity<GatheringResponse> getOneGathering(@PathVariable("gathering_id") Long gathering_id) {
        GatheringResponse response = service.getOne(gathering_id);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/gatherings")
    public ResponseEntity<GatheringListResponse> getOneGathering() {
        GatheringListResponse response = service.getAll();
        return ResponseEntity.ok().body(response);
    }
}
