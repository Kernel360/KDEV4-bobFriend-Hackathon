package com.hackathon.bobFriend.places.controller;

import com.hackathon.bobFriend.places.dto.PlaceDto;
import com.hackathon.bobFriend.places.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/places")
@RequiredArgsConstructor
public class PlaceController {

    private final PlaceService placeService;

    @PostMapping // 장소 등록
    public ResponseEntity createPlace(@RequestBody PlaceDto.Post placePostDto) {
        PlaceDto.Response response = placeService.create(placePostDto);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @PatchMapping // 장소 수정
    public ResponseEntity updatePlace(@RequestBody PlaceDto.Patch placePatchDto) {
        PlaceDto.Response response = placeService.update(placePatchDto);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping // 장소 전체 보기
    // createdAt 순으로 정렬? 좋아요 순으로 정렬?
//    public ResponseEntity getAllPlace(@RequestParam(value = "sort", defaultValue = "") String ss) {
    public ResponseEntity getAllPlace() {
        List<PlaceDto.Response> response = placeService.get();
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/{placeId}") // 장소 하나 보기
    public ResponseEntity getPlace(@PathVariable("placeId") long id) {
        PlaceDto.Response response = placeService.get(id);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @DeleteMapping("/{placeId}") // 장소 삭제
    public ResponseEntity deletePlace(@PathVariable("placeId") long id) {
        placeService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
