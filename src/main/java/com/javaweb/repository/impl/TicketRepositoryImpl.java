package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.javaweb.model.TicketRequest;
import com.javaweb.repository.TicketRepository;
import com.javaweb.util.DBUtil;

public class TicketRepositoryImpl implements TicketRepository {

	@Override
	public int save(TicketRequest req, long totalPrice) {
	    try (Connection conn = DBUtil.getConnection()) {

	        String sql = "INSERT INTO tickets(" +
	                "account_id, depart_trip_id, return_trip_id, ticket_type, total_price, payment_status, created_time" +
	                ") VALUES (?, ?, ?, ?, ?, ?, NOW())";

	        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

	        ps.setInt(1, req.getAccount_id()); // INT
	        ps.setInt(2, req.getOutbound().getTrip_id()); // INT
	        Integer returnTripId = 
	        	    req.getReturnTrip() != null ? req.getReturnTrip().getTrip_id() : null;

	        	if (returnTripId != null) {
	        	    ps.setInt(3, returnTripId);
	        	} else {
	        	    ps.setNull(3, java.sql.Types.INTEGER);
	        	}
	        ps.setString(4, req.getTicket_type());
	        ps.setLong(5, totalPrice);
	        ps.setString(6, "pending");
	        ps.executeUpdate();

	        ResultSet rs = ps.getGeneratedKeys();
	        if (rs.next()) return rs.getInt(1);

	        return 0;
	    } catch (Exception e) {
	        e.printStackTrace();
	        throw new RuntimeException("Lỗi lưu ticket");
	    }
	}


	@Override
	public void updateTotal(int ticketId, long totalPrice) {
	    try (Connection conn = DBUtil.getConnection()) {
	        String sql = "UPDATE Tickets SET total_price=? WHERE ticket_id=?";
	        PreparedStatement ps = conn.prepareStatement(sql);
	        ps.setLong(1, totalPrice);
	        ps.setInt(2, ticketId);
	        ps.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	        throw new RuntimeException("Lỗi cập nhật tổng tiền");
	    }
	}

}
