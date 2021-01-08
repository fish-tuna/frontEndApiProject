/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.HurtzCarRentalsBackend.entity;

import java.util.Objects;

/**
 *
 * @author Bennett Foley <bennett.c.foley@gmail.com>
 */
public class Vehicle {
    int vehicleId;
    String licensePlate;
    String category;
    String model;
    String make;

    public Vehicle(int vehicleId, String licensePlate, String category, String model, String make) {
        this.vehicleId = vehicleId;
        this.licensePlate = licensePlate;
        this.category = category;
        this.model = model;
        this.make = make;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getCategory() {
        return category;
    }

    public String getModel() {
        return model;
    }

    public String getMake() {
        return make;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setMake(String make) {
        this.make = make;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.vehicleId;
        hash = 67 * hash + Objects.hashCode(this.licensePlate);
        hash = 67 * hash + Objects.hashCode(this.category);
        hash = 67 * hash + Objects.hashCode(this.model);
        hash = 67 * hash + Objects.hashCode(this.make);
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
        if (this.vehicleId != other.vehicleId) {
            return false;
        }
        if (!Objects.equals(this.licensePlate, other.licensePlate)) {
            return false;
        }
        if (!Objects.equals(this.category, other.category)) {
            return false;
        }
        if (!Objects.equals(this.model, other.model)) {
            return false;
        }
        if (!Objects.equals(this.make, other.make)) {
            return false;
        }
        return true;
    }
    
    
    
}
