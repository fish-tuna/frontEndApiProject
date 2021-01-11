/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.HurtzCarRentalsBackend.dao;

import com.mthree.HurtzCarRentalsBackend.entity.Vehicle;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Bennett Foley bennett.c.foley@gmail.com
 */
public interface VehicleDao {
    public Vehicle getVehicleByLicensePlate(String vehicleLicensePlate);
    public List<Vehicle> getAllVehicles();
    public List<Vehicle> getAllAvailableVehiclesOnDate(Date date);
    public List<Vehicle> getAllAvailableVehiclesBetween(Date startDate, Date endDate);
    public Vehicle addVehicle(Vehicle v);
    public void updateVehicle(Vehicle v);    
    public void deleteVehicleByLicensePlate(String vehicleLicensePlate);    
}
