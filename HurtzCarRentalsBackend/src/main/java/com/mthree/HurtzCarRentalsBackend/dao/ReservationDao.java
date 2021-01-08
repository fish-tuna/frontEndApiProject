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
 * @author Bennett Foley <bennett.c.foley@gmail.com>
 */
public interface ReservationDao {
    public Reservation addReservation();
    public void deleteReservation(int reservationId);
    public Reservation getReservation(int reservationId);
    public List<Reservation> getAllReservations();
}
