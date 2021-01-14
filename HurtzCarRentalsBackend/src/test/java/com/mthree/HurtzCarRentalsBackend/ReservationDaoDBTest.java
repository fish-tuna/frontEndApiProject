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
import java.util.Date;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
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
        
        TestUtil.clearAll(categoryDao, makeDao, modelDao, vehicleDao);
        TestUtil.setupSubarus(categoryDao, makeDao, modelDao);
        TestUtil.setupLexus(categoryDao, makeDao, modelDao);
        TestUtil.makeMyVehicle(categoryDao, modelDao, vehicleDao, makeDao);
        TestUtil.makeBearsVehicle(categoryDao, modelDao, vehicleDao, makeDao);
        TestUtil.makeLuxuryVehicle(categoryDao, modelDao, vehicleDao, makeDao, "Blue", "VANITY");
        TestUtil.makeLuxuryVehicle(categoryDao, modelDao, vehicleDao, makeDao, "Red", "PLATE");
    }
    
    /*
    int reservationId, int customerId, String licensePlate, Date startDate, Date endDate, double beforeTax, double tax, double discount, double totalPrice) {
    */
    
    @Test
    public void testAddReservation() {
        Calendar startDate = Calendar.getInstance();
        startDate.set(2021,1,15);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2021,1,20);
        Calendar myBday = Calendar.getInstance();
        myBday.set(1996, 8, 14);
        Customer me = TestUtil.makeCustomerWithName(customerDao, "Bennett", "Foley", myBday.getTime(), "1234", 0);
        Reservation reservation = reservationDao.addReservation(new Reservation(0, 
                                                                    me.getCustomerId(), 
                                                                    "VANITY", 
                                                                    startDate.getTime(), 
                                                                    endDate.getTime(), 1.0, 3.0, 0.0));
        
        Reservation fromDao = reservationDao.getReservationById(reservation.getReservationId());
        System.out.println(reservation.toString());
        System.out.println(fromDao.toString());
        assertEquals(reservation, fromDao);
    }
    
    @Test
    public void testGetAvailableVehicles() {
        Calendar startDate = Calendar.getInstance();
        startDate.set(2021,1,15);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2021,1,20);
        Calendar myBday = Calendar.getInstance();
        myBday.set(1996, 8, 14);
        Customer me = TestUtil.makeCustomerWithName(customerDao, "Bennett", "Foley", myBday.getTime(), "1234", 0);
        Reservation reservation = reservationDao.addReservation(new Reservation(0, 
                                                                    me.getCustomerId(), 
                                                                    "VANITY", 
                                                                    startDate.getTime(), 
                                                                    endDate.getTime(), 1.0, 3.0, 0.0));
        List<Vehicle> vehicles = vehicleDao.getAllAvailableVehiclesBetween(startDate.getTime(), endDate.getTime());
        boolean found = false;
        for (Vehicle v : vehicles) {
            System.out.println("KEYWORD:" + v.toString());
            if (v.getLicensePlate().equals("VANITY")) {
                found = true;
            }
        }
        assertFalse(found);
        
    }
    
    @Test
    public void testDeleteReservation() {
        Calendar startDate = Calendar.getInstance();
        startDate.set(2021,2,1);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2021,2,6);
        Calendar myBday = Calendar.getInstance();
        myBday.set(1996, 8, 14);

        Customer me = TestUtil.makeCustomerWithName(customerDao, "Bennett", "Foley", myBday.getTime(), "1234", 0);
        Reservation reservation = reservationDao.addReservation(new Reservation(0, 
                                                                    me.getCustomerId(), 
                                                                    "VANITY", 
                                                                    startDate.getTime(), 
                                                                    endDate.getTime(), 1.0, 3.0, 0.0));
      
        Reservation fromDao = reservationDao.getReservationById(reservation.getReservationId());
        assertEquals(reservation, fromDao);
        
        reservationDao.deleteReservationById(reservation.getReservationId());
        
        fromDao = reservationDao.getReservationById(reservation.getReservationId());
        assertNull(fromDao);
            
    }
    
    @Test
    public void testUpdateReservation() {
        Calendar startDate = Calendar.getInstance();
        startDate.set(2021,2,1);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2021,2,6);
        Calendar myBday = Calendar.getInstance();
        myBday.set(1996, 8, 14);
        
        Customer me = TestUtil.makeCustomerWithName(customerDao, "Bennett", "Foley", myBday.getTime(), "1234", 0);
        Reservation reservation = reservationDao.addReservation(new Reservation(0, 
                                                                    me.getCustomerId(), 
                                                                    "VANITY", 
                                                                    startDate.getTime(), 
                                                                    endDate.getTime(), 1.0, 3.0, 0.0));
        
        Reservation fromDao = reservationDao.getReservationById(reservation.getReservationId());
        assertEquals(reservation, fromDao);
        
        reservation.setDiscount(-50.00);
        reservationDao.updateReservation(reservation);
        
        fromDao = reservationDao.getReservationById(reservation.getReservationId());
        assertNotEquals(reservation, fromDao);
            
    }
    
}
