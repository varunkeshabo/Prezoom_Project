package Prezoom.model;

import Prezoom.model.Graphics.Graphics;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

public class State {
    public ArrayList<Shape> itemStates;
    private int currentState = 0;

    public State(){
        this.currentState = 0;
        this.itemStates = new ArrayList<Shape>();
    }

    public State(State state){
        this.currentState = state.currentState + 1;
        this.itemStates = state.itemStates;
    }

    public void addItemInState(Shape item){
        this.itemStates.add(item);
    }
    public ArrayList<Shape> getAllStates(){
        return this.itemStates;
    }

    public String getCurrentNumString(){
        int i = this.currentState / 2;
        String num = String.valueOf(i);
        return "State "+num;
    }

}
