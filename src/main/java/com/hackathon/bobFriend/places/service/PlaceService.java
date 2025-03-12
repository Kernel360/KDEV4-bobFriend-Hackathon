package com.hackathon.bobFriend.places.service;

import com.hackathon.bobFriend.places.dto.PlaceDto;
import com.hackathon.bobFriend.places.entity.Place;
import com.hackathon.bobFriend.places.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaceService {
    private final PlaceRepository placeRepository;

    // 장소 생성
    public PlaceDto.Response create(PlaceDto.Post post) {
        Place place = Place.builder()
                .name(post.getName())
                .content(post.getContent())
                .address(post.getAddress())
                .category(post.getCategory())
                .build();
        Place savedPlace = placeRepository.save(place);
        PlaceDto.Response response = PlaceDto.Response.builder()
                .id(savedPlace.getId())
                .name(savedPlace.getName())
                .content(savedPlace.getContent())
                .address(savedPlace.getAddress())
                .category(savedPlace.getCategory())
                // like count, category
                .build();
        return response;
    }

    // 장소 수정
    public PlaceDto.Response update(PlaceDto.Patch patch) {
        Place findPlace = placeRepository.findById(patch.id).get();

        // 수정된 사항 있으면 변경
        // 이름
        if(findPlace.getName() != patch.getName())
            findPlace.setName(patch.getName());
        // 내용
        if(findPlace.getContent() != patch.getContent())
            findPlace.setContent(patch.getContent());
        // 주소
        if(findPlace.getAddress() != patch.getAddress())
            findPlace.setAddress(patch.getAddress());
        // 카테고리, 좋아요
        Place savedPlace = placeRepository.save(findPlace);
        PlaceDto.Response response = PlaceDto.Response.builder()
                .id(savedPlace.getId())
                .name(savedPlace.getName())
                .content(savedPlace.getContent())
                .address(savedPlace.getAddress())
                // category, like
                .build();
        return response;
    }

    // 장소 리스트 보기
    public List<PlaceDto.Response> get() {
        List<PlaceDto.Response> responses = placeRepository.findAll().stream()
                .map(p -> PlaceDto.Response.builder()
                        .id(p.getId())
                        .name(p.getName())
                        .content(p.getContent())
                        .address(p.getAddress())
                        // like count, category
                        .build())
                .toList();
        return responses;
    }

    // 장소 하나 보기
    public PlaceDto.Response get(long id) {
        Place findPlace = placeRepository.findById(id).get();
        PlaceDto.Response response = PlaceDto.Response.builder()
                .id(findPlace.getId())
                .name(findPlace.getName())
                .content(findPlace.getContent())
                .address(findPlace.getAddress())
                // category, like
                .build();
        return response;
    }

    // 장소 삭제 (상태 변경)
    public void delete(long id) {
        Place findPlace = placeRepository.findById(id).get();
        placeRepository.delete(findPlace);
    }
}