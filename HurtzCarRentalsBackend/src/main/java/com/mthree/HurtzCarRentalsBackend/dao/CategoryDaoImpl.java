/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.HurtzCarRentalsBackend.dao;

import com.mthree.HurtzCarRentalsBackend.entity.Category;
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
public class CategoryDaoImpl implements CategoryDao {
    
    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Category getCategoryById(int categoryId) {
        try {
            final String GET_CATEGORY_BY_ID = "SELECT * FROM Category WHERE categoryId = ?";
            return jdbc.queryForObject(GET_CATEGORY_BY_ID, new CategoryMapper(), categoryId);
        } catch(DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Category> getAllCategories() {
        final String SELECT_ALL_CATEGORIES = "SELECT * FROM Category";
        return jdbc.query(SELECT_ALL_CATEGORIES, new CategoryMapper());
    }

    @Override
    @Transactional
    public Category addCategory(Category c) {
        final String INSERT_CATEGORY = "INSERT INTO Category("
                + "categoryName, categoryPrice) VALUES(?,?)";
        jdbc.update(INSERT_CATEGORY, 
                c.getCategoryName(),
                c.getCategoryPrice());
        
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        c.setCategoryId(newId);
        return c;
    }

    @Override
    public void updateCategory(Category c) {
       final String UPDATE_CATEGORY = "UPDATE Category SET categoryName = ?, "
                + "categoryPrice = ? WHERE categoryId = ?";
        jdbc.update(UPDATE_CATEGORY,
                c.getCategoryName(),
                c.getCategoryPrice(),
                c.getCategoryId());
    }

    @Override
    @Transactional
    public void deleteCategoryById(int categoryId) {
        //DELETE RESERVATION GOES HERE
        
        //delete vehicle
        final String DELETE_VEHICLE = "DELETE FROM Vehicle WHERE categoryId = ?";
        jdbc.update(DELETE_VEHICLE, categoryId);       
        
        final String DELETE_CATEGORY = "DELETE FROM Category WHERE categoryId = ?";
        jdbc.update(DELETE_CATEGORY, categoryId);
    }
    
    public static final class CategoryMapper implements RowMapper<Category> {

        @Override
        public Category mapRow(ResultSet rs, int index) throws SQLException {
            Category c = new Category(rs.getInt("categoryId"),
                                      rs.getString("categoryName"),
                                      rs.getDouble("categoryPrice"));
            return c; 
        }
    
}
}
