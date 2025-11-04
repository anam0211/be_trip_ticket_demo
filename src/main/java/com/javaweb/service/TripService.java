package com.javaweb.service;

import java.util.List;

import com.javaweb.model.TripDTO;
import com.javaweb.model.TripSearchRequest;

public interface TripService {

    // Tìm kiếm danh sách chuyến
    List<TripDTO> findAll(TripSearchRequest request);

    // Lấy chi tiết chuyến
    TripDTO findById(Integer tripId);
    
}
