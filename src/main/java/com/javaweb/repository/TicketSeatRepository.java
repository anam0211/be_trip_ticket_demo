package com.javaweb.repository;

public interface TicketSeatRepository {
    void save(int ticketId, int tripId, String seatLabel);
    boolean seatBooked(int tripId, String seatLabel);
}
