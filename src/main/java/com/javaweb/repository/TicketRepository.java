package com.javaweb.repository;

import java.sql.Connection;
import com.javaweb.model.TicketRequest;

public interface TicketRepository {

    int save(Connection conn, TicketRequest req, long totalPrice);

    void updateTotal(Connection conn, int ticketId, long totalPrice);
}
