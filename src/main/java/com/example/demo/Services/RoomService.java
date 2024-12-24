package com.example.demo.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Dto.RoomRequestDto;
import com.example.demo.Entities.Hotel;
import com.example.demo.Entities.Room;
import com.example.demo.Exception.HotelNotFoundException;
import com.example.demo.Repositories.HotelRepository;
import com.example.demo.Repositories.RoomRepository;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private HotelRepository hotelRepository;

    public Room createRoom(RoomRequestDto roomRequestDto) {
        Hotel hotel = hotelRepository.findById(roomRequestDto.getHotelId())
                .orElseThrow(() -> new HotelNotFoundException("Hotel not found"));

        Room room = Room.builder()
                .name(roomRequestDto.getName())
                .description(roomRequestDto.getDescription())
                .totalNumberOfRooms(roomRequestDto.getTotalNumberOfRooms())
                .hotel(hotel)
                .build();

        return roomRepository.save(room);
    }

    public Room updateRoom(Long id, RoomRequestDto roomRequestDto) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found"));

        Hotel hotel = hotelRepository.findById(roomRequestDto.getHotelId())
                .orElseThrow(() -> new HotelNotFoundException("Hotel not found"));

        room.setName(roomRequestDto.getName());
        room.setDescription(roomRequestDto.getDescription());
        room.setTotalNumberOfRooms(roomRequestDto.getTotalNumberOfRooms());
        room.setHotel(hotel);

        return roomRepository.save(room);
    }

    public List<Room> getAllRoomDetails() {
        return roomRepository.findAll();
    }

    // Method to get room details by ID
    public Room getRoomById(Long id) {
        Optional<Room> room = roomRepository.findById(id);
        return room.orElseThrow(() -> new RuntimeException("Room not found"));
    }

    public void deleteRoomById(Long id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found"));
        roomRepository.delete(room);
    }

}
