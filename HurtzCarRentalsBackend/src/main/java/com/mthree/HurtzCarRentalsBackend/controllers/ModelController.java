/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.HurtzCarRentalsBackend.controllers;

import com.mthree.HurtzCarRentalsBackend.dao.ModelDao;
import com.mthree.HurtzCarRentalsBackend.entity.Model;
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
public class ModelController {

    @Autowired
    ModelDao modelDao;
    
    
    //GET ALL MODELS
    @GetMapping(value = "/models")
    public List<Model> getAllModels() {
        return modelDao.getAllModels();
    }
    
    //GET MODEL BY ID  
    @GetMapping("/models/{modelId}")
    Model getModelById(@PathVariable int modelId) {
        return modelDao.getModelById(modelId);
    }

    //ADD MODEL
    @PostMapping("/models")
    Model addModel(@RequestBody Model newModel) {
        return modelDao.addModel(newModel);
    }
    
    //UPDATE MODEL
    @PutMapping("/models/{modelId}")
    Model updateModel(@RequestBody Model newModel, @PathVariable int modelId) {
        Model old = modelDao.getModelById(modelId);
        old.setModelName(newModel.getModelName()); 
        old.setMakeId(newModel.getMakeId()); 
        modelDao.updateModel(old);
        return old;
    }

    //DELETE MODEL
    @DeleteMapping("/models/{modelId}")
    public void deleteModel(@PathVariable int modelId) {
        modelDao.deleteModelById(modelId);
    }
}
