/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.HurtzCarRentalsBackend.dao;

import com.mthree.HurtzCarRentalsBackend.entity.Customer;
import java.util.List;

/**
 *
 * @author Bennett Foley <bennett.c.foley@gmail.com>
 */
public interface CustomerDao {
    public Customer addCustomer(Customer c);
    public boolean deleteCustomer(int customerId);
    public Customer getCustomer(int customerId);
    public List<Customer> getAllCustomers();
}
