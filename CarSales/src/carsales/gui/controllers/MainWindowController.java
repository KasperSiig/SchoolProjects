/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carsales.gui.controllers;

import carsales.be.Car;
import carsales.gui.models.CarModel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

/**
 *
 * @author Kasper Siig
 */
public class MainWindowController implements Initializable {
    
    private Label label;
    @FXML
    private ListView<Car> lstCars;
    @FXML
    private Button loadBtn;
    
    private CarModel carModel;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        carModel = new CarModel();
        lstCars.setItems(carModel.getCars());
    }    

    @FXML
    private void HandleLoadBtn(ActionEvent event) {
        carModel.loadCars();
        
    }
    
}
