/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.HurtzCarRentalsBackend.entity;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Bennett Foley bennett.c.foley@gmail.com
 */
public class Customer {
    int customerId;
    String firstName;
    String lastName;
    Date dob;
    String licenseNumber;
    int loyaltyPts;
    
    public Customer() {
        
    }

    @Override
    public String toString() {
        return "Customer{" + "customerId=" + customerId + ", firstName=" + firstName + ", lastName=" + lastName + ", dob=" + dob + ", licenseNumber=" + licenseNumber + ", loyaltyPts=" + loyaltyPts + '}';
    }

    public Customer(int customerId, String firstName, String lastName, Date dob, String licenseNumber, int loyaltyPts) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.licenseNumber = licenseNumber;
        this.loyaltyPts = loyaltyPts;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getDob() {
        return dob;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public int getLoyaltyPts() {
        return loyaltyPts;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public void setLoyaltyPts(int loyaltyPts) {
        this.loyaltyPts = loyaltyPts;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + this.customerId;
        hash = 71 * hash + Objects.hashCode(this.firstName);
        hash = 71 * hash + Objects.hashCode(this.lastName);
        hash = 71 * hash + Objects.hashCode(this.dob);
        hash = 71 * hash + Objects.hashCode(this.licenseNumber);
        hash = 71 * hash + this.loyaltyPts;
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
        final Customer other = (Customer) obj;
        if (this.customerId != other.customerId) {
            return false;
        }
        if (this.loyaltyPts != other.loyaltyPts) {
            return false;
        }
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        if (!Objects.equals(this.licenseNumber, other.licenseNumber)) {
            return false;
        }
        if (!Objects.equals(this.dob, other.dob)) {
            return false;
        }
        return true;
    }
    
    
    
}
