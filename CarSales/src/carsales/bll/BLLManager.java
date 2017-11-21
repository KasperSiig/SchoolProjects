/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carsales.bll;

import carsales.be.Car;
import carsales.dal.CarDAO;
import java.util.List;

/**
 *
 * @author Kasper Siig
 */
public class BLLManager {
    
    // Start Singleton
    private static BLLManager instance;
    
    public static BLLManager getInstance() {
        if (instance == null) {
            instance = new BLLManager();
        }
        return instance;
    }
    
    // End Singleton
    
    private CarDAO carDAO;
    
    private BLLManager() {
        carDAO = new CarDAO();
    }
    
    public List<Car> getAllCars() {
        return carDAO.getAllCars();
    }
}
