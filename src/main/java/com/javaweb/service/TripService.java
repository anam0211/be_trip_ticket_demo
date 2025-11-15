package com.javaweb.service;

import java.util.List;
import java.util.Map;

import com.javaweb.model.TripDTO;
import com.javaweb.model.TripSearchRequest;

public interface TripService {

    // Tìm kiếm danh sách chuyến
    List<TripDTO> findAll(TripSearchRequest request);
    Map<String, List<TripDTO>> findRoundTrip(TripSearchRequest request);
}
