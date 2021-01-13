/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.HurtzCarRentalsBackend.dao;

import com.mthree.HurtzCarRentalsBackend.entity.Customer;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Bennett Foley bennett.c.foley@gmail.com
 */
@Repository
public interface CustomerDao {
    public Customer getCustomerById(int customerId);
    public List<Customer> getAllCustomers();
    public Customer addCustomer(Customer c);
    public void updateCustomer(Customer c);
    public void deleteCustomerById(int customerId);  
}
