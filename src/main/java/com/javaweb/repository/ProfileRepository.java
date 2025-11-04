package com.javaweb.repository;

import java.util.List;

import com.javaweb.model.TicketHistoryDTO;
import com.javaweb.repository.entity.UserEntity;

public interface ProfileRepository {
    UserEntity getUserById(String accountId);
    List<TicketHistoryDTO> getTicketsByUser(String accountId);
}