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
 * @author Bennett Foley <bennett.c.foley@gmail.com>
 */
public class Reservation {
    
    int reservationId;
    int customerId;
    String licensePlate;
    Date startDate;
    Date endDate;
    double beforeTax;
    double tax;
    double discount;
    double totalPrice;    
    
    public Reservation() {
    }

    public Reservation(int reservationId, int customerId, String licensePlate, Date startDate, Date endDate, double beforeTax, double tax, double discount, double totalPrice) {
        this.reservationId = reservationId;
        this.customerId = customerId;
        this.licensePlate = licensePlate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.beforeTax = beforeTax;
        this.tax = tax;
        this.discount = discount;
        this.totalPrice = totalPrice;
    }

       
    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public void setCustomerLicenseNumber(int customerId) {
        this.customerId = customerId;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setBeforeTax(double beforeTax) {
        this.beforeTax = beforeTax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
    
    public int getReservationId() {
        return reservationId;
    }
    
    public int getCustomerId() {
        return customerId;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public double getBeforeTax() {
        return beforeTax;
    }

    public double getTax() {
        return tax;
    }

    public double getDiscount() {
        return discount;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.reservationId;
        hash = 97 * hash + Objects.hashCode(this.customerId);
        hash = 97 * hash + Objects.hashCode(this.licensePlate);
        hash = 97 * hash + Objects.hashCode(this.startDate);
        hash = 97 * hash + Objects.hashCode(this.endDate);
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.beforeTax) ^ (Double.doubleToLongBits(this.beforeTax) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.tax) ^ (Double.doubleToLongBits(this.tax) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.discount) ^ (Double.doubleToLongBits(this.discount) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.totalPrice) ^ (Double.doubleToLongBits(this.totalPrice) >>> 32));
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
        final Reservation other = (Reservation) obj;
        if (this.reservationId != other.reservationId) {
            return false;
        }
        if (Double.doubleToLongBits(this.beforeTax) != Double.doubleToLongBits(other.beforeTax)) {
            return false;
        }
        if (Double.doubleToLongBits(this.tax) != Double.doubleToLongBits(other.tax)) {
            return false;
        }
        if (Double.doubleToLongBits(this.discount) != Double.doubleToLongBits(other.discount)) {
            return false;
        }
        if (Double.doubleToLongBits(this.totalPrice) != Double.doubleToLongBits(other.totalPrice)) {
            return false;
        }
        if (!Objects.equals(this.customerId, other.customerId)) {
            return false;
        }
        if (!Objects.equals(this.licensePlate, other.licensePlate)) {
            return false;
        }
        if (!Objects.equals(this.startDate, other.startDate)) {
            return false;
        }
        if (!Objects.equals(this.endDate, other.endDate)) {
            return false;
        }
        return true;
    }
    
}
