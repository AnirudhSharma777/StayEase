package com.example.demo.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomRequestDto {

    private String name;
    private String description;
    private int totalNumberOfRooms;
    private Long hotelId;  // Hotel ID to associate with the room
}

