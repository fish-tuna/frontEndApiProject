/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.HurtzCarRentalsBackend.controllers;

import com.mthree.HurtzCarRentalsBackend.dao.CustomerDao;
import com.mthree.HurtzCarRentalsBackend.entity.Customer;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author kmill
 */
@RestController
public class CustomerController {

    @Autowired
    CustomerDao customerDao;
    
    
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

    //ADD CUSTOMER
    @PostMapping("/customers")
    Customer addCustomer(@RequestBody Customer newCustomer) {
        return customerDao.addCustomer(newCustomer);
    }
    
    //UPDATE CUSTOMER
    @PutMapping("/customers/{customerId}")
    Customer updateCustomer(@RequestBody Customer newCustomer, @PathVariable int customerId) {
 
        return customerDao.getCustomerById(customerId).map(customer -> {
            customer.setFirstName(newCustomer.getFirstName());
            customer.setLastName(newCustomer.getLastName());
            customer.setDob(newCustomer.getDob());
            customer.setLicenseNumber(newCustomer.getLicenseNumber());
            customer.setLoyaltyPts(newCustomer.getLoyaltyPts());
            return customerDao.updateCustomer(customer);

        });
    }

    //DELETE CUSTOMER
    @DeleteMapping("/customers/{customerId}")
    public void deleteCustomer(@PathVariable int customerId) {
        customerDao.deleteCustomerById(customerId);
    }
  
}
