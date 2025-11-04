package com.javaweb.service;

import com.javaweb.model.TicketRequest;
import com.javaweb.model.TicketResponse;

public interface TicketService {
    TicketResponse create(TicketRequest req);
}
