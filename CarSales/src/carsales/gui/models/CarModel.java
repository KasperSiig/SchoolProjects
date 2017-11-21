/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carsales.gui.models;

import carsales.be.Car;
import carsales.bll.BLLManager;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Kasper Siig
 */
public class CarModel {
    private BLLManager bll;
    
    public CarModel(){
        bll = BLLManager.getInstance();
    }
    
    private ObservableList<Car> cars 
            = FXCollections.observableArrayList();
    
    public ObservableList<Car> getCars() {
        return cars;
    }
    
    public void loadCars() {
        List<Car> loadedCars = bll.getAllCars();
        
        cars.clear();
        cars.addAll(loadedCars);

    }
    
    
}
