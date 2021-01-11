/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.HurtzCarRentalsBackend.dao;

import com.mthree.HurtzCarRentalsBackend.entity.Customer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Bennett Foley <bennett.c.foley@gmail.com>
 */
@Repository
public class CustomerDaoImpl implements CustomerDao {
    
    @Autowired
    JdbcTemplate jdbc;
    
    @Override
    public Customer getCustomerById(int customerId) {
        try {
            final String GET_CUSTOMER_BY_ID = "SELECT * FROM Customer WHERE customerId = ?";
            return jdbc.queryForObject(GET_CUSTOMER_BY_ID, new CustomerMapper(), customerId);
        } catch(DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Customer> getAllCustomers() {
        final String SELECT_ALL_CUSTOMERS = "SELECT * FROM Customer";
        return jdbc.query(SELECT_ALL_CUSTOMERS, new CustomerMapper());
    }
    
    @Override
    @Transactional
    public Customer addCustomer(Customer c) {  
        
        //loyalty points are 0 by default
        final String INSERT_CUSTOMER = "INSERT INTO Customer(firstName, "
                + "lastName, dob, licenseNumber, loyaltyPts) VALUES(?,?,?,?,0)";
        jdbc.update(INSERT_CUSTOMER, c.getFirstName(), c.getLastName(), c.getDob(),
                c.getLicenseNumber(), 0);
        
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        c.setCustomerId(newId);
        return c;        
    }
    
    @Override
    public void updateCustomer(Customer c) {
        final String UPDATE_CUSTOMER = "UPDATE Customer SET firstName = ?, lastName = ?, " +
                "dob = ?, licenseNumber = ?, loyaltyPts = ? WHERE id = ?";
        jdbc.update(UPDATE_CUSTOMER,
                c.getFirstName(),
                c.getLastName(),
                c.getDob(),
                c.getLicenseNumber(),
                c.getLoyaltyPts(),                        
                c.getCustomerId());    
    }

    @Override
    @Transactional
    public void deleteCustomerById(int customerId) {
        
        final String DELETE_RESERVATION = "DELETE FROM Reservation WHERE customerId = ?";
        jdbc.update(DELETE_RESERVATION, customerId);
        
        final String DELETE_CUSTOMER = "DELETE FROM Customer WHERE customerId = ?";
        jdbc.update(DELETE_CUSTOMER, customerId);
        
    }    
    
    public static final class CustomerMapper implements RowMapper<Customer> {

        @Override
        public Customer mapRow(ResultSet rs, int index) throws SQLException {
            Customer c = new Customer(rs.getInt("customerId"),
                                      rs.getString("firstName"),
                                      rs.getString("lastName"),
                                      rs.getDate("dateOfBirth"),
                                      rs.getString("licenseNumber"),
                                      rs.getInt("loyaltyPoints"));
            return c; 
        }
    }
    
}
