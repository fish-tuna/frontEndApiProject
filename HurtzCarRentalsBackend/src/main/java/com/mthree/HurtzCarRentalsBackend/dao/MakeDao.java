/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.HurtzCarRentalsBackend.dao;

import com.mthree.HurtzCarRentalsBackend.entity.Make;
import java.util.List;

/**
 *
 * @author Bennett Foley <bennett.c.foley@gmail.com>
 */
public interface MakeDao {
    public Make getMakeById(int makeId);
    public List<Make> getAllMakes();
    public Make addMake(Make m); 
    public void updateMake(Make m);
    public void deleteMakeById(int makeId);    
}
