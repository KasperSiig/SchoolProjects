/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inshape.gui.models;

import inshape.BE.Shape;
import inshape.bll.DrawLogic;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author kaspe
 */
public class MainWindowModel {
    
    private DrawLogic dl;

    public MainWindowModel() {
        this.dl = DrawLogic.getInstance();
    }
    
    public void addShape(Shape shape) {
        dl.addShape(shape);
    }
    
    public List<Shape> getShapes() {
        return dl.getShapes();
    }

    public void drawShapes(GraphicsContext gc) {
        dl.drawShapes(gc);
    }
    
}
