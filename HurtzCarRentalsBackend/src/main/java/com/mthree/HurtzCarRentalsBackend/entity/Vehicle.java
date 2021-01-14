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
public class Vehicle {
    String licensePlate; //primary key
    int categoryId;
    int modelId; 
    double categoryPrice;
    String categoryName;
    String modelName;
    String makeName;
    String color;
    
    
    
    public Vehicle() {
        
    }

    @Override
    public String toString() {
        return "Vehicle{" + "licensePlate=" + licensePlate + ", categoryId=" + categoryId + ", modelId=" + modelId + ", categoryPrice=" + categoryPrice + ", categoryName=" + categoryName + ", modelName=" + modelName + ", makeName=" + makeName + ", color=" + color + '}';
    }


    
    public Vehicle(String licensePlate, Category category, Model model, Make make, String color) {
        this.licensePlate = licensePlate;
        this.categoryId = category.getCategoryId();
        this.categoryName = category.getCategoryName();
        this.modelId = model.getModelId();
        this.modelName = model.getModelName();
        this.makeName = make.getMakeName();        
        this.color = color;
    }
    
    
    public Vehicle(String licensePlate, int categoryId, int modelId, String color, String modelName, String makeName, String categoryName, double categoryPrice) {
        this.licensePlate = licensePlate;
        this.categoryId = categoryId;
        this.modelId = modelId;
        this.color = color;
        this.modelName = modelName;
        this.makeName = makeName;
        this.categoryName = categoryName;
        this.categoryPrice = categoryPrice;
    }
    
    public double getCategoryPrice() {
        return this.categoryPrice;
    }
    
    public String getCategoryName() {
        return categoryName;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public int getModelId() {
        return modelId;
    }

    public String getColor() {
        return color;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public void setModelId(int modelId, String modelName, String makeName) {
        this.modelName = modelName;
        this.makeName = makeName;
        this.modelId = modelId;
    }
    
    public String getMakeName() {
        return makeName;
    }
    
    public String getModelName() {
        return modelName;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.licensePlate);
        hash = 47 * hash + this.categoryId;
        hash = 47 * hash + this.modelId;
        hash = 47 * hash + Objects.hashCode(this.color);
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
        final Vehicle other = (Vehicle) obj;
        if (this.categoryId != other.categoryId) {
            return false;
        }
        if (this.modelId != other.modelId) {
            return false;
        }
        if (!Objects.equals(this.licensePlate, other.licensePlate)) {
            return false;
        }
        if (!Objects.equals(this.color, other.color)) {
            return false;
        }
        return true;
    }
    
    
}
