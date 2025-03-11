package com.hackathon.bobFriend.gathering.repositoty;

import com.hackathon.bobFriend.gathering.entity.GatheringEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GatheringRepository extends JpaRepository<GatheringEntity, Long> {

}
