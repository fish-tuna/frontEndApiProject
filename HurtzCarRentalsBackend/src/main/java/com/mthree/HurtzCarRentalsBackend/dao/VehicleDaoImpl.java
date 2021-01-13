/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.HurtzCarRentalsBackend.dao;

import com.mthree.HurtzCarRentalsBackend.entity.Vehicle;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Bennett Foley bennett.c.foley@gmail.com
 */
@Repository
public class VehicleDaoImpl implements VehicleDao {
    
    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Vehicle getVehicleByLicensePlate(String vehicleLicensePlate) {
        try {
            final String GET_VEHICLE_BY_LICENSE_PLATE = "SELECT * FROM Vehicle WHERE licensePlate = ?";
            return jdbc.queryForObject(GET_VEHICLE_BY_LICENSE_PLATE, new VehicleMapper(), vehicleLicensePlate);
        } catch(DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        final String SELECT_ALL_VEHICLES = "SELECT * FROM Vehicle";
        return jdbc.query(SELECT_ALL_VEHICLES, new VehicleMapper());
    }
    
    @Override
    public List<Vehicle> getAllAvailableVehiclesBetween(Date startDate, Date endDate) {
        /*
        reference: bool overlap = a.start < b.end && b.start < a.end;
        
        sd, ed = start_date, end_date on sql server, respectively.
        (i.e. startDate is the startDate in this function's arguments, whereas sd is the one in the db that we're comparing to)
        
        overlappingReservations = get all reservations that have sd < endDate and startDate < ed
            select * where 
                startDate < ed and
                sd < endDate
        
        overlappingPlates = getLicensePlates(overlappingReservations);

        vehicles vs = get all vehicles
        find all vehicles v not in overlappingPlates
        */
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public List<Vehicle> getAllAvailableVehiclesOnDate(Date date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Vehicle addVehicle(Vehicle v) {
        final String INSERT_VEHICLE = "INSERT INTO Vehicle(licensePlate, categoryId, modelId, color) "
                + "VALUES(?,?,?,?)";
        jdbc.update(INSERT_VEHICLE,
                v.getLicensePlate(),
                v.getCategoryId(),
                v.getModelId(),
                v.getColor());
        return v;
    }

    @Override
    public void updateVehicle(Vehicle v) {
        final String UPDATE_VEHICLE = "UPDATE Vehicle SET categoryId = ?, "
                + "modelId = ?, color = ? WHERE licensePlate = ?";
        jdbc.update(UPDATE_VEHICLE,
                v.getCategoryId(),
                v.getModelId(),
                v.getColor(),
                v.getLicensePlate());
    }
    

    @Override
    @Transactional
    public void deleteVehicleByLicensePlate(String licensePlate) {
        final String DELETE_RESERVATION = "DELETE FROM Reservation WHERE licensePlate = ?";
        jdbc.update(DELETE_RESERVATION, licensePlate);
        
        final String DELETE_VEHICLE = "DELETE FROM Vehicle WHERE licensePlate = ?";
        jdbc.update(DELETE_VEHICLE, licensePlate);
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
