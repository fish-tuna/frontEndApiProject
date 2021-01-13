/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.HurtzCarRentalsBackend.controllers;

import com.mthree.HurtzCarRentalsBackend.dao.CategoryDao;
import com.mthree.HurtzCarRentalsBackend.entity.Category;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author kmill
 */
@RestController
@RequestMapping("/")
public class CategoryController {

    @Autowired
    CategoryDao categoryDao;
    
    
    //GET ALL CATEGORYS
    @GetMapping(value = "/categories")
    public List<Category> getAllCategorys() {
        return categoryDao.getAllCategories();
    }
    
    //GET CATEGORY BY ID  
    @GetMapping("/categories/{categoryId}")
    Category getCategoryById(@PathVariable int categoryId) {
        return categoryDao.getCategoryById(categoryId);
    }

    //ADD CATEGORY
    @PostMapping("/categories")
    Category addCategory(@RequestBody Category newCategory) {
        return categoryDao.addCategory(newCategory);
    }
    
    //UPDATE CATEGORY
    @PutMapping("/categories/{categoryId}")
    Category updateCategory(@RequestBody Category newCategory, @PathVariable int categoryId) {
        Category old = categoryDao.getCategoryById(categoryId);
        old.setCategoryName(newCategory.getCategoryName()); 
        old.setCategoryPrice(newCategory.getCategoryPrice()); 
        categoryDao.updateCategory(old);
        return old;
    }

    //DELETE CATEGORY
    @DeleteMapping("/categories/{categoryId}")
    public void deleteCategory(@PathVariable int categoryId) {
        categoryDao.deleteCategoryById(categoryId);
    }
}
