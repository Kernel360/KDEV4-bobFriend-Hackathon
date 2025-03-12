package com.hackathon.bobFriend.places.repository;

import com.hackathon.bobFriend.places.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<Place, Long> {

    public Place findByName(String name);
}