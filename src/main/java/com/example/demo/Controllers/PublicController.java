package com.example.demo.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entities.Hotel;
import com.example.demo.Services.HotelService;

@RestController
@RequestMapping("/api/v1/hotels")
public class PublicController {

    @Autowired
    private HotelService hotelService;

    @GetMapping()
    public ResponseEntity<List<Hotel>> getAllHotels() {
        List<Hotel> hotels = hotelService.getAllHotels();
        return new ResponseEntity<>(hotels,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hotel> getHotel(@PathVariable("id") Long id) {
        Hotel hotel = hotelService.getHotel(id);
        return new ResponseEntity<>(hotel,HttpStatus.OK);
    }
}
