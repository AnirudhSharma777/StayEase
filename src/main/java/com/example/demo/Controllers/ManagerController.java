package com.example.demo.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Dto.HotelRequest;
import com.example.demo.Entities.Hotel;
import com.example.demo.Services.HotelService;

@RestController
@RequestMapping("/api/v1/management")
public class ManagerController {


    // @Operation(
    //         description = "Get endpoint for manager",
    //         summary = "This is a summary for management get endpoint",
    //         responses = {
    //                 @ApiResponse(
    //                         description = "Success",
    //                         responseCode = "200"
    //                 ),
    //                 @ApiResponse(
    //                         description = "Unauthorized / Invalid Token",
    //                         responseCode = "403"
    //                 )
    //         }

    // )

    @Autowired
    private HotelService hotelService;

        
    @GetMapping
    public String get() {
        return "GET:: management controller";
    }
    @PostMapping
    public String post() {
        return "POST:: management controller";
    }
    
    @PutMapping("/hotels/{id}")
    public ResponseEntity<Hotel> updateHotel(@PathVariable("id") Long id, @RequestBody HotelRequest request) {
        Hotel hotel = hotelService.updateHotel(id, request);
        return new ResponseEntity<>(hotel,HttpStatus.OK);
    }
    
    @DeleteMapping
    public String delete() {
        return "DELETE:: management controller";
    }
}