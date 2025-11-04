package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.javaweb.model.TripSearchRequest;
import com.javaweb.repository.TripRepository;
import com.javaweb.repository.entity.TripEntity;
import com.javaweb.util.DBUtil;

@Repository
public class TripRepositoryImpl implements TripRepository {

    @Override
    public List<TripEntity> findAll(TripSearchRequest request) {
        List<TripEntity> result = new ArrayList<>();

        StringBuilder sql = new StringBuilder(
            "SELECT t.trip_id, t.start_location, t.end_location, t.start_time, " +
            "t.price, t.status, c.coach_type, c.coach_id, c.total_seat " +
            "FROM trips t " +
            "JOIN coachs c ON t.coach_id = c.coach_id " +
            "WHERE 1=1 "
        );

        if (request.getStart_location() != null && !request.getStart_location().isEmpty()) {
            sql.append("AND t.start_location LIKE ? ");
        }
        if (request.getEnd_location() != null && !request.getEnd_location().isEmpty()) {
            sql.append("AND t.end_location LIKE ? ");
        }

        // ✅ hỗ trợ start_date / end_date FE gửi
        if (request.getStart_date() != null && !request.getStart_date().isEmpty()) {
            sql.append("AND DATE(t.start_time) >= ? ");
        }
        if (request.getEnd_date() != null && !request.getEnd_date().isEmpty()) {
            sql.append("AND DATE(t.start_time) <= ? ");
        }

        if (request.getCoach_type() != null && !request.getCoach_type().isEmpty()) {
            sql.append("AND c.coach_type = ? ");
        }
        if (request.getPrice_min() != null) {
            sql.append("AND t.price >= ? ");
        }
        if (request.getPrice_max() != null) {
            sql.append("AND t.price <= ? ");
        }
        if (request.getTime_min() != null && !request.getTime_min().isEmpty()) {
            sql.append("AND TIME(t.start_time) >= ? ");
        }
        if (request.getTime_max() != null && !request.getTime_max().isEmpty()) {
            sql.append("AND TIME(t.start_time) <= ? ");
        }
        if (request.getSeat_min() != null) {
            sql.append("AND c.total_seat >= ? ");
        }

        sql.append("ORDER BY t.start_time ASC ");
        sql.append("LIMIT ? OFFSET ?");

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql.toString())) {

            int index = 1;

            if (request.getStart_location() != null && !request.getStart_location().isEmpty()) {
                stmt.setString(index++, "%" + request.getStart_location() + "%");
            }
            if (request.getEnd_location() != null && !request.getEnd_location().isEmpty()) {
                stmt.setString(index++, "%" + request.getEnd_location() + "%");
            }
            if (request.getStart_date() != null && !request.getStart_date().isEmpty()) {
                stmt.setString(index++, request.getStart_date());
            }
            if (request.getEnd_date() != null && !request.getEnd_date().isEmpty()) {
                stmt.setString(index++, request.getEnd_date());
            }
            if (request.getCoach_type() != null && !request.getCoach_type().isEmpty()) {
                stmt.setString(index++, request.getCoach_type());
            }
            if (request.getPrice_min() != null) {
                stmt.setLong(index++, request.getPrice_min());
            }
            if (request.getPrice_max() != null) {
                stmt.setLong(index++, request.getPrice_max());
            }
            if (request.getTime_min() != null && !request.getTime_min().isEmpty()) {
                stmt.setString(index++, request.getTime_min());
            }
            if (request.getTime_max() != null && !request.getTime_max().isEmpty()) {
                stmt.setString(index++, request.getTime_max());
            }
            if (request.getSeat_min() != null) {
                stmt.setInt(index++, request.getSeat_min());
            }

            stmt.setInt(index++, request.getSize());
            stmt.setInt(index++, (request.getPage() - 1) * request.getSize());

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                TripEntity e = new TripEntity();
                e.setTrip_id(rs.getInt("trip_id"));
                e.setStart_location(rs.getString("start_location"));
                e.setEnd_location(rs.getString("end_location"));
                e.setStart_time(rs.getTimestamp("start_time").toLocalDateTime());
                e.setPrice(rs.getLong("price"));
                e.setStatus(rs.getString("status"));
                e.setCoach_type(rs.getString("coach_type"));
                e.setCoach_id(rs.getInt("coach_id"));
                e.setTotal_seat(rs.getInt("total_seat"));
                result.add(e);
            }

        } catch (Exception e) {
            throw new RuntimeException("Error query trips", e);
        }

        return result;
    }

    @Override
    public TripEntity findById(Integer tripId) {
        String sql =
            "SELECT t.trip_id, t.start_location, t.end_location, t.start_time, " +
            "t.price, t.status, c.coach_type, c.coach_id, c.total_seat " +
            "FROM trips t " +
            "JOIN coachs c ON t.coach_id = c.coach_id " +
            "WHERE t.trip_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, tripId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                TripEntity e = new TripEntity();
                e.setTrip_id(rs.getInt("trip_id"));
                e.setStart_location(rs.getString("start_location"));
                e.setEnd_location(rs.getString("end_location"));
                e.setStart_time(rs.getTimestamp("start_time").toLocalDateTime());
                e.setPrice(rs.getLong("price"));
                e.setStatus(rs.getString("status"));
                e.setCoach_type(rs.getString("coach_type"));
                e.setCoach_id(rs.getInt("coach_id"));
                e.setTotal_seat(rs.getInt("total_seat"));
                return e;
            }

        } catch (Exception e) {
            throw new RuntimeException("Error query trip detail", e);
        }

        return null;
    }

    @Override
    public List<String> findBookedSeats(Integer tripId) {
        List<String> result = new ArrayList<>();
        String sql = "SELECT seat_label FROM booked_seats WHERE trip_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, tripId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                result.add(rs.getString("seat_label"));
            }

        } catch (Exception e) {
            throw new RuntimeException("Error query booked seats", e);
        }

        return result;
    }
}
