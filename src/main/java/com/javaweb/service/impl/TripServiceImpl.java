package com.javaweb.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    // ✅ 1. Tìm chuyến 1 chiều (CÓ ordered_seat)
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

            // ✅ Lấy ghế đã đặt
            dto.setOrdered_seat(tripRepository.findBookedSeats(t.getTrip_id()));

            result.add(dto);
        }

        return result;
    }

    // ✅ 2. Chi tiết chuyến
    @Override
    public TripDTO findById(Integer tripId) {
        TripEntity t = tripRepository.findById(tripId);
        if (t == null) return null;

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

        // Lấy ghế đã đặt
        dto.setOrdered_seat(tripRepository.findBookedSeats(tripId));

        return dto;
    }

    //  3. Tìm chuyến khứ hồi (CÓ ordered_seat cho cả đi + về)
    @Override
    public Map<String, List<TripDTO>> findRoundTrip(TripSearchRequest req) {

        // Chuyến đi
        List<TripDTO> departTrips = findAll(req);

        // Tạo request chuyến về (đổi chiều, đổi ngày)
        TripSearchRequest returnReq = new TripSearchRequest();
        returnReq.setStart_location(req.getEnd_location());
        returnReq.setEnd_location(req.getStart_location());
        returnReq.setStart_date(req.getEnd_date()); 
        returnReq.setEnd_date(null); 
        returnReq.setPage(req.getPage());
        returnReq.setSize(req.getSize());

      
        List<TripDTO> returnTrips = findAll(returnReq);

        
        return Map.of(
                "depart_trips", departTrips,
                "return_trips", returnTrips
        );
    }
}
