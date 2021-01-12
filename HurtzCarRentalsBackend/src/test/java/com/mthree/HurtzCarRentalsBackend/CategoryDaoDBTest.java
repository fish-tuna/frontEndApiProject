/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.HurtzCarRentalsBackend;

import com.mthree.HurtzCarRentalsBackend.dao.CategoryDao;
import com.mthree.HurtzCarRentalsBackend.entity.Category;
import java.util.List;
import static org.junit.Assert.assertNotEquals;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Bennett Foley bennett.c.foley@gmail.com
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryDaoDBTest {
    @Autowired
    CategoryDao categoryDao;
    
    public CategoryDaoDBTest() {
        
    }
    
    @BeforeEach
    public void setup() {
        List<Category> categories = categoryDao.getAllCategories();
        for (Category c : categories) {
            categoryDao.deleteCategoryById(c.getCategoryId());
        }
    }
    
    @Test
    public void addCategory() {
        Category c = TestUtil.getStandardCategory();
        categoryDao.addCategory(c);
        c = new Category(1, "Luxury", 125);
        categoryDao.addCategory(c);
        Category c1 = categoryDao.getCategoryById(0);
        assertNotEquals(c, c1);
    }
    
}
