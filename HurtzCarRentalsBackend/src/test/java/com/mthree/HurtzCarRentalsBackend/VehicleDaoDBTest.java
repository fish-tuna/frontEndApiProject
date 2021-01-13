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
        List<Vehicle> vehicles = vehicleDao.getAllVehicles();
        for (int i = 0; i < vehicles.size(); i++) {
            vehicleDao.deleteVehicleByLicensePlate(vehicles.get(i).getLicensePlate());
        }
        List<Category> categories = categoryDao.getAllCategories();
        for (Category c : categories) {
            categoryDao.deleteCategoryById(c.getCategoryId());
        }
        List<Model> models = modelDao.getAllModels();
        for (Model m : models) {
            modelDao.deleteModelById(m.getModelId());
        }
        List<Make> makes = makeDao.getAllMakes();
        for (Make m : makes) {
            makeDao.deleteMakeById(m.getMakeId());
        }
        
        TestUtil.setupSubarus(categoryDao, makeDao, modelDao);
        
//        Category sedan = TestUtil.getStandardCategory();
//        categoryDao.addCategory(sedan);
//        categoryDao.addCategory(new Category(2, "Luxury", 125));
    }
    
    public Vehicle makeBearsVehicle() {
        Category sedan = categoryDao.getCategoryByName("Sedan");
        Model m = modelDao.getModelByName("Crosstrek");
        return vehicleDao.addVehicle(new Vehicle("GO-BEARS", sedan.getCategoryId(), m.getModelId(), "blue"));
    }
    
    public Vehicle makeMyVehicle() {
        return vehicleDao.addVehicle(
                new Vehicle("BENNETT", 
                            categoryDao.getCategoryByName("Sedan").getCategoryId(), 
                            modelDao.getModelByName("Outback").getModelId(),
                            "red"));
    }
    
    @Test
    public void testAddVehicle() {
        
        
        Category sedan = categoryDao.getCategoryByName("Sedan");
        Model m = modelDao.getModelByName("Crosstrek");
        Vehicle v = makeBearsVehicle();
        
        Vehicle v1 = vehicleDao.getVehicleByLicensePlate("GO-BEARS");
        assertEquals(v, v1);
        Vehicle v2 = makeMyVehicle();
        assertNotEquals(v1, v2);
    }
    
    @Test
    public void testUpdateVehicle() {
        
        Vehicle bears = makeBearsVehicle();
        Vehicle mine = makeMyVehicle();
        Vehicle oldMine = vehicleDao.getVehicleByLicensePlate(mine.getLicensePlate());
        mine.setColor("black");
        vehicleDao.updateVehicle(mine);
        System.out.println("mine: " + mine.toString());
        System.out.println("olde: " + oldMine.toString());
        assertNotEquals(oldMine, mine);
        vehicleDao.updateVehicle(mine);
        Vehicle newMine = vehicleDao.getVehicleByLicensePlate(mine.getLicensePlate());
        assertEquals(mine, newMine);
        assertNotEquals(newMine, oldMine);
    }
    
    @Test
    public void testDeleteVehicleByLicensePlate() {
        Vehicle bears = makeBearsVehicle();
        Vehicle mine = makeMyVehicle();
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
