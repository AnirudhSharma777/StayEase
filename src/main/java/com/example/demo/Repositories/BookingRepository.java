package com.example.demo.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entities.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Long>{

} 
