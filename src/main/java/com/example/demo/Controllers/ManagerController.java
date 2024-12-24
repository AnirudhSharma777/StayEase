package com.example.demo.Controllers;

import java.util.List;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Dto.HotelRequest;
import com.example.demo.Dto.RoomRequestDto;
import com.example.demo.Entities.Booking;
import com.example.demo.Entities.Hotel;
import com.example.demo.Entities.Room;
import com.example.demo.Services.BookingService;
import com.example.demo.Services.HotelService;
import com.example.demo.Services.RoomService;

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

    @Autowired
    private RoomService roomService;

    @Autowired
    private BookingService bookingService;
        
    @PostMapping("/rooms")
    public  ResponseEntity<Room> createRoom(@RequestBody RoomRequestDto roomRequestDto) {
        Room room = roomService.createRoom(roomRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(room);
    }

    @PutMapping("rooms/{id}")
    public ResponseEntity<Room> updateRoom(@PathVariable("id") Long id, @RequestBody RoomRequestDto roomRequestDto) {
        Room room = roomService.updateRoom(id, roomRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(room);
    }

    @GetMapping("/rooms")
    public ResponseEntity<List<Room>>getAllRoomDetails() {
        return ResponseEntity.ok(roomService.getAllRoomDetails());
    }

    
    @GetMapping("rooms/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(roomService.getRoomById(id));
    }
    
    @PutMapping("/hotels/{id}")
    public ResponseEntity<Hotel> updateHotel(@PathVariable("id") Long id, @RequestBody HotelRequest request) {
        Hotel hotel = hotelService.updateHotel(id, request);
        return new ResponseEntity<>(hotel,HttpStatus.OK);
    }

    @GetMapping("/hotels/{id}")
    public ResponseEntity<Hotel> getHotel(@PathVariable("id") Long id) {
        Hotel hotel = hotelService.getHotel(id);
        return new ResponseEntity<>(hotel,HttpStatus.OK);
    }
    
    @GetMapping("/bookings")
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }
    
    @GetMapping("bookings/{id}")
    public Booking getBookingById(@PathVariable Long id) {
        return bookingService.getBookingById(id);
    }

    @DeleteMapping("bookings/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBookingById(@PathVariable Long id) {
        bookingService.deleteBookingById(id);
    }
}