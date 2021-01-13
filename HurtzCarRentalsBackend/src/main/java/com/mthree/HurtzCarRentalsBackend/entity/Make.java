/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.HurtzCarRentalsBackend.entity;

/**
 *
 * @author Bennett Foley bennett.c.foley@gmail.com
 */
public class Make {
    int makeId; 
    String makeName;

    public Make(int makeId, String makeName) {
        this.makeId = makeId;
        this.makeName = makeName;
    }

    @Override
    public String toString() {
        return "Make{" + "makeId=" + makeId + ", makeName=" + makeName + '}';
    }

    public int getMakeId() {
        return makeId;
    }

    public String getMakeName() {
        return makeName;
    }

    public void setMakeId(int makeId) {
        this.makeId = makeId;
    }

    public void setMakeName(String makeName) {
        this.makeName = makeName;
    }
    
    
}
