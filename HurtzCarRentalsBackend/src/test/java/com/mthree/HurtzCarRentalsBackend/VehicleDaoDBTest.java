/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.HurtzCarRentalsBackend;


import com.mthree.HurtzCarRentalsBackend.dao.CategoryDao;
import com.mthree.HurtzCarRentalsBackend.dao.MakeDao;
import com.mthree.HurtzCarRentalsBackend.dao.ModelDao;
import com.mthree.HurtzCarRentalsBackend.dao.VehicleDao;
import com.mthree.HurtzCarRentalsBackend.entity.Category;
import com.mthree.HurtzCarRentalsBackend.entity.Model;
import com.mthree.HurtzCarRentalsBackend.entity.Vehicle;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
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
    
    public VehicleDaoDBTest() {
        
    }
    
    
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        List<Vehicle> vehicles = vehicleDao.getAllVehicles();
        for (Vehicle v : vehicles) {
            vehicleDao.deleteVehicleByLicensePlate(v.getLicensePlate());
        }
        List<Category> categories = categoryDao.getAllCategories();
        for (Category c : categories) {
            categoryDao.deleteCategoryById(c.getCategoryId());
        }
//        Category sedan = TestUtil.getStandardCategory();
//        categoryDao.addCategory(sedan);
//        categoryDao.addCategory(new Category(2, "Luxury", 125));
    }
    
    @Test
    public void testAddVehicle() {
        Category sedan = TestUtil.getStandardCategory();
        categoryDao.addCategory(sedan);
        categoryDao.addCategory(new Category(2, "Luxury", 125));
        
        Vehicle v = TestUtil.getStandardVehicle();
        
        vehicleDao.addVehicle(v);
        
        Vehicle v1 = vehicleDao.getVehicleByLicensePlate("GO-BEARS");
        assertEquals(v, v1);
    }
    
    @Test
    public void testUpdateVehicle() {
        Vehicle v = TestUtil.getStandardVehicle();
        vehicleDao.addVehicle(v);
        Vehicle v1 = TestUtil.getStandardVehicle();
        v1.setLicensePlate("GO-BUFFALO");
        vehicleDao.addVehicle(v1);
        
        vehicleDao.updateVehicle(new Vehicle("GO-BEARS", 0, 0, "red"));
        
        assertNotEquals(v, vehicleDao.getVehicleByLicensePlate("GO-BEARS"));
        vehicleDao.updateVehicle(new Vehicle("GO-BEARS", 0, 0, "blue"));
        assertEquals(v, vehicleDao.getVehicleByLicensePlate("GO-BEARS"));
        assertNotEquals(vehicleDao.getVehicleByLicensePlate("GO-BEARS"), 
                        vehicleDao.getVehicleByLicensePlate("GO-BUFFALO"));
    }
    
    @Test
    public void testDeleteVehicleByLicensePlate() {
        
        Vehicle vehicle = TestUtil.getStandardVehicle();
        vehicle.setLicensePlate("3L78V9");
        vehicle.setColor("red");
        vehicleDao.addVehicle(vehicle);
                
        vehicle = vehicleDao.addVehicle(vehicle);
        
        Vehicle fromDao = vehicleDao.getVehicleByLicensePlate(vehicle.getLicensePlate());
        assertEquals(vehicle, fromDao);
        
        vehicleDao.deleteVehicleByLicensePlate(vehicle.getLicensePlate());
        
        fromDao = vehicleDao.getVehicleByLicensePlate(vehicle.getLicensePlate());
        assertNull(fromDao);
            
    }
    
    @After
    public void tearDown() {
    }
}
