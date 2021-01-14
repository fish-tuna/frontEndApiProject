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
import com.mthree.HurtzCarRentalsBackend.dao.ReservationDao;
import com.mthree.HurtzCarRentalsBackend.dao.VehicleDao;
import com.mthree.HurtzCarRentalsBackend.entity.Category;
import com.mthree.HurtzCarRentalsBackend.entity.Customer;
import com.mthree.HurtzCarRentalsBackend.entity.Make;
import com.mthree.HurtzCarRentalsBackend.entity.Model;
import com.mthree.HurtzCarRentalsBackend.entity.Reservation;
import com.mthree.HurtzCarRentalsBackend.entity.Vehicle;
import java.util.Calendar;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
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
public class VehicleDaoDBTest {
    
    @Autowired
    VehicleDao vehicleDao;
    @Autowired
    ModelDao modelDao;
    @Autowired
    MakeDao makeDao;
    @Autowired
    CategoryDao categoryDao;
    @Autowired
    CustomerDao customerDao;
    @Autowired
    ReservationDao reservationDao;
    
    int myCustomerId;
    
    public VehicleDaoDBTest() {
        
    }
    
    
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        TestUtil.clearAll(categoryDao, makeDao, modelDao, vehicleDao);
        
        
        TestUtil.setupSubarus(categoryDao, makeDao, modelDao);
        Calendar cal = Calendar.getInstance();
//        cal.set(1995, 8, 15);
//        Customer margo = TestUtil.makeCustomerWithName(customerDao, "Margo", "Lastname", cal.getTime(), "9876", 0);
//        myCustomerId = margzo.getCustomerId();
//        TestUtil.makeBearsVehicle(categoryDao, modelDao, vehicleDao, makeDao);
//        TestUtil.makeLuxuryVehicle(categoryDao, modelDao, vehicleDao, makeDao, "Blue", "CHICAGO");
//        TestUtil.makeLuxuryVehicle(categoryDao, modelDao, vehicleDao, makeDao, "Black", "NYC");
    }
    
    
    
    @Test
    public void testAddVehicle() {
        
        Vehicle v = TestUtil.makeBearsVehicle(categoryDao, modelDao, vehicleDao, makeDao);
        
        Vehicle v1 = vehicleDao.getVehicleByLicensePlate("GO-BEARS");
        assertEquals(v, v1);
        Vehicle v2 = TestUtil.makeMyVehicle(categoryDao, modelDao, vehicleDao, makeDao);
        assertNotEquals(v1, v2);
    }
    
    @Test
    public void testUpdateVehicle() {
        
        Vehicle bears = TestUtil.makeBearsVehicle(categoryDao, modelDao, vehicleDao, makeDao);
        Vehicle mine = TestUtil.makeMyVehicle(categoryDao, modelDao, vehicleDao, makeDao);
        Vehicle oldMine = vehicleDao.getVehicleByLicensePlate(mine.getLicensePlate());
        mine.setColor("black");
        vehicleDao.updateVehicle(mine);
        assertNotEquals(oldMine, mine);
        vehicleDao.updateVehicle(mine);
        Vehicle newMine = vehicleDao.getVehicleByLicensePlate(mine.getLicensePlate());
        assertEquals(mine, newMine);
        assertNotEquals(newMine, oldMine);
    }
    
    @Test
    public void testDeleteVehicleByLicensePlate() {
        Vehicle bears = TestUtil.makeBearsVehicle(categoryDao, modelDao, vehicleDao, makeDao);
        Vehicle mine = TestUtil.makeMyVehicle(categoryDao, modelDao, vehicleDao, makeDao);
        vehicleDao.deleteVehicleByLicensePlate("BENNETT");
        Vehicle nothing = vehicleDao.getVehicleByLicensePlate("BENNETT");
        assertEquals(nothing, null);
        Vehicle something = vehicleDao.getVehicleByLicensePlate("GO-BEARS");
        assertEquals(something, bears);
    }
    
    @Test
    public void testGetAvailableVehiclesBetween() {
        Calendar startDate = Calendar.getInstance();
        startDate.set(2021,1,15);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2021,1,20);
        System.out.println("myCustomerId: " + myCustomerId);
        Calendar cal = Calendar.getInstance();
        cal.set(1996, 8, 14);
        Customer me = TestUtil.makeCustomerWithName(customerDao, "Bennett", "Foley", cal.getTime(), "1234", 0);
        TestUtil.makeMyVehicle(categoryDao, modelDao, vehicleDao, makeDao);
        Reservation reservation = reservationDao.addReservation(new Reservation(0, 
                                                                    me.getCustomerId(), 
                                                                    "BENNETT", 
                                                                    startDate.getTime(), 
                                                                    endDate.getTime(), 1.0, 3.0, 0.0));
        List<Vehicle> availVehicles = vehicleDao.getAllAvailableVehiclesBetween(startDate.getTime(), endDate.getTime());
        boolean found = false;
        for(Vehicle v : availVehicles) {
            if (v.getLicensePlate().equals("BENNETT")) {
                found = true;
            }
            System.out.println(v.toString());
        }
        assertFalse(found);
    }
    
    @After
    public void tearDown() {
    }
}
