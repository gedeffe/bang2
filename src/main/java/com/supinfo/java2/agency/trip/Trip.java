package com.supinfo.java2.agency.trip;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Trip {
    private Long id;
    private Long departureId;
    private Long destinationId;
    private Double price;
}
