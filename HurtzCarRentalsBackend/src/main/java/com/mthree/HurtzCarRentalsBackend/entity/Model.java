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
public class Model {
    int modelId;
    String modelName;
    int makeId;

    public Model(int modelId, String modelName, int makeId) {
        this.modelId = modelId;
        this.modelName = modelName;
        this.makeId = makeId;
    }

    public int getModelId() {
        return modelId;
    }

    public String getModelName() {
        return modelName;
    }
    
    public int getMakeId() {
        return makeId;
    }

    public void setModelId(int modelId) {
        this.modelId = modelId;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }
    
    public void setMakeId(int makeId) {
        this.makeId = makeId;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.modelId;
        hash = 97 * hash + Objects.hashCode(this.modelName);
        hash = 97 * hash + this.makeId;
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
        final Model other = (Model) obj;
        if (this.modelId != other.modelId) {
            return false;
        }
        if (this.makeId != other.makeId) {
            return false;
        }
        if (!Objects.equals(this.modelName, other.modelName)) {
            return false;
        }
        return true;
    }
   
}
