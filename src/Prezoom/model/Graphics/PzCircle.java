package Prezoom.model.Graphics;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

//PzCircle avoid hazards with Circle(in java.fx lib.)
public class PzCircle extends Graphics{
    public PzCircle() {
        Circle cir = new Circle(20,20,20);
//        Circle circle = new Circle(20,20,20);
//        this.cir = circle;
    }

}
