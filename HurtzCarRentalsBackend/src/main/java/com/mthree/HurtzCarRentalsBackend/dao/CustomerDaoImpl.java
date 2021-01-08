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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Bennett Foley <bennett.c.foley@gmail.com>
 */
@Repository
public class CustomerDaoImpl implements CustomerDao {
    
    @Autowired
    JdbcTemplate jdbc;
    
    @Override
    public Customer addCustomer(Customer c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteCustomer(int customerId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Customer getCustomer(int customerId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Customer> getAllCustomers() {
        final String SELECT_ALL_CUSTOMERS = "SELECT * FROM Customer";
        return jdbc.query(SELECT_ALL_CUSTOMERS, new CustomerMapper());
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
            
            /*
            int customerId;
            String firstName;
            String lastName;
            Date dob;
            String licenseNumber;
            int loyaltyPts;
            
            */
            
            return c; 
        }
    }
    
}
