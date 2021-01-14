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

/**
 *
 * @author Bennett Foley bennett.c.foley@gmail.com
 */
public class TestUtil {
    
    
    public static void clearAll(CategoryDao categoryDao, MakeDao makeDao, ModelDao modelDao, VehicleDao vehicleDao) {
        
        TestUtil.clearAllVehicles(vehicleDao);
        TestUtil.clearAllCategories(categoryDao);
        TestUtil.clearAllModels(modelDao);
        TestUtil.clearAllMakes(makeDao);
    }
    
    public static void clearAll(CategoryDao categoryDao, MakeDao makeDao, ModelDao modelDao, VehicleDao vehicleDao, ReservationDao reservationDao, CustomerDao customerDao) {
        clearAll(categoryDao, makeDao, modelDao, vehicleDao);
        clearAllReservations(reservationDao);
        clearAllCustomers(customerDao);
    }
    
    public static void setupSubarus(CategoryDao categoryDao, MakeDao makeDao, ModelDao modelDao) {
        Category sedan = categoryDao.addCategory(new Category("Sedan", 75));
        Category luxury = categoryDao.addCategory(new Category("Luxury", 125));
        Make subaru = makeDao.addMake(new Make(0, "Subaru"));
        Model crosstrek = modelDao.addModel(new Model(0, "Crosstrek", subaru.getMakeId()));
        Model outback = modelDao.addModel(new Model(0, "Outback", subaru.getMakeId()));
    }
    
    public static Vehicle makeBearsVehicle(CategoryDao categoryDao, ModelDao modelDao, VehicleDao vehicleDao, MakeDao makeDao) {
        Category sedan = categoryDao.getCategoryByName("Sedan");
        Model model = modelDao.getModelByName("Crosstrek");
        Make make = makeDao.getMakeByName("Subaru");
        return vehicleDao.addVehicle(new Vehicle("GO-BEARS", sedan, model, make, "Blue"));
    }
    
    public static Vehicle makeMyVehicle(CategoryDao categoryDao, ModelDao modelDao, VehicleDao vehicleDao, MakeDao makeDao) {
        return vehicleDao.addVehicle(
                new Vehicle("BENNETT", 
                            categoryDao.getCategoryByName("Sedan"), 
                            modelDao.getModelByName("Outback"),
                            makeDao.getMakeByName("Subaru"),
                            "Red"));
    }
    
    public static Customer makeFirstCustomer(CustomerDao customerDao) {
        Calendar cal = Calendar.getInstance();
        cal.set(1996,8,14);
        return customerDao.addCustomer(new Customer(0, "Bennett", "Foley", cal.getTime(), "1234", 0));
    }
    
    public static void clearAllVehicles(VehicleDao vehicleDao) {
        List<Vehicle> vehicles = vehicleDao.getAllVehicles();
        for (Vehicle v : vehicles) {
            vehicleDao.deleteVehicleByLicensePlate(v.getLicensePlate());
        }
    }
    
    public static void clearAllCategories(CategoryDao categoryDao) {
        List<Category> categories = categoryDao.getAllCategories();
        for (Category c : categories) {
            categoryDao.deleteCategoryById(c.getCategoryId());
        }
    }
    
    public static void clearAllModels(ModelDao modelDao) {
        List<Model> models = modelDao.getAllModels();
        for (Model m : models) {
            modelDao.deleteModelById(m.getModelId());
        }
    }
    
    public static void clearAllMakes(MakeDao makeDao) {
        List<Make> makes = makeDao.getAllMakes();
        for (Make m : makes) {
            makeDao.deleteMakeById(m.getMakeId());
        }
    }
    
    public static void clearAllCustomers(CustomerDao customerDao) {
        List<Customer> customers = customerDao.getAllCustomers();
        for (Customer c : customers) { 
            customerDao.deleteCustomerById(c.getCustomerId());
        }
    }
    
    public static void clearAllReservations(ReservationDao reservationDao) {
        List<Reservation> reservations = reservationDao.getAllReservations();
        for (Reservation r : reservations) {
            reservationDao.deleteReservationById(r.getReservationId());
        }
    }
    
}
