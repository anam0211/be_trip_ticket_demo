package com.javaweb.model;

public class TripSearchRequest {

    private String start_location;
    private String end_location;

    // ✅ FE gửi start_date & end_date => thay vì chỉ date
    private String start_date;
    private String end_date;

    private String coach_type; 
    private Long price_min;
    private Long price_max;
    private String time_min;    
    private String time_max;    
    private Integer seat_min;   

    private int page = 1;
    private int size = 5;

    public String getStart_location() { return start_location; }
    public void setStart_location(String start_location) { this.start_location = start_location; }

    public String getEnd_location() { return end_location; }
    public void setEnd_location(String end_location) { this.end_location = end_location; }

    public String getStart_date() { return start_date; }
    public void setStart_date(String start_date) { this.start_date = start_date; }

    public String getEnd_date() { return end_date; }
    public void setEnd_date(String end_date) { this.end_date = end_date; }

    public String getCoach_type() { return coach_type; }
    public void setCoach_type(String coach_type) { this.coach_type = coach_type; }

    public Long getPrice_min() { return price_min; }
    public void setPrice_min(Long price_min) { this.price_min = price_min; }

    public Long getPrice_max() { return price_max; }
    public void setPrice_max(Long price_max) { this.price_max = price_max; }

    public String getTime_min() { return time_min; }
    public void setTime_min(String time_min) { this.time_min = time_min; }

    public String getTime_max() { return time_max; }
    public void setTime_max(String time_max) { this.time_max = time_max; }

    public Integer getSeat_min() { return seat_min; }
    public void setSeat_min(Integer seat_min) { this.seat_min = seat_min; }

    public int getPage() { return page; }
    public void setPage(int page) { this.page = page; }

    public int getSize() { return size; }
    public void setSize(int size) { this.size = size; }
}
