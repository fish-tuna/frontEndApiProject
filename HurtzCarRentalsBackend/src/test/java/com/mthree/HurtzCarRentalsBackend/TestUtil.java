/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.HurtzCarRentalsBackend;

import com.mthree.HurtzCarRentalsBackend.dao.CategoryDao;
import com.mthree.HurtzCarRentalsBackend.dao.MakeDao;
import com.mthree.HurtzCarRentalsBackend.dao.ModelDao;
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
        v.setCategoryId(1);
        v.setModelId(1);
        return v;
    }
    
    public static void setupSubarus(CategoryDao categoryDao, MakeDao makeDao, ModelDao modelDao) {
        Category sedan = categoryDao.addCategory(new Category("Sedan", 75));
        Category luxury = categoryDao.addCategory(new Category("Luxury", 125));
        Make subaru = makeDao.addMake(new Make(0, "Subaru"));
        Model crosstrek = modelDao.addModel(new Model(0, "Crosstrek", subaru.getMakeId()));
        Model outback = modelDao.addModel(new Model(0, "Outback", subaru.getMakeId()));
    }
    
    public static Make getStandardMake() {
        return new Make(1, "Chevy");
    }
    
    public static Model getStandardModel() {
        return new Model(1, "Malibu", 0);
    }
    
    public static Category getStandardCategory() {
        return new Category(1, "Sedan", 75);
    }
}
