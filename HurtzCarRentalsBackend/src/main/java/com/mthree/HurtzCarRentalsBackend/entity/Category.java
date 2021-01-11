/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.HurtzCarRentalsBackend.entity;

import java.util.Objects;

/**
 *
 * @author Bennett Foley bennett.c.foley@gmail.com
 */
public class Category {
    int categoryId;
    String categoryName;
    double categoryPrice;

    public Category(int categoryId, String categoryName, double categoryPrice) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryPrice = categoryPrice;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public double getCategoryPrice() {
        return categoryPrice;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setCategoryPrice(double categoryPrice) {
        this.categoryPrice = categoryPrice;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.categoryId;
        hash = 89 * hash + Objects.hashCode(this.categoryName);
        hash = 89 * hash + (int) (Double.doubleToLongBits(this.categoryPrice) ^ (Double.doubleToLongBits(this.categoryPrice) >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Category other = (Category) obj;
        if (this.categoryId != other.categoryId) {
            return false;
        }
        if (Double.doubleToLongBits(this.categoryPrice) != Double.doubleToLongBits(other.categoryPrice)) {
            return false;
        }
        if (!Objects.equals(this.categoryName, other.categoryName)) {
            return false;
        }
        return true;
    }
    
    
    
    
}
