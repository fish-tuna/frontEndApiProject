/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.HurtzCarRentalsBackend.dao;

import com.mthree.HurtzCarRentalsBackend.entity.Vehicle;
import java.util.List;

/**
 *
 * @author Bennett Foley <bennett.c.foley@gmail.com>
 */
public interface VehicleDao {
    public Vehicle addVehicle();
    public boolean deleteVehicle(int vehicleId);
    public Vehicle getVehicle();
    public List<Vehicle> getAllVehicles();
}
