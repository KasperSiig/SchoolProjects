/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inshape.BE;

/**
 *
 * @author kaspe
 */
public class Shape {
    private String shape;
    
    private int size;

    public Shape(String shape, int size) {
        this.shape = shape;
        this.size = size;
    }

    public String getShape() {
        return shape;
    }

    public int getSize() {
        return size;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    public void setSize(int size) {
        this.size = size;
    }
    
    
}
