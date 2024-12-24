package com.example.demo.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingRequestDto {

    private Long roomId;  // Room ID to associate with the booking
    private Long userId;  // User ID (customer) who is making the booking
    private LocalDate bookingDate;  // Date of the booking
}