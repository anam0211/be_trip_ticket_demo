package com.javaweb.service.impl;

import java.sql.Connection;
import java.time.Instant;

import com.javaweb.model.TicketRequest;
import com.javaweb.model.TicketResponse;
import com.javaweb.repository.TicketRepository;
import com.javaweb.repository.TicketSeatRepository;
import com.javaweb.repository.impl.TicketRepositoryImpl;
import com.javaweb.repository.impl.TicketSeatRepositoryImpl;
import com.javaweb.service.TicketService;
import com.javaweb.util.DBUtil;

public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepo = new TicketRepositoryImpl();
    private final TicketSeatRepository ticketSeatRepo = new TicketSeatRepositoryImpl();

    @Override
    public TicketResponse create(TicketRequest req) {

        // Validate outbound
        if (req.getOutbound() == null || 
            req.getOutbound().getOrdered_seat() == null || 
            req.getOutbound().getOrdered_seat().isEmpty()) 
        {
            throw new RuntimeException("Bạn chưa chọn ghế cho lượt đi!");
        }

        // Check ghế lượt đi
        for (String seat : req.getOutbound().getOrdered_seat()) {
            if (ticketSeatRepo.seatBooked(req.getOutbound().getTrip_id(), seat)) {
                throw new RuntimeException("Ghế " + seat + " lượt đi đã được đặt!");
            }
        }

        // Có lượt về?
        boolean hasReturn = 
        	    "return".equals(req.getTicket_type()) && req.getReturnTrip() != null;


        if (hasReturn) {
            if (req.getReturnTrip().getOrdered_seat() == null || req.getReturnTrip().getOrdered_seat().isEmpty()) {
                throw new RuntimeException("Bạn chưa chọn ghế lượt về!");
            }

            for (String seat : req.getReturnTrip().getOrdered_seat()) {
                if (ticketSeatRepo.seatBooked(req.getReturnTrip().getTrip_id(), seat)) {
                    throw new RuntimeException("Ghế " + seat + " lượt về đã được đặt!");
                }
            }
        }

        // Tính tiền
        long total = req.getOutbound().getPrice() * req.getOutbound().getOrdered_seat().size();
        if (hasReturn) {
            total += req.getReturnTrip().getPrice() * req.getReturnTrip().getOrdered_seat().size();
        }

        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false); // ✅ transaction

            // Lưu ticket
            Integer ticketId = ticketRepo.save(req, total);
            if (ticketId == null) throw new RuntimeException("Không tạo được ticket!");

            // Lưu ghế lượt đi
            for (String seat : req.getOutbound().getOrdered_seat()) {
                ticketSeatRepo.save(ticketId, req.getOutbound().getTrip_id(), seat);
            }

            // Lưu ghế lượt về nếu có
            if (hasReturn) {
                for (String seat : req.getReturnTrip().getOrdered_seat()) {
                    ticketSeatRepo.save(ticketId, req.getReturnTrip().getTrip_id(), seat);
                }
            }

            conn.commit();

            // Build response
            TicketResponse res = new TicketResponse();
            res.setTicket_id(ticketId);
            res.setAccount_id(req.getAccount_id());
            res.setName(req.getName());
            res.setPhone(req.getPhone());
            res.setAddress(req.getAddress());
            res.setTicket_type(req.getTicket_type());
            res.setPayment_status("pending");
            res.setCreated_time(Instant.now());
            res.setNote(req.getNote());
            res.setStart_location(req.getStart_location());
            res.setEnd_location(req.getEnd_location());
            res.setTotal_price(total);

            // outbound leg
            TicketResponse.Leg out = new TicketResponse.Leg();
            out.setTrip_id(req.getOutbound().getTrip_id());
            out.setCoach_id(req.getOutbound().getCoach_id());
            out.setStart_time(req.getOutbound().getStart_time());
            out.setCoach_type(req.getOutbound().getCoach_type());
            out.setOrdered_seat(req.getOutbound().getOrdered_seat());
            out.setPrice(req.getOutbound().getPrice());
            res.setOutbound(out);

            // return leg
            if (hasReturn) {
                TicketResponse.Leg ret = new TicketResponse.Leg();
                ret.setTrip_id(req.getReturnTrip().getTrip_id());
                ret.setCoach_id(req.getReturnTrip().getCoach_id());
                ret.setStart_time(req.getReturnTrip().getStart_time());
                ret.setCoach_type(req.getReturnTrip().getCoach_type());
                ret.setOrdered_seat(req.getReturnTrip().getOrdered_seat());
                ret.setPrice(req.getReturnTrip().getPrice());
                res.setReturnTrip(ret);
            }

            return res;

        } catch (Exception e) {
            try {
                if (conn != null) conn.rollback(); // ✅ rollback nếu lỗi
            } catch (Exception ignore) {}
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                if (conn != null) conn.setAutoCommit(true);
            } catch (Exception ignore) {}
        }
    }
}
