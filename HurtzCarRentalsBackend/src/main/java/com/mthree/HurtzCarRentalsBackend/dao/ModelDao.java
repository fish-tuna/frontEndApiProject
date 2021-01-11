/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.HurtzCarRentalsBackend.dao;

import com.mthree.HurtzCarRentalsBackend.entity.Model;
import java.util.List;

/**
 *
 * @author Bennett Foley <bennett.c.foley@gmail.com>
 */
public interface ModelDao {
    public Model getModelById(int modelId);
    public List<Model> getAllModels();
    public Model addModel(Model m); 
    public void updateModel(Model m);
    public void deleteModelById(int modelId);   
   
}

