/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.HurtzCarRentalsBackend;

import com.mthree.HurtzCarRentalsBackend.dao.CategoryDao;
import com.mthree.HurtzCarRentalsBackend.dao.CustomerDao;
import com.mthree.HurtzCarRentalsBackend.dao.MakeDao;
import com.mthree.HurtzCarRentalsBackend.dao.ModelDao;
import com.mthree.HurtzCarRentalsBackend.dao.VehicleDao;
import com.mthree.HurtzCarRentalsBackend.dao.ReservationDao;
import com.mthree.HurtzCarRentalsBackend.entity.Customer;
import com.mthree.HurtzCarRentalsBackend.entity.Reservation;
import com.mthree.HurtzCarRentalsBackend.entity.Vehicle;
import java.util.Calendar;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Bennett Foley bennett.c.foley@gmail.com
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReservationDaoDBTest {
    
    @Autowired
    ReservationDao reservationDao;
    
    @Autowired
    VehicleDao vehicleDao;
    
    @Autowired
    CustomerDao customerDao;
    
    @Autowired
    CategoryDao categoryDao;
    
    @Autowired
    MakeDao makeDao;
    
    @Autowired
    ModelDao modelDao;
    
    @Before
    public void setUp() {
        TestUtil.clearAll(categoryDao, makeDao, modelDao, vehicleDao, reservationDao, customerDao);
        TestUtil.setupSubarus(categoryDao, makeDao, modelDao);
        TestUtil.makeBearsVehicle(categoryDao, modelDao, vehicleDao, makeDao);
    }
    
    @Test
    public void testAddReservation() {
        Reservation reservation = new Reservation();
        Customer first = TestUtil.makeFirstCustomer(customerDao);
        
        reservation.setBeforeTax(1.00);
        reservation.setCustomerId(first.getCustomerId());
        reservation.setDiscount(0.00);
        reservation.setReservationId(294);
        reservation.setTax(3.00);
        reservation.setLicensePlate("GO-BEARS");
        
        Calendar startDate = Calendar.getInstance();
        startDate.set(2021,2,1);
        reservation.setStartDate(startDate.getTime());
        
        Calendar endDate = Calendar.getInstance();
        endDate.set(2021,2,5);
        reservation.setEndDate(endDate.getTime());
        
        reservationDao.addReservation(reservation);
        
        Reservation fromDao = reservationDao.getReservationById(reservation.getReservationId());
        assertEquals(reservation, fromDao);
    }
    
    @Test
    public void testDeleteReservation() {
        
        Reservation reservation = new Reservation();
        Customer first = TestUtil.makeFirstCustomer(customerDao);
        reservation.setBeforeTax(1.00);
        reservation.setCustomerId(first.getCustomerId());
        reservation.setDiscount(0.00);
        reservation.setReservationId(294);
        reservation.setTax(3.00);
        reservation.setLicensePlate("GO-BEARS");
        
        Calendar startDate = Calendar.getInstance();
        startDate.set(2021,2,1);
        reservation.setStartDate(startDate.getTime());
        
        Calendar endDate = Calendar.getInstance();
        endDate.set(2021,2,5);
        reservation.setEndDate(endDate.getTime());
        
        reservationDao.addReservation(reservation);
        
        Reservation fromDao = reservationDao.getReservationById(reservation.getReservationId());
        assertEquals(reservation, fromDao);
        
        reservationDao.deleteReservationById(reservation.getReservationId());
        
        fromDao = reservationDao.getReservationById(reservation.getReservationId());
        assertNull(fromDao);
            
    }
    
    @Test
    public void testUpdateReservation() {
        
        Reservation reservation = new Reservation();
        Customer first = TestUtil.makeFirstCustomer(customerDao);
        reservation.setBeforeTax(1.00);
        reservation.setCustomerId(first.getCustomerId());
        reservation.setDiscount(0.00);
        reservation.setReservationId(294);
        reservation.setTax(3.00);
        reservation.setLicensePlate("GO-BEARS");
        
        Calendar startDate = Calendar.getInstance();
        startDate.set(2021,2,1);
        reservation.setStartDate(startDate.getTime());
        
        Calendar endDate = Calendar.getInstance();
        endDate.set(2021,2,5);
        reservation.setEndDate(endDate.getTime());
        
        reservationDao.addReservation(reservation);
        
        Reservation fromDao = reservationDao.getReservationById(reservation.getReservationId());
        assertEquals(reservation, fromDao);
        
        reservation.setDiscount(-50.00);
        reservationDao.updateReservation(reservation);
        
        fromDao = reservationDao.getReservationById(reservation.getReservationId());
        assertEquals(reservation, fromDao);
            
    }
    
}
