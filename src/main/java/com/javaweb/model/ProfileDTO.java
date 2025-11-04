package com.javaweb.model;

import java.util.List;

public class ProfileDTO {
    private String accountId;
    private String username;
    private String phone;
    private String address;
    private String note;
    private List<TicketHistoryDTO> tickets;
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public List<TicketHistoryDTO> getTickets() {
		return tickets;
	}
	public void setTickets(List<TicketHistoryDTO> tickets) {
		this.tickets = tickets;
	}
    

}
