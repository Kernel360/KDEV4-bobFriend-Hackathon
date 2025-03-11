package com.hackathon.bobFriend.gathering.service;

import com.hackathon.bobFriend.common.exception.CustomErrorCode;
import com.hackathon.bobFriend.common.exception.CustomException;
import com.hackathon.bobFriend.gathering.dto.GatheringListResponse;
import com.hackathon.bobFriend.gathering.dto.GatheringRequest;
import com.hackathon.bobFriend.gathering.dto.GatheringResponse;
import com.hackathon.bobFriend.gathering.entity.GatheringEntity;
import com.hackathon.bobFriend.gathering.repositoty.GatheringRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GatheringService {

    private final GatheringRepository repository;

    @Transactional
    public void create(GatheringRequest request) {
        GatheringEntity entity = request.toEntity();
        repository.save(entity);
        GatheringResponse.of(entity);
    }

    @Transactional(readOnly = true)
    public GatheringResponse getOne(Long id) {

        GatheringEntity entity = findGathering(id);
        return GatheringResponse.of(entity);
    }

    @Transactional(readOnly = true)
    public GatheringListResponse getAll() {

        List<GatheringEntity> entities = repository.findAll();
        return GatheringListResponse.of(entities);
    }

    private GatheringEntity findGathering(Long id) {
        return repository.findById(id).orElseThrow(() ->
            new CustomException(CustomErrorCode.GATHERING_NOT_FOUND));
    }
}
