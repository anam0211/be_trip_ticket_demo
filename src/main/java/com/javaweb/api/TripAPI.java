package com.javaweb.api;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.model.TripDTO;
import com.javaweb.model.TripSearchRequest;
import com.javaweb.service.TripService;
@CrossOrigin(origins = "http://localhost:8386")
@RestController
@RequestMapping("")
public class TripAPI {

    @Autowired
    private TripService tripService;

    @GetMapping("/search")
    public ResponseEntity<?> searchTrip(TripSearchRequest request) {
        List<TripDTO> data = tripService.findAll(request);
        return ResponseEntity.ok(Map.of("data", data)); // wrap data
    }

    @GetMapping("/schedules")
    public ResponseEntity<?> schedules(TripSearchRequest request) {
        List<TripDTO> data = tripService.findAll(request);
        return ResponseEntity.ok(Map.of("data", data)); //  wrap data
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTripDetail(@PathVariable Integer id) {
        TripDTO t = tripService.findById(id);
        if (t == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(Map.of("data", t)); // wrap object
    }
}
