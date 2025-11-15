package com.javaweb.repository;

import java.util.List;
import java.util.Map;

import com.javaweb.model.TripSearchRequest;
import com.javaweb.repository.entity.TripEntity;

public interface TripRepository {
    List<TripEntity> findAll(TripSearchRequest request);
    List<String> findBookedSeats(Integer tripId);
    Map<String, List<TripEntity>> findRoundTrip(TripSearchRequest req);


}
