package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.javaweb.repository.TicketSeatRepository;
import com.javaweb.util.DBUtil;

public class TicketSeatRepositoryImpl implements TicketSeatRepository {

    @Override
    public void save(int ticketId, int tripId, String seatLabel) {
        try (Connection conn = DBUtil.getConnection()) {
            String sql = "INSERT INTO booked_seats(ticket_id, trip_id, seat_label) VALUES (?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, ticketId);
            ps.setInt(2, tripId);
            ps.setString(3, seatLabel);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Lỗi lưu ghế đã đặt");
        }
    }

    @Override
    public boolean seatBooked(int tripId, String seatLabel) {
        try (Connection conn = DBUtil.getConnection()) {
            String sql = "SELECT COUNT(*) FROM booked_seats WHERE trip_id=? AND seat_label=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, tripId);
            ps.setString(2, seatLabel);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1) > 0;
        } catch (Exception e) {
            e.printStackTrace();
            // an toàn: coi như ghế đã bị đặt
            return true;
        }
    }
}
