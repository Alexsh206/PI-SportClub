package com.sportclub.dto;

import lombok.Data;

@Data
public class HallRequest {

    private Long eventId;

    private int rows;
    private int seatsInRow;

    private int vipFrom;
    private int vipTo;

    private int standardFrom;
    private int standardTo;

    private int economyFrom;
    private int economyTo;

    private double vipPrice;
    private double standardPrice;
    private double economyPrice;
}