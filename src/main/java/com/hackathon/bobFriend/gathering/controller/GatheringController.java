package com.hackathon.bobFriend.gathering.controller;

import com.hackathon.bobFriend.common.dto.ApiResponse;
import com.hackathon.bobFriend.common.security.UserDetailsImpl;
import com.hackathon.bobFriend.gathering.dto.GatheringListResponse;
import com.hackathon.bobFriend.gathering.dto.GatheringRequest;
import com.hackathon.bobFriend.gathering.dto.GatheringResponse;
import com.hackathon.bobFriend.gathering.entity.GatheringEntity;
import com.hackathon.bobFriend.gathering.service.GatheringService;
import com.hackathon.bobFriend.users.dto.UserRequest;
import com.hackathon.bobFriend.users.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bobfriend/gatherings")
@RequiredArgsConstructor
public class GatheringController {

    private final GatheringService service;

    @PostMapping
    public ResponseEntity<ApiResponse> createGathering(
        @AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody GatheringRequest request
    ) {
        service.create(userDetails.getUser(), request);
        return ResponseEntity.ok().body(new ApiResponse(HttpStatus.CREATED.value(), "밥 친구 게시글이 생성되었습니다."));
    }

    @GetMapping("/{gathering_id}")
    public ResponseEntity<GatheringResponse> getOneGathering(@PathVariable("gathering_id") Long gathering_id) {
        GatheringResponse response = service.getOne(gathering_id);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping
    public ResponseEntity<GatheringListResponse> getOneGathering() {
        GatheringListResponse response = service.getAll();
        return ResponseEntity.ok().body(response);
    }
}
