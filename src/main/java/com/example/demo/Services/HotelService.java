package com.example.demo.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Dto.HotelRequest;
import com.example.demo.Entities.Hotel;
import com.example.demo.Exception.HotelNotFoundException;
import com.example.demo.Exception.UserNotFoundException;
import com.example.demo.Repositories.HotelRepository;

@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;
    

    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    public Hotel addNewHotel(HotelRequest request) {
        var hotel = Hotel.builder()
        .hotelname(request.getHotelname())
        .hoteladdress(request.getAddress())
        .description(request.getDescription())
        .location(request.getLocation())
        .build();

        return hotelRepository.save(hotel);
    }

    public void deleteHotel(Long id) {
        try {
            if (!hotelRepository.existsById(id)) {
                throw new HotelNotFoundException("Hotel with id " + id + " not found.");
            }
            hotelRepository.deleteById(id);
        } catch (Exception e) {
            throw new HotelNotFoundException("Hotel not found");
        }
    }


    public Hotel updateHotel(Long id, HotelRequest hotel){
        Hotel existingHotel = hotelRepository.findById(id).orElse(null);
        if (existingHotel != null) {
            existingHotel.setHotelname(hotel.getHotelname());
            existingHotel.setHoteladdress(hotel.getAddress());
            existingHotel.setDescription(hotel.getDescription());
            existingHotel.setLocation(hotel.getLocation());

        }
        return hotelRepository.save(existingHotel);
    }

    public Hotel getHotel(Long id) {
        Optional<Hotel> hotel = hotelRepository.findById(id);
        if(hotel.isPresent()){
            return hotel.get();
        } else {
            throw new UserNotFoundException("User Not exist By this Id"+id);
        }
    }



}
