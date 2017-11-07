/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inshape.bll;

import inshape.BE.Shape;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author kaspe
 */
public class DrawLogic {
    
    private static DrawLogic instance;
    
    private List<Shape> shapes;
    
    public static DrawLogic getInstance() {
        if (instance == null) {
            instance = new DrawLogic();
        }
        return instance;
    }
    
    
    

    private DrawLogic() {
        this.shapes = new ArrayList();
    }
    
    public void drawShapes(GraphicsContext gc) {
        
        gc.setFill(Color.GREEN);
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(5);
        for (int i = 0; i < shapes.size(); i++) {
            drawShape(gc, i);
        }
    }
    
    public void addShape(Shape shape) {
        shapes.add(shape);
    }

    public List<Shape> getShapes() {
        return shapes;
    }
    
    private void drawShape(GraphicsContext gc, int id) {
        Shape shape = shapes.get(id);
        String shapeText = shape.getShape();
        int size = shape.getSize();
        int posX = (int) Math.ceil(Math.random() * 200);
        int posY = (int) Math.ceil(Math.random() * 300);
        switch(shapeText) {
            case "Circle":
                gc.fillOval(posX, posY, size, size);
                break;
            case "Rectangle":
                gc.fillRect(posX, posY, size, Math.abs(size / 3 * 2));
                break;
        }
        
    }
}
