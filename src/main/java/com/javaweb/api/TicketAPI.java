package com.javaweb.api;

import org.springframework.web.bind.annotation.*;

import com.javaweb.model.TicketRequest;
import com.javaweb.model.TicketResponse;
import com.javaweb.service.impl.TicketServiceImpl;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("")
public class TicketAPI {

    private TicketServiceImpl service = new TicketServiceImpl();

    @PostMapping("/tickets")
    public TicketResponse createTicket(@RequestBody TicketRequest req) {
        return service.create(req);
    }
}
