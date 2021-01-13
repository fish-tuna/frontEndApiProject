/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.HurtzCarRentalsBackend.controllers;

import com.mthree.HurtzCarRentalsBackend.dao.MakeDao;
import com.mthree.HurtzCarRentalsBackend.entity.Make;
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
public class MakeController {

    @Autowired
    MakeDao makeDao;
    
    
    //GET ALL MAKES
    @GetMapping(value = "/makes")
    public List<Make> getAllMakes() {
        return makeDao.getAllMakes();
    }
    
    //GET MAKE BY ID  
    @GetMapping("/makes/{makeId}")
    Make getMakeById(@PathVariable int makeId) {
        return makeDao.getMakeById(makeId);
    }

    //ADD MAKE
    @PostMapping("/makes")
    Make addMake(@RequestBody Make newMake) {
        return makeDao.addMake(newMake);
    }
    
    //UPDATE MAKE
    @PutMapping("/makes/{makeId}")
    Make updateMake(@RequestBody Make newMake, @PathVariable int makeId) {
        Make old = makeDao.getMakeById(makeId);
        old.setMakeName(newMake.getMakeName());        
        makeDao.updateMake(old);
        return old;
    }

    //DELETE MAKE
    @DeleteMapping("/makes/{makeId}")
    public void deleteMake(@PathVariable int makeId) {
        makeDao.deleteMakeById(makeId);
    }
}