package com.example.demo.Services;

import com.example.demo.Entities.Booking;
import com.example.demo.Entities.Room;
import com.example.demo.Entities.User;
import com.example.demo.Repositories.BookingRepository;
import com.example.demo.Repositories.RoomRepository;
import com.example.demo.Repositories.UserRepository;
import com.example.demo.Dto.BookingRequestDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private UserRepository userRepository;

    // Method to create a new booking
    public Booking createBooking(BookingRequestDto bookingRequestDto) {
        // Fetch the room and user from the database
        Room room = roomRepository.findById(bookingRequestDto.getRoomId())
                .orElseThrow(() -> new RuntimeException("Room not found"));
        User user = userRepository.findById(bookingRequestDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Create a new booking
        Booking booking = new Booking();
        booking.setRoom(room);
        booking.setUser(user);
        booking.setBookingDate(bookingRequestDto.getBookingDate());

        // Save the booking to the database
        return bookingRepository.save(booking);
    }

    // Method to get all bookings
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    // Method to get a booking by ID
    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
    }

    // Method to delete a booking by ID
    public void deleteBookingById(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        bookingRepository.delete(booking);
    }
}
