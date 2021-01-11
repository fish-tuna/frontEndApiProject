/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.HurtzCarRentalsBackend;

import com.mthree.HurtzCarRentalsBackend.dao.VehicleDao;
import com.mthree.HurtzCarRentalsBackend.entity.Vehicle;
import java.util.List;
import static org.junit.Assert.assertEquals;
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
    
    public VehicleDaoDBTest() {
        
    }
    
    @BeforeEach
    public void setUp() {
        List<Vehicle> vehicles = vehicleDao.getAllVehicles();
        for (Vehicle v : vehicles) {
            vehicleDao.deleteVehicleByLicensePlate(v.getLicensePlate());
        }
    }
    
    @Test
    public void testAddVehicle() {
        
        Vehicle v = new Vehicle();
        v.setLicensePlate("GO-BEARS");
        v.setColor("blue");
        v.setCategoryId(0);
        v.setModelId(0);
        
        vehicleDao.addVehicle(v);
        
        Vehicle v1 = vehicleDao.getVehicleByLicensePlate("GO-BEARS");
        assertEquals(v, v1);
        
        
    }
}
