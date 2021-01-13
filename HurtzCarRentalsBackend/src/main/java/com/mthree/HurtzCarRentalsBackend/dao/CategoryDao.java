/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.HurtzCarRentalsBackend.dao;

import com.mthree.HurtzCarRentalsBackend.entity.Category;
import java.util.List;

/**
 *
 * @author Bennett Foley <bennett.c.foley@gmail.com>
 */
public interface CategoryDao {
    public Category getCategoryById(int categoryId);
    public Category getCategoryByName(String categoryName);
    public List<Category> getAllCategories();
    public Category addCategory(Category c); 
    public void updateCategory(Category c);
    public void deleteCategoryById(int categoryId);     
}