package com.javaweb.model;

public class TicketHistoryDTO {
	 private String ticketId;
	    private String route;
	    private String createdTime;
	    private String ticketType;
	    private Long price;
	    private String status;
		public String getTicketId() {
			return ticketId;
		}
		public void setTicketId(String ticketId) {
			this.ticketId = ticketId;
		}
		public String getRoute() {
			return route;
		}
		public void setRoute(String route) {
			this.route = route;
		}
		public String getCreatedTime() {
			return createdTime;
		}
		public void setCreatedTime(String createdTime) {
			this.createdTime = createdTime;
		}
		public String getTicketType() {
			return ticketType;
		}
		public void setTicketType(String ticketType) {
			this.ticketType = ticketType;
		}
		public Long getPrice() {
			return price;
		}
		public void setPrice(Long price) {
			this.price = price;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
	    

}
