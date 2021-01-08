/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.HurtzCarRentalsBackend.dao;

import com.mthree.HurtzCarRentalsBackend.entity.Reservation;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class ReservationDaoImpl implements ReservationDao {


    @Override
    public void deleteReservation(int reservationId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Reservation getReservation(int reservationId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Reservation> getAllReservations() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Reservation addReservation(Reservation r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public static final class ReservationMapper implements RowMapper<Reservation> {

        @Override
        public Reservation mapRow(ResultSet rs, int index) throws SQLException {
            Reservation r = new Reservation(rs.getInt("reservationId"),
                                            rs.getInt("customerId"),
                                            rs.getString("licensePlate"),
                                            rs.getDate("startDate"),
                                            rs.getDate("endDate"),
                                            rs.getDouble("beforeTaxPrice"),
                                            rs.getDouble("tax"),
                                            rs.getDouble("discount"),
                                            rs.getDouble("totalPrice"));
            /*
            int reservationId;
            String customerLicenseNumber;
            String vehicleLicenseNumber;
            Date startDate;
            Date endDate;
            double beforeTax;
            double tax;
            double discount;
            double totalPrice;

            
            */
            return r;
        }
    }
}
