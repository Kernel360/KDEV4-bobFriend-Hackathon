package com.hackathon.bobFriend.gathering.controller;

import com.hackathon.bobFriend.common.dto.ApiResponse;
import com.hackathon.bobFriend.gathering.dto.GatheringListResponse;
import com.hackathon.bobFriend.gathering.dto.GatheringRequest;
import com.hackathon.bobFriend.gathering.dto.GatheringResponse;
import com.hackathon.bobFriend.gathering.entity.GatheringEntity;
import com.hackathon.bobFriend.gathering.service.GatheringService;
import com.hackathon.bobFriend.users.dto.UserRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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

    // 메인 페이지
    @GetMapping("/all")
    public ResponseEntity<List<GatheringResponse>> getOneGathering() {
        System.out.println("요청됨");
        return ResponseEntity.ok().body(service.getAll());
    }

    // 모임 참석하기
    @PostMapping("/gatherings/{gathering_id}/attend")
    public ResponseEntity attendGathering(@PathVariable Long gathering_id, @RequestBody UserRequest userRequest) {
        if(service.attendGathering(gathering_id, userRequest.getId())) {
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

     @GetMapping("/gatherings/search")
    public ResponseEntity<List<GatheringResponse>> searchGathering(@RequestParam String field, @RequestParam String word) {
        var list = service.searchGathering(field, word);
        return ResponseEntity.ok().body(list);
     }
}
