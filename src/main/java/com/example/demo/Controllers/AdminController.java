package com.example.demo.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Dto.HotelRequest;
import com.example.demo.Entities.Hotel;
import com.example.demo.Services.HotelService;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    private final HotelService hotelService;

    public AdminController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    
    @PostMapping("/hotels")
    public ResponseEntity<Hotel> createHotel(@RequestBody HotelRequest request) {
        Hotel hotel = hotelService.addNewHotel(request);
        return new ResponseEntity<>(hotel,HttpStatus.CREATED);
    }

    @DeleteMapping("/hotels/{id}")
    public ResponseEntity<HttpStatus> deleteHotel(@PathVariable("id") Long id) {
        hotelService.deleteHotel(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/hotels/{id}")
    public ResponseEntity<Hotel> getHotel(@PathVariable("id") Long id) {
        Hotel hotel = hotelService.getHotel(id);
        return new ResponseEntity<>(hotel,HttpStatus.OK);
    }
}
