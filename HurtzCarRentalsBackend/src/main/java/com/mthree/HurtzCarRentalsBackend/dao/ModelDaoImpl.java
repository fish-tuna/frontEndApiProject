/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.HurtzCarRentalsBackend.dao;

import com.mthree.HurtzCarRentalsBackend.entity.Model;
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
public class ModelDaoImpl implements ModelDao{
    
    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Model getModelById(int modelId) {
        try {
            final String GET_MODEL_BY_ID = "SELECT * FROM Model WHERE modelId = ?";
            return jdbc.queryForObject(GET_MODEL_BY_ID, new ModelMapper(), modelId);
        } catch(DataAccessException ex) {
            return null;
        }
    }
    
    @Override
    public Model getModelByName(String modelName) {
        final String GET_MODEL_BY_NAME = "SELECT * FROM Model WHERE modelName = ?";
        return jdbc.queryForObject(GET_MODEL_BY_NAME, new ModelMapper(), modelName);
    }

    @Override
    public List<Model> getAllModels() {
        final String SELECT_ALL_MODELS = "SELECT * FROM Model";
        return jdbc.query(SELECT_ALL_MODELS, new ModelMapper());
    }

    @Override
    @Transactional
    public Model addModel(Model c) {
        final String INSERT_MODEL = "INSERT INTO Model("
                + "modelName, makeId) VALUES(?,?)";
        jdbc.update(INSERT_MODEL, 
                c.getModelName(),
                c.getMakeId());
        
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        c.setModelId(newId);
        return c;
    }

    @Override
    public void updateModel(Model m) {
       final String UPDATE_MODEL = "UPDATE Model SET modelName = ?, "
                + "modelPrice = ? WHERE modelId = ?";
        jdbc.update(UPDATE_MODEL,
                m.getModelName(),
                m.getMakeId(),
                m.getModelId());
    }

    @Override
    @Transactional
    public void deleteModelById(int modelId) {
        //DELETE RESERVATION GOES HERE
        
        //delete vehicle
        final String DELETE_VEHICLE = "DELETE FROM Vehicle WHERE modelId = ?";
        jdbc.update(DELETE_VEHICLE, modelId);       
        
        final String DELETE_MODEL = "DELETE FROM Model WHERE modelId = ?";
        jdbc.update(DELETE_MODEL, modelId);
    }
    
    public static final class ModelMapper implements RowMapper<Model> {

        @Override
        public Model mapRow(ResultSet rs, int index) throws SQLException {
            Model m = new Model(rs.getInt("modelId"),
                                      rs.getString("modelName"),
                                      rs.getInt("makeId"));
            return m; 
        }
    
}
}
