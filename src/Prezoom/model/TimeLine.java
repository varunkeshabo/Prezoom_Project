package Prezoom.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class TimeLine {
    public ArrayList<State> stateArrayList;

    public TimeLine(){
        this.stateArrayList = new ArrayList<State>();
    }

    public int statesNum(){
        return stateArrayList.size();
    }
    public void addStateInTimeLine(State state){
        State s = new State(state);
        this.stateArrayList.add(s);
    }
    public void deleteStateInTimeLine(State state){
        this.stateArrayList.remove(state);
    }

    public ArrayList<State> getTimeLineItems(){
        ArrayList<State> state = new ArrayList<State>();
        state = this.stateArrayList;
        return state;
    }
    public State getLatestState(){
        return stateArrayList.get(stateArrayList.size()-1);
    }
}
