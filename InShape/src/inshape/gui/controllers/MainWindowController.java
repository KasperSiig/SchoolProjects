/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inshape.gui.controllers;

import inshape.BE.Shape;
import inshape.gui.models.MainWindowModel;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

/**
 *
 * @author kaspe
 */
public class MainWindowController implements Initializable {

    private Label label;
    @FXML
    private ComboBox<String> cBox;
    @FXML
    private TextField tfSize;
    @FXML
    private AnchorPane sPaneFigures;
    @FXML
    private Canvas canvas;

    @FXML
    private Button btnAdd;

    private GraphicsContext gc;

    private MainWindowModel mainModel;
    @FXML
    private VBox vBoxShapes;
    @FXML
    private Button btnDraw;

    public MainWindowController() {
        
        mainModel = new MainWindowModel();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.gc = canvas.getGraphicsContext2D();
        addOptions();
        
    }

    @FXML
    private void addShapes(ActionEvent event) {
        
        String shape = cBox.getValue().toString();
        int size = Integer.parseInt(tfSize.getText());
        mainModel.addShape(new Shape(shape, size));
        showShapes();

    }

    private void addOptions() {
        ObservableList<String> options
                = FXCollections.observableArrayList(
                        "Circle",
                        "Rectangle",
                        "Triangle"
                );
        cBox.getItems().addAll(options);
        cBox.getSelectionModel().selectFirst();
    }
    
    private void showShapes() {
        vBoxShapes.getChildren().clear();
        List<Shape> shapes = mainModel.getShapes();
        for (Shape shape : shapes) {
            Label lbl = new Label();
            lbl.setText(shape.getShape() + " " + shape.getSize());
            vBoxShapes.getChildren().add(lbl);
        }
    }

    @FXML
    private void drawShapes(ActionEvent event) {
        mainModel.drawShapes(gc);
    }

}
