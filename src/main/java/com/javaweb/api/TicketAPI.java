package com.javaweb.api;

import org.springframework.web.bind.annotation.*;

import com.javaweb.model.TicketRequest;
import com.javaweb.model.TicketResponse;
import com.javaweb.service.impl.TicketServiceImpl;
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:8386"}, allowCredentials = "true")
@RestController
@RequestMapping("/tickets")
public class TicketAPI {

    private TicketServiceImpl service = new TicketServiceImpl();

    @PostMapping("")
    public TicketResponse createTicket(@RequestBody TicketRequest req) {
        return service.create(req);
    }
}
