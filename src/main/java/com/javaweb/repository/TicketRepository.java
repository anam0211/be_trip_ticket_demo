package com.javaweb.repository;

import com.javaweb.model.TicketRequest;

public interface TicketRepository {
    // Trả về ticket_id vừa sinh (INT)
    int save(TicketRequest req, long totalPrice);
    void updateTotal(int ticketId, long totalPrice);
}
