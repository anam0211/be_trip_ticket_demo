package com.javaweb.service.impl;

import com.javaweb.model.ProfileDTO;
import com.javaweb.repository.entity.UserEntity;
import com.javaweb.repository.impl.ProfileRepositoryImpl;
import com.javaweb.service.ProfileService;

public class ProfileServiceImpl implements ProfileService {
	private final ProfileRepositoryImpl repo = new ProfileRepositoryImpl();

    @Override
    public ProfileDTO getProfile(String accountId) {
        UserEntity user = repo.getUserById(accountId);
        if (user == null) return null;

        ProfileDTO dto = new ProfileDTO();
        dto.setAccountId(user.getAccountId());
        dto.setUsername(user.getUsername());
        dto.setPhone(user.getPhone());
        dto.setAddress(user.getAddress());
        dto.setNote(user.getNote());
        dto.setTickets(repo.getTicketsByUser(accountId));

        return dto;
    }
}
