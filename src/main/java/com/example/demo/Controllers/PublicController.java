package com.example.demo.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Dto.BookingRequestDto;
import com.example.demo.Entities.Booking;
import com.example.demo.Entities.Hotel;
import com.example.demo.Services.BookingService;
import com.example.demo.Services.HotelService;

@RestController
@RequestMapping("/api/v1")
public class PublicController {

    @Autowired
    private HotelService hotelService;

    @Autowired
    private BookingService bookingService;

    @GetMapping("/hotels")
    public ResponseEntity<List<Hotel>> getAllHotels() {
        List<Hotel> hotels = hotelService.getAllHotels();
        return new ResponseEntity<>(hotels,HttpStatus.OK);
    }

    @GetMapping("hotels/{id}")
    public ResponseEntity<Hotel> getHotel(@PathVariable("id") Long id) {
        Hotel hotel = hotelService.getHotel(id);
        return new ResponseEntity<>(hotel,HttpStatus.OK);
    }

    @PostMapping("/booking")
    @ResponseStatus(HttpStatus.CREATED)
    public Booking createBooking(@RequestBody BookingRequestDto bookingRequestDto) {
        return bookingService.createBooking(bookingRequestDto);
    }


}
