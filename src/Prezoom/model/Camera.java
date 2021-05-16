package Prezoom.model;

import javafx.scene.layout.Region;

public class Camera extends Region {
    Region camera = new Region();
    public Camera() {
        this.camera = new Region();
        camera.prefHeight(350);
        camera.prefWidth(350);
        camera.setStyle("-fx-border-color: red");
        camera.setStyle("-fx-border-width: 1");
        camera.setLayoutX(0);
        camera.setLayoutY(0);
        camera.toFront();

    }
}
