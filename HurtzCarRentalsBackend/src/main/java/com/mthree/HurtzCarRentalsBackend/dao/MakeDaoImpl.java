/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.HurtzCarRentalsBackend.dao;

import com.mthree.HurtzCarRentalsBackend.entity.Make;
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
 * @author kmill
 */
@Repository
public class MakeDaoImpl implements MakeDao{
        
    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Make getMakeById(int makeId) {
        try {
            final String GET_MAKE_BY_ID = "SELECT * FROM Make WHERE makeId = ?";
            return jdbc.queryForObject(GET_MAKE_BY_ID, new MakeMapper(), makeId);
        } catch(DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Make> getAllMakes() {
        final String SELECT_ALL_MAKES = "SELECT * FROM Make";
        return jdbc.query(SELECT_ALL_MAKES, new MakeMapper());
    }

    @Override
    @Transactional
    public Make addMake(Make m) {
        final String INSERT_MAKE = "INSERT INTO Make("
                + "makeName VALUES(?)";
        jdbc.update(INSERT_MAKE, 
                m.getMakeName());
        
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        m.setMakeId(newId);
        return m;
    }

    @Override
    public void updateMake(Make m) {
       final String UPDATE_MAKE = "UPDATE Make SET makeName = ? WHERE makeId = ?";
        jdbc.update(UPDATE_MAKE,
                m.getMakeName(),
                m.getMakeId());
    }

    @Override
    @Transactional
    public void deleteMakeById(int makeId) {
        //DELETE RESERVATION GOES HERE        
        
        //DELETE VEHICLE GOES HERE
        
        //delete model
        final String DELETE_MODEL = "DELETE FROM Model WHERE makeId = ?";
        jdbc.update(DELETE_MODEL, makeId);       
        
        final String DELETE_MAKE = "DELETE FROM Make WHERE makeId = ?";
        jdbc.update(DELETE_MAKE, makeId);
    }
    
    public static final class MakeMapper implements RowMapper<Make> {

        @Override
        public Make mapRow(ResultSet rs, int index) throws SQLException {
            Make m = new Make(rs.getInt("makeId"),
                              rs.getString("makeName"));
            return m; 
        }
    
}
}

