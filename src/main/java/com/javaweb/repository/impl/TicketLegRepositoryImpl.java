//package com.javaweb.repository.impl;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.Timestamp;
//
//import com.javaweb.model.TicketRequest;
//import com.javaweb.repository.TicketLegRepository;
//import com.javaweb.util.DBUtil;
//
//public class TicketLegRepositoryImpl implements TicketLegRepository {
//
//    @Override
//    public boolean seatBooked(String tripId, String seat) {
//        try (Connection conn = DBUtil.getConnection()) {
//            String sql = "SELECT COUNT(*) FROM ticket_leg WHERE trip_id=? AND FIND_IN_SET(?, seats)";
//            PreparedStatement ps = conn.prepareStatement(sql);
//            ps.setString(1, tripId);
//            ps.setString(2, seat);
//            ResultSet rs = ps.executeQuery();
//            rs.next();
//            return rs.getInt(1) > 0;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return true;
//    }
//
//    @Override
//    public void save(String ticketId, String type, TicketRequest.Leg leg) {
//        try (Connection conn = DBUtil.getConnection()) {
//
//            String sql = "INSERT INTO ticket_leg(ticket_id,leg_type,trip_id,coach_id,start_time,coach_type,seats) "
//                    + "VALUES (?,?,?,?,?,?,?)";
//
//            PreparedStatement ps = conn.prepareStatement(sql);
//            ps.setString(1, ticketId);
//            ps.setString(2, type);
//            ps.setString(3, leg.getTrip_id());
//            ps.setString(4, leg.getCoach_id());
//            ps.setTimestamp(5, Timestamp.from(leg.getStart_time()));
//            ps.setString(6, leg.getCoach_id());
//            ps.setString(7, String.join(",", leg.getOrdered_seat()));
//            ps.executeUpdate();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
