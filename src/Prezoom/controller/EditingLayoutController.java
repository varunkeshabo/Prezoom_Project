package Prezoom.controller;

import Prezoom.Main;
import Prezoom.model.Camera;
import Prezoom.model.Graphics.Graphics;
import Prezoom.model.Graphics.PzCircle;
import Prezoom.model.Sheet;
import Prezoom.model.State;
import Prezoom.model.TimeLine;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class EditingLayoutController {


    @FXML
    private TabPane inforPane;

    @FXML
    private Tab editTab;

    @FXML
    private Button submitEditing;

    @FXML
    private Button deleteGraphics;

    @FXML
    private Tab transitionTab;

    @FXML
    private Button submitTransition;

    @FXML
    private ListView<String> stateList;

    @FXML
    private Button addState;

    @FXML
    private Button deleteState;

    @FXML
    private ChoiceBox graphicsItem;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private Pane sheet;

    @FXML
    private Button addCircleButton;

    @FXML
    private Button addRectangleButton;

    @FXML
    private Button addTextButton;

    @FXML
    private Button addOvalButton;

    @FXML
    private Button addLineButton;

    private GraphicsContext gc;

    private Main main;
    private Graphics PzCircle;
    private Graphics PzLine;
    private Graphics PzOval;
    private Graphics PzRectangle;
    private Graphics PzText;
    State state = new State();

    TimeLine timeLine;




    // These 2 variable are used for dragging and dropping
    double orgSceneX,orgSceneY;

//    private ObservableList<State> stateData = FXCollections.observableArrayList();


    public TimeLine initTimeLine(){
       return this.timeLine = new TimeLine();
    }
    public void setMain(Main main) {
//

        scrollPane.setContent(this.sheet);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        initTimeLine();
        printTimeLine();
//        main = main;

//        GraphicsContext gc = sheet.getGraphicsContext2D();
//        sheet.setStyle("-fx-background-color: red");
//        gc.setFill(Color.YELLOWGREEN);
//        gc.strokeOval(0,80,50,50);
//        gc.fillOval(100,80,50,50);



        // Add observable list data to the table
    }

    @FXML
    void addAState() {
//        System.out.println(timeLine.statesNum());
        System.out.println("Add a state");
        if(timeLine.statesNum() == 0){
            State s = new State();
            sheet.setStyle("-fx-background-color: white");
            sheet.setPrefSize(400,380);
            Camera camera = new Camera();
            sheet.getChildren().add(camera);
            camera.prefHeight(350);

            timeLine.addStateInTimeLine(s);
            stateList.getItems().add(s.getCurrentNumString());
        }else
            {
//            State latestState = timeLine.getLatestState();
            State x = new State(timeLine.getLatestState());
            sheet.setPrefSize(sheet.getWidth()+400,sheet.getHeight());

//            Pane ns =new Pane();
//            ns.setStyle("-fx-background-color: white");
//            this.scrollPane.setContent(ns);
            timeLine.addStateInTimeLine(x);
            this.state = x;
//            Camera camera = new Camera();
//            sheet.getChildren().add(camera);
//            sheet.getChildren().addAll(x.getAllStates());
            stateList.getItems().add(x.getCurrentNumString());
        }
//        timeLine.addStateInTimeLine();
        System.out.println("Total states : "+timeLine.statesNum());
        printTimeLine();


    }
    @FXML
    void deleteAState(ActionEvent event) {
//        System.out.println(timeLine.statesNum());
        System.out.println("Delete a state");
        if(timeLine.statesNum() == 0){
            System.out.println("No state in the Prezoom");
        }else
        {
            State latestState = timeLine.getLatestState();
            timeLine.deleteStateInTimeLine(latestState);
            stateList.getItems().remove(timeLine.statesNum());
            sheet.setPrefSize(sheet.getWidth()-400,sheet.getHeight());
        }
        System.out.println("Total states : "+ timeLine.statesNum());

    }

    @FXML
    void addACircle() {


        if(timeLine.statesNum() == 0){
            System.out.println("No state");
        }else {
            System.out.println("Add a circle");
//        Circle circle = new Circle(20,20,20);



            Circle circle = new Circle(20, 20, 20);
            circle.setCursor(Cursor.HAND);
            circle.setFill(Color.BLACK);
//
            State x = timeLine.getLatestState();
            x.addItemInState(circle);

            System.out.println(x.itemStates);

            System.out.println("add a circle in " + state.getCurrentNumString());

//        cir.setFill(Color.BLUE);
            this.sheet.getChildren().add(circle);

            circle.setOnMousePressed((t) -> {
                orgSceneX = t.getSceneX();
                orgSceneY = t.getSceneY();

                Circle c = (Circle) (t.getSource());
                c.toFront();
            });
            circle.setOnMouseDragged((t) -> {
                double offsetX = t.getSceneX() - orgSceneX;
                double offsetY = t.getSceneY() - orgSceneY;

                Circle c = (Circle) (t.getSource());

                c.setCenterX(c.getCenterX() + offsetX);
                c.setCenterY(c.getCenterY() + offsetY);

                orgSceneX = t.getSceneX();
                orgSceneY = t.getSceneY();
            });
        }

    }
    @FXML
    void addATextButton(ActionEvent event){
        System.out.println("Add a text");
        Text text = new Text("Write something here...");
        text.setFont(Font.font(20));
        text.setLayoutY(50);
        text.setLayoutX(50);
//        Double s = text.getLayoutX();
//        Double z = text.getLayoutY();
//        System.out.println(s+ " "+ z);
        sheet.getChildren().add(text);
        text.setOnMousePressed((t) -> {
            orgSceneX = t.getSceneX();
            orgSceneY = t.getSceneY();

            Text c = (Text) (t.getSource());
            c.toFront();
        });
        text.setOnMouseDragged((t) -> {
            double offsetX = t.getSceneX() - orgSceneX;
            double offsetY = t.getSceneY() - orgSceneY;

            Text c = (Text) (t.getSource());

            c.setLayoutX(c.getLayoutX() + offsetX);
            c.setLayoutY(c.getLayoutY() + offsetY);

            orgSceneX = t.getSceneX();
            orgSceneY = t.getSceneY();
        });
    }

    @FXML
    void addALine(ActionEvent event) {
        System.out.println("Add a line");
        Line line = new Line();
        line.setLayoutX(40);
        line.setLayoutY(40);
        line.setStartX(40);
        line.setEndX(200);
        sheet.getChildren().add(line);

        line.setOnMousePressed((t) -> {
            orgSceneX = t.getSceneX();
            orgSceneY = t.getSceneY();

            Line c = (Line) (t.getSource());
            c.toFront();
        });
        line.setOnMouseDragged((t) -> {
            double offsetX = t.getSceneX() - orgSceneX;
            double offsetY = t.getSceneY() - orgSceneY;

            Line c = (Line) (t.getSource());

            c.setLayoutX(c.getLayoutX() + offsetX);
            c.setLayoutY(c.getLayoutY() + offsetY);

            orgSceneX = t.getSceneX();
            orgSceneY = t.getSceneY();
        });

    }

    @FXML
    void addAOval(ActionEvent event) {
        System.out.println("Add an oval");
        Ellipse oval = new Ellipse(60,60,60,20);
        sheet.getChildren().add(oval);

        oval.setOnMousePressed((t) -> {
            orgSceneX = t.getSceneX();
            orgSceneY = t.getSceneY();

            Ellipse c = (Ellipse) (t.getSource());
            c.toFront();
        });
        oval.setOnMouseDragged((t) -> {
            double offsetX = t.getSceneX() - orgSceneX;
            double offsetY = t.getSceneY() - orgSceneY;

            Ellipse c = (Ellipse) (t.getSource());

            c.setLayoutX(c.getLayoutX() + offsetX);
            c.setLayoutY(c.getLayoutY() + offsetY);

            orgSceneX = t.getSceneX();
            orgSceneY = t.getSceneY();
        });
    }

    @FXML
    void addARectangle(ActionEvent event) {
        System.out.println("Add a rectangle");
        Rectangle rectangle = new Rectangle(20,20,100,50);
        sheet.getChildren().add(rectangle);

        rectangle.setOnMousePressed((t) -> {
            orgSceneX = t.getSceneX();
            orgSceneY = t.getSceneY();

            Rectangle c = (Rectangle) (t.getSource());
            c.toFront();
        });
        rectangle.setOnMouseDragged((t) -> {
            double offsetX = t.getSceneX() - orgSceneX;
            double offsetY = t.getSceneY() - orgSceneY;

            Rectangle c = (Rectangle) (t.getSource());

            c.setLayoutX(c.getLayoutX() + offsetX);
            c.setLayoutY(c.getLayoutY() + offsetY);

            orgSceneX = t.getSceneX();
            orgSceneY = t.getSceneY();
        });
    }

    public void printTimeLine(){
        System.out.println(timeLine.stateArrayList);
    }
}
