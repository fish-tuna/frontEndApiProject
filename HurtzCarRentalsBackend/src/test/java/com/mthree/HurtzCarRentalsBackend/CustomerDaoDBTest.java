/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.HurtzCarRentalsBackend;

import com.mthree.HurtzCarRentalsBackend.entity.Customer;

import com.mthree.HurtzCarRentalsBackend.dao.CustomerDao;
import java.util.Calendar;
import java.util.List;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerDaoDBTest {
    
    @Autowired
    CustomerDao customerDao;
    
    public CustomerDaoDBTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
        List<Customer> customers = customerDao.getAllCustomers();
        for(Customer customer : customers) {
            customerDao.deleteCustomerById(customer.getCustomerId());
        }

    }
    
    @Test
    public void testAddCustomer() {
        
        Customer customer = new Customer();
        customer.setFirstName("Test Customer First");
        customer.setLastName("Test Customer Last");
        
        Calendar cal = Calendar.getInstance();
        cal.set(1985,1,8);
        customer.setDob(cal.getTime());
        
        customer.setLicenseNumber("S-001-2033");
        customer.setLoyaltyPts(0);
                
        customer = customerDao.addCustomer(customer);
        
        Customer fromDao = customerDao.getCustomerById(customer.getCustomerId());
        assertEquals(customer, fromDao);
    }
    
    @Test
    public void testDeleteCustomerById() {
        
        Customer customer = new Customer();
        customer.setFirstName("Test Customer First");
        customer.setLastName("Test Customer Last");
        
        Calendar cal = Calendar.getInstance();
        cal.set(1985,1,8);
        customer.setDob(cal.getTime());
        
        customer.setLicenseNumber("S-001-2033");
        customer.setLoyaltyPts(0);
                
        customer = customerDao.addCustomer(customer);
        
        Customer fromDao = customerDao.getCustomerById(customer.getCustomerId());
        assertEquals(customer, fromDao);
        
        customerDao.deleteCustomerById(customer.getCustomerId());
        
        fromDao = customerDao.getCustomerById(customer.getCustomerId());
        assertNull(fromDao);
        
        
    }
    
}
