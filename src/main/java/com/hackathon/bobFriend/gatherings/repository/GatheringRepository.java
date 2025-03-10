package com.hackathon.bobFriend.gatherings.repository;

import com.hackathon.bobFriend.gatherings.entity.GatheringEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GatheringRepository extends JpaRepository<GatheringEntity, Long> {

}
