/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.HurtzCarRentalsBackend;

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
}
