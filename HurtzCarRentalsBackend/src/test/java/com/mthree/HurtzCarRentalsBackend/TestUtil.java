/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.HurtzCarRentalsBackend;

import com.mthree.HurtzCarRentalsBackend.entity.Category;
import com.mthree.HurtzCarRentalsBackend.entity.Make;
import com.mthree.HurtzCarRentalsBackend.entity.Model;
import com.mthree.HurtzCarRentalsBackend.entity.Vehicle;

/**
 *
 * @author Bennett Foley bennett.c.foley@gmail.com
 */
public class TestUtil {
    public static Vehicle getStandardVehicle() {
        Vehicle v = new Vehicle();
        v.setLicensePlate("GO-BEARS");
        v.setColor("blue");
        v.setCategoryId(0);
        v.setModelId(0);
        return v;
    }
    
    public static Make getStandardMake() {
        return new Make(0, "Chevy");
    }
    
    public static Model getStandardModel() {
        return new Model(0, "Malibu", 0);
    }
    
    public static Category getStandardCategory() {
        return new Category(0, "Sedan", 75);
    }
}
