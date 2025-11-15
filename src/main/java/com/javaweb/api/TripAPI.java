package com.javaweb.api;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.javaweb.model.TripDTO;
import com.javaweb.model.TripSearchRequest;
import com.javaweb.service.TripService;

//@CrossOrigin(origins = "*")
@RestController
@RequestMapping("")
public class TripAPI {

    @Autowired
    private TripService tripService;


    // API tìm chuyến (1 chiều + khứ hồi)
   @GetMapping("/schedules")
public ResponseEntity<?> schedules(TripSearchRequest request) {

    boolean isRoundTrip =
        "true".equalsIgnoreCase(request.getRound_trip()) ||
        (request.getStart_date() != null && request.getEnd_date() != null);

    if (isRoundTrip) {

        Map<String, List<TripDTO>> data = tripService.findRoundTrip(request);

        List<TripDTO> depart = data.get("depart_trips");
        List<TripDTO> back   = data.get("return_trips");

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("outbound", depart != null ? depart : List.of());
        result.put("returnTrip", back != null ? back : List.of());

        return ResponseEntity.ok(result);
    }

    // Một chiều
    List<TripDTO> list = tripService.findAll(request);

    Map<String, Object> result = new LinkedHashMap<>();
    result.put("data", list != null ? list : List.of());

    return ResponseEntity.ok(result);
}



//    
//    // Chi tiết 1 chuyến
//    
//    @GetMapping("/{id}")
//    public ResponseEntity<?> getTripDetail(@PathVariable Integer id) {
//        TripDTO t = tripService.findById(id);
//        if (t == null) return ResponseEntity.notFound().build();
//        return ResponseEntity.ok(Map.of("data", t));
//    }
}
