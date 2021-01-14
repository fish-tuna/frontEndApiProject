/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.HurtzCarRentalsBackend.controllers;

import com.mthree.HurtzCarRentalsBackend.dao.VehicleDao;
import com.mthree.HurtzCarRentalsBackend.entity.DatePair;
import com.mthree.HurtzCarRentalsBackend.entity.Vehicle;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author kmill
 */
@RestController
@RequestMapping("/")
public class VehicleController {
    
    @Autowired
    VehicleDao vehicleDao;
    
    
    //GET ALL VEHICLES
    @GetMapping(value = "/vehicles")
    public List<Vehicle> getAllVehicles() {
        return vehicleDao.getAllVehicles();
    }
    
    //GET VEHICLE BY ID  
    @GetMapping("/vehicles/{vehicleLicensePlate}")
    Vehicle getVehicleById(@PathVariable String vehicleLicensePlate) {
        return vehicleDao.getVehicleByLicensePlate(vehicleLicensePlate);
    }
    
    //GET VEHICLES BETWEEN DATES INCLUSIVE
    @GetMapping("/vehicles?start={start}?end={end}")
    public List<Vehicle> getAllAvailableVehiclesBetween(@PathVariable Date start, @PathVariable Date end) {
        return vehicleDao.getAllAvailableVehiclesBetween(start, end);
    }
    
    @GetMapping("/vehiclesBetween")
    public List<Vehicle> getAllAvailableVehiclesBetween(@RequestBody DatePair datePair) {
        return vehicleDao.getAllAvailableVehiclesBetween(datePair.getStartDate(), datePair.getEndDate());
    }

    //ADD VEHICLE
    @PostMapping("/vehicles")
    Vehicle addVehicle(@RequestBody Vehicle newVehicle) {
        return vehicleDao.addVehicle(newVehicle);
    }
    
    //UPDATE VEHICLE
    @PutMapping("/vehicles/{vehicleLicensePlate}")
    Vehicle updateVehicle(@RequestBody Vehicle newVehicle, @PathVariable String vehicleLicensePlate) {
        Vehicle old = vehicleDao.getVehicleByLicensePlate(vehicleLicensePlate);
        old.setCategoryId(newVehicle.getCategoryId());
        old.setModelId(newVehicle.getModelId(), newVehicle.getModelName(), newVehicle.getMakeName());
        old.setColor(newVehicle.getColor());
        vehicleDao.updateVehicle(old);
        return old;
    }

    //DELETE VEHICLE
    @DeleteMapping("/vehicles/{vehicleLicensePlate}")
    public void deleteVehicle(@PathVariable String vehicleLicensePlate) {
        vehicleDao.deleteVehicleByLicensePlate(vehicleLicensePlate);
    }
 
}
