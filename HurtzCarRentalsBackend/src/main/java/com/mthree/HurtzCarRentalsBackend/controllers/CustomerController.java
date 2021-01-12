/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.HurtzCarRentalsBackend.controllers;

import com.mthree.HurtzCarRentalsBackend.dao.CategoryDao;
import com.mthree.HurtzCarRentalsBackend.dao.CustomerDao;
import com.mthree.HurtzCarRentalsBackend.dao.MakeDao;
import com.mthree.HurtzCarRentalsBackend.dao.ModelDao;
import com.mthree.HurtzCarRentalsBackend.dao.ReservationDao;
import com.mthree.HurtzCarRentalsBackend.dao.VehicleDao;
import com.mthree.HurtzCarRentalsBackend.entity.Customer;
import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author kmill
 */
@RestController
public class CustomerController {
    
    @Autowired
    CategoryDao categoryDao;
    
    @Autowired
    CustomerDao customerDao;
    
    @Autowired
    MakeDao makeDao;
    
    @Autowired
    ModelDao modelDao;
    
    @Autowired
    ReservationDao reservationDao;
    
    @Autowired
    VehicleDao vehicleDao;
    
    //GET ALL CUSTOMERS
    @GetMapping(value = "/customers")
    public List<Customer> getAllCustomers() {
        return customerDao.getAllCustomers();
    }
    
    //GET CUSTOMER BY ID  
    @GetMapping("/customers/{customerId}")
    Customer getCustomerById(@PathVariable int customerId) {
        return customerDao.getCustomerById(customerId);
    }

    @PostMapping("/customers")
    public ResponseEntity<Object> createStudent(@RequestBody Customer customer) {
     //????????

    }

    @DeleteMapping("/customers/{customerId}")
    public void deleteCustomer(@PathVariable int customerId) {
        customerDao.deleteCustomerById(customerId);
    }
    
    
            
            
    
}
