package com.javaweb.repository;

import java.util.List;
import com.javaweb.model.TripSearchRequest;
import com.javaweb.repository.entity.TripEntity;

public interface TripRepository {
    List<TripEntity> findAll(TripSearchRequest request);
    TripEntity findById(Integer tripId);
    List<String> findBookedSeats(Integer tripId);

}
