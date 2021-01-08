/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.HurtzCarRentalsBackend.dao;

import com.mthree.HurtzCarRentalsBackend.entity.Vehicle;
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
public class VehicleDaoImpl implements VehicleDao {
    
    @Autowired
    JdbcTemplate jdbc;
    
    @Override
    public Vehicle addVehicle(Vehicle v) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteVehicle(int vehicleId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Vehicle getVehicle(int vehicleId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        final String SELECT_ALL_VEHICLES = "SELECT * FROM Vehicle";
        
        return jdbc.query(SELECT_ALL_VEHICLES, new VehicleMapper());
    }
    
    public static final class VehicleMapper implements RowMapper<Vehicle> {

        @Override
        public Vehicle mapRow(ResultSet rs, int index) throws SQLException {
            Vehicle v = new Vehicle(rs.getString("licensePlate"),
                                    rs.getInt("categoryId"), 
                                    rs.getInt("modelId"),
                                    rs.getString("color"));
            return v;
        }
    }
    
    
}
