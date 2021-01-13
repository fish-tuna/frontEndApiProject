/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.HurtzCarRentalsBackend.controllers;

import com.mthree.HurtzCarRentalsBackend.dao.ReservationDao;
import com.mthree.HurtzCarRentalsBackend.entity.Reservation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author kmill
 */
public class ReservationController {
    @Autowired
    ReservationDao reservationDao;
    
    
    //GET ALL RESERVATIONS
    @GetMapping(value = "/reservations")
    public List<Reservation> getAllReservations() {
        return reservationDao.getAllReservations();
    }
    
    //GET RESERVATION BY ID  
    @GetMapping("/reservations/{reservationId}")
    Reservation getReservationById(@PathVariable int reservationId) {
        return reservationDao.getReservationById(reservationId);
    }

    //ADD RESERVATION
    @PostMapping("/reservations")
    Reservation addReservation(@RequestBody Reservation newReservation) {
        return reservationDao.addReservation(newReservation);
    }
    
    //UPDATE RESERVATION
    @PutMapping("/reservations/{reservationId}")
    Reservation updateReservation(@RequestBody Reservation newReservation, @PathVariable int reservationId) {
        Reservation old = reservationDao.getReservationById(reservationId);
        old.setCustomerId(newReservation.getCustomerId());
        old.setLicensePlate(newReservation.getLicensePlate());
        old.setStartDate(newReservation.getStartDate());
        old.setEndDate(newReservation.getEndDate());
        old.setBeforeTax(newReservation.getBeforeTax());
        old.setTax(newReservation.getTax());
        old.setDiscount(newReservation.getDiscount());
        old.setTotalPrice(newReservation.getTotalPrice());
        return old;
    }

    //DELETE RESERVATION
    @DeleteMapping("/reservations/{reservationId}")
    public void deleteReservation(@PathVariable int reservationId) {
        reservationDao.deleteReservationById(reservationId);
    }
  
}    

