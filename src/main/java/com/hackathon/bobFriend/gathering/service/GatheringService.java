package com.hackathon.bobFriend.gathering.service;

import com.hackathon.bobFriend.common.exception.CustomErrorCode;
import com.hackathon.bobFriend.common.exception.CustomException;
import com.hackathon.bobFriend.gathering.dto.GatheringListResponse;
import com.hackathon.bobFriend.gathering.dto.GatheringRequest;
import com.hackathon.bobFriend.gathering.dto.GatheringResponse;
import com.hackathon.bobFriend.gathering.entity.GatherTalkFlag;
import com.hackathon.bobFriend.gathering.entity.GatheringEntity;
import com.hackathon.bobFriend.gathering.repositoty.GatheringRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import com.hackathon.bobFriend.places.dto.PlaceDto;
import com.hackathon.bobFriend.places.entity.Place;
import com.hackathon.bobFriend.places.repository.PlaceRepository;
import com.hackathon.bobFriend.users.dto.UserRequest;
import com.hackathon.bobFriend.users.dto.UserResponse;
import com.hackathon.bobFriend.users.entity.UserEntity;
import com.hackathon.bobFriend.users.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GatheringService {

    private final GatheringRepository repository;
    private final GatheringRepository gatheringRepository;
    private final UserRepository userRepository;
    private final PlaceRepository placeRepository;

    @Transactional
    public void create(GatheringRequest request) {
        GatheringEntity entity = request.toEntity();
        entity.setPlaceId(placeRepository.findByName(request.getPlaceName()).getId());
        repository.save(entity);
        GatheringResponse.of(entity);
    }

    @Transactional(readOnly = true)
    public GatheringResponse getOne(Long id) {

        GatheringEntity entity = findGathering(id);
        var response =  GatheringResponse.of(entity);

        List<UserResponse> userList = getCurrentParticipant(id);

        response.setCurrent_participant(userList.size());
        response.setParticipantList(userList);

        var place = placeRepository.findById(entity.getId()).get();

        var placeDto = PlaceDto.Response.builder()
                        .id(entity.getId())
                        .name(place.getName())
                        .content(place.getContent())
                        .address(place.getAddress())
                        .category(place.getCategory())
                        .build();

        response.setPlace(placeDto);
        return response;
    }

    @Transactional(readOnly = true)
    public List<GatheringResponse> getAll() {

        return repository.findAll().stream()
                .map(gathering -> {

                    var response = GatheringResponse.of(gathering);
                    var userList = getCurrentParticipant(gathering.getId());
                    response.setCurrent_participant(userList.size());
                    response.setParticipantList(userList);

                    var place = placeRepository.findById(gathering.getPlaceId()).get();
                    var placeDto = PlaceDto.Response.builder()
                            .id(place.getId())
                            .name(place.getName())
                            .content(place.getContent())
                            .address(place.getAddress())
                            .category(place.getCategory())
                            .build();

                    response.setPlace(placeDto);
                    return response;
                }).toList();
    }

    private GatheringEntity findGathering(Long id) {
        return repository.findById(id).orElseThrow(() ->
            new CustomException(CustomErrorCode.GATHERING_NOT_FOUND));
    }

    // 모임 참석
    public boolean attendGathering(Long gatheringId, Long userId) {
            var gathering = findGathering(gatheringId);
            int max = gathering.getMaxParticipant();

            List<Object[]> participantList = gatheringRepository.getCurrentParticipant(gatheringId);
            // 모집 인원 초과
            if(participantList.size() >= max) {
                return false;
            }
            List<Object[]> list = gatheringRepository.findParticipant(userId);
            // 이미 참석중인 모임이 있음
            if(!list.isEmpty()){
                return false;
            } else {
                gatheringRepository.addParticipant(gatheringId, userId);
                return true;
            }
    }

    public List<UserResponse> getCurrentParticipant(Long gatheringId) {
        List<Object[]> list = gatheringRepository.getCurrentParticipant(gatheringId);

        List<UserResponse> userList = list.stream().map(objectArray -> {
            Long userId = (Long)objectArray[1];
            var user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
            System.out.println("참석자 : " + user.getName());
            return UserResponse.toDto(user);
        }).toList();

        return userList;
    }

    // 모임 검색
    public List<GatheringResponse> searchGathering(String field, String word) {
        // 전체 검색
        if(field.isEmpty()) {
            return gatheringRepository.findByTitle(word).stream()
                    .map(GatheringResponse::of).toList();
        // 플래그 검색
        } else {
            GatherTalkFlag[] talkFlags = GatherTalkFlag.values();
            for(GatherTalkFlag talkFlag : talkFlags) {
                // 플래그 필터링
                if(talkFlag.name().equals(field)) {
                    // 제목 검색
                    List<GatheringEntity> list = gatheringRepository.findByTalkFlag(talkFlag);
                    return list.stream()
                            .map(entity -> {
                                if (entity.getTitle().equals(word)) {
                                    return GatheringResponse.of(entity);
                                } else {
                                    return null;
                                }
                            })
                            .filter(Objects::nonNull) // null 값 필터링
                            .toList();
                }
            }
        }
        return getAll();
    }
}
