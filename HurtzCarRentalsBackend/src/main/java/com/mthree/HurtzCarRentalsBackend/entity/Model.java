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

    public Model(int modelId, String modelName) {
        this.modelId = modelId;
        this.modelName = modelName;
    }

    public int getModelId() {
        return modelId;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelId(int modelId) {
        this.modelId = modelId;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.modelId;
        hash = 97 * hash + Objects.hashCode(this.modelName);
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
        if (!Objects.equals(this.modelName, other.modelName)) {
            return false;
        }
        return true;
    }
    
}
