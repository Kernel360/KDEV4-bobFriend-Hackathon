package com.hackathon.bobFriend.gathering.repositoty;

import com.hackathon.bobFriend.gathering.entity.GatheringEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface GatheringRepository extends JpaRepository<GatheringEntity, Long> {

    @Modifying
    @Query(value = "INSERT INTO participants (gathering_id, user_id) VALUES (:gatheringId, :userId)", nativeQuery = true)
    void addParticipant(@Param("gatheringId") Long gatheringId, @Param("userId") Long userId);

    @Modifying
    @Query(value = "select * from participants where user_id = :userId", nativeQuery = true)
    List<Object[]> findParticipant(@Param("userId") Long userId);

    @Modifying
    @Query(value = "select * from participants where gathering_id = :gatheringId", nativeQuery = true)
    List<Object[]> getCurrentParticipant(@Param("gatheringId") Long gatheringId);

}
