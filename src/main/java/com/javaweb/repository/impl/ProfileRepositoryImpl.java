package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.javaweb.model.TicketHistoryDTO;
import com.javaweb.repository.ProfileRepository;
import com.javaweb.repository.entity.UserEntity;
import com.javaweb.util.DBUtil;

public class ProfileRepositoryImpl implements ProfileRepository {
    @Override
    public UserEntity getUserById(String accountId) {
        try (Connection conn = DBUtil.getConnection()) {
            String sql = """
               SELECT account_id, username, phone, address, order_history
               FROM user WHERE account_id = ?
            """;

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, accountId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                UserEntity u = new UserEntity();
                u.setAccountId(rs.getString("account_id"));
                u.setUsername(rs.getString("username"));
                u.setPhone(rs.getString("phone"));
                u.setAddress(rs.getString("address"));
                u.setNote(rs.getString("order_history"));
                return u;
            }
        } catch (Exception e) { e.printStackTrace(); }

        return null;
    }

    @Override
    public List<TicketHistoryDTO> getTicketsByUser(String accountId) {
        List<TicketHistoryDTO> list = new ArrayList<>();

        try (Connection conn = DBUtil.getConnection()) {
            String sql = """
                SELECT t.ticket_id, 
                       CONCAT(s.start_location, ' → ', s.end_location) AS route,
                       t.created_time, 
                       t.ticket_type, 
                       t.payment_status, 
                       t.price
                FROM ticket t
                JOIN schedule s ON t.trip_id = s.trip_id
                WHERE t.account_id = ?
                ORDER BY t.created_time DESC
            """;

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, accountId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                TicketHistoryDTO t = new TicketHistoryDTO();
                t.setTicketId(rs.getString("ticket_id"));
                t.setRoute(rs.getString("route"));
                t.setCreatedTime(rs.getString("created_time"));
                t.setTicketType(rs.getString("ticket_type"));
                t.setStatus(rs.getString("payment_status"));
                t.setPrice(rs.getLong("price"));
                list.add(t);
            }
        } catch (Exception e) { e.printStackTrace(); }

        return list;
    }

}
