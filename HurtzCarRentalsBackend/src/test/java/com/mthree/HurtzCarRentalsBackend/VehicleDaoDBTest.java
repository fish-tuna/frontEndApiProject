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
import com.mthree.HurtzCarRentalsBackend.entity.Make;
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
        
    }
    
    
    
    @Test
    public void testAddVehicle() {
        
        
        Vehicle v = TestUtil.makeBearsVehicle(categoryDao, modelDao, vehicleDao);
        
        Vehicle v1 = vehicleDao.getVehicleByLicensePlate("GO-BEARS");
        assertEquals(v, v1);
        Vehicle v2 = TestUtil.makeMyVehicle(categoryDao, modelDao, vehicleDao);
        assertNotEquals(v1, v2);
    }
    
    @Test
    public void testUpdateVehicle() {
        
        Vehicle bears = TestUtil.makeBearsVehicle(categoryDao, modelDao, vehicleDao);
        Vehicle mine = TestUtil.makeMyVehicle(categoryDao, modelDao, vehicleDao);
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
        Vehicle bears = TestUtil.makeBearsVehicle(categoryDao, modelDao, vehicleDao);
        Vehicle mine = TestUtil.makeMyVehicle(categoryDao, modelDao, vehicleDao);
        vehicleDao.deleteVehicleByLicensePlate("BENNETT");
        Vehicle nothing = vehicleDao.getVehicleByLicensePlate("BENNETT");
        assertEquals(nothing, null);
        Vehicle something = vehicleDao.getVehicleByLicensePlate("GO-BEARS");
        assertEquals(something, bears);
    }
    
    @After
    public void tearDown() {
    }
}
