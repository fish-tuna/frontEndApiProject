/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.HurtzCarRentalsBackend.dao;

import com.mthree.HurtzCarRentalsBackend.entity.Reservation;
import java.util.List;

/**
 *
 * @author Bennett Foley bennett.c.foley@gmail.com
 */
public interface ReservationDao {
    public Reservation getReservationById(int reservationId);
    public List<Reservation> getAllReservations();
    public Reservation addReservation(Reservation r);
    public void updateReservation(Reservation r);
    public void deleteReservationById(int reservationId);
}
