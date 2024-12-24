package com.example.demo.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotelRequest {
  
    private String hotelname;

    private String address;

    private Double[] location;

    private String description;

    private boolean availability;
}
