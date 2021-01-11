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
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Bennett Foley <bennett.c.foley@gmail.com>
 */
public class ReservationDaoImpl implements ReservationDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Reservation getReservationById(int reservationId) {
        try {
            final String GET_RESERVATION_BY_ID = "SELECT * FROM Reservation WHERE reservationId = ?";
            return jdbc.queryForObject(GET_RESERVATION_BY_ID, new ReservationMapper(), reservationId);
        } catch(DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Reservation> getAllReservations() {
        final String SELECT_ALL_RESERVATIONS = "SELECT * FROM Reservation";
        return jdbc.query(SELECT_ALL_RESERVATIONS, new ReservationMapper());
    }

    @Override
    @Transactional
    public Reservation addReservation(Reservation r) {
        final String INSERT_RESERVATION = "INSERT INTO Reservation("
                + "customerId, licensePlate, startDate, endDate,"
                + "beforeTax, tax, discount, totalPrice) VALUES(?,?,?,?,?,?,?,?)";
        jdbc.update(INSERT_RESERVATION, 
                r.getCustomerId(),
                r.getLicensePlate(),
                r.getStartDate(),
                r.getEndDate(),
                r.getBeforeTax(),
                r.getTax(),
                r.getDiscount(),
                r.getTotalPrice());
        
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        r.setReservationId(newId);
        return r;
    }

    @Override
    public void updateReservation(Reservation r) {
        final String UPDATE_RESERVATION = "UPDATE Reservation SET customerId = ?, "
                + "licensePlate = ?, startDate = ?, endDate = ?,"
                + "beforeTax = ?, tax = ?, discount = ?, totalPrice = ? WHERE reservationId = ?";
        jdbc.update(UPDATE_RESERVATION,
                r.getCustomerId(),
                r.getLicensePlate(),
                r.getStartDate(),
                r.getEndDate(),
                r.getBeforeTax(),
                r.getTax(),
                r.getDiscount(),
                r.getTotalPrice(),
                r.getReservationId());
    }

    @Override
    @Transactional
    public void deleteReservationById(int reservationId) {
        final String DELETE_RESERVATION = "DELETE FROM Reservation WHERE reservationId = ?";
        jdbc.update(DELETE_RESERVATION, reservationId);
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
            return r;
        }
    }
}
