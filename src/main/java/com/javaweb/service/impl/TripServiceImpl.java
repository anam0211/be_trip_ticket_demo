package com.javaweb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.model.TripDTO;
import com.javaweb.model.TripSearchRequest;
import com.javaweb.repository.TripRepository;
import com.javaweb.repository.entity.TripEntity;
import com.javaweb.service.TripService;

@Service
public class TripServiceImpl implements TripService {

    @Autowired
    private TripRepository tripRepository;

    // ✅ Lấy danh sách chuyến xe (search trip)
    @Override
    public List<TripDTO> findAll(TripSearchRequest request) {
        List<TripEntity> trips = tripRepository.findAll(request);
        List<TripDTO> result = new ArrayList<>();

        for (TripEntity t : trips) {
            TripDTO dto = new TripDTO();
            dto.setTrip_id(t.getTrip_id());
            dto.setStart_location(t.getStart_location());
            dto.setEnd_location(t.getEnd_location());
            dto.setStart_time(t.getStart_time());
            dto.setPrice(t.getPrice());
            dto.setStatus(t.getStatus());
            dto.setCoach_type(t.getCoach_type());
            dto.setCoach_id(t.getCoach_id());
            dto.setTotal_seat(t.getTotal_seat());

            // ❌ Không trả booked_seats ở đây (danh sách chuyến không cần)
            dto.setOrdered_seat(new ArrayList<>());


            result.add(dto);
        }
        return result;
    }

    // ✅ Xem chi tiết 1 chuyến, kèm ghế đã đặt
    @Override
    public TripDTO findById(Integer tripId) {
        TripEntity t = tripRepository.findById(tripId);
        if (t == null) {
            return null;
        }

        TripDTO dto = new TripDTO();
        dto.setTrip_id(t.getTrip_id());
        dto.setStart_location(t.getStart_location());
        dto.setEnd_location(t.getEnd_location());
        dto.setStart_time(t.getStart_time());
        dto.setPrice(t.getPrice());
        dto.setStatus(t.getStatus());
        dto.setCoach_type(t.getCoach_type());
        dto.setCoach_id(t.getCoach_id());
        dto.setTotal_seat(t.getTotal_seat());

        // ✅ Lấy danh sách ghế đã đặt từ DB
        List<String> seats = tripRepository.findBookedSeats(tripId);
        dto.setOrdered_seat(seats);

        return dto;
    }
}
