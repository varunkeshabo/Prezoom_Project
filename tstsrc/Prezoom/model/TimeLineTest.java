package Prezoom.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TimeLineTest {
    private TimeLine timeLine;
    private State state0;
    private State state1;
    private State state2;
    private State state3;

    @BeforeEach
    void setUp() {
        timeLine = new TimeLine();
        state0 = new State();
        state1 = new State(state0);
        state2 = new State(state1);
        state3 = new State(state2);
    }

    @Test
    void addOneStateInTimeLine() {
        timeLine.addStateInTimeLine(state0);
        assertEquals(1,
                timeLine.statesNum(),
                "added new state and check the size increased");
    }

    @DisplayName("check addStateInTimeLine method")
    @Test
    void addStateInTimeLine() {
        timeLine.addStateInTimeLine(state0);
        timeLine.addStateInTimeLine(state1);
        assertEquals(2,
                timeLine.statesNum(),
                "added new state and check the size increased");
    }

    @Test
    void addMultipleStateInTimeLine() {
        timeLine.addStateInTimeLine(state0);
        timeLine.addStateInTimeLine(state1);
        timeLine.addStateInTimeLine(state2);
        timeLine.addStateInTimeLine(state3);
        assertEquals(4,
                timeLine.statesNum(),
                "added new state and check the size increased");
    }

    @DisplayName("check deleteStateInTimeLine method corner case")
    @Test
    void deleteStateInTimeLine() {
        timeLine.deleteStateInTimeLine(state0);
        assertEquals(0,
                timeLine.statesNum(),
                "deleted state and check the size decreased");
    }

    @DisplayName("check deleteStateInTimeLine method with two states")
    @Test
    void deleteStateInTimeLine1() {
        timeLine.addStateInTimeLine(state0);
        timeLine.addStateInTimeLine(state1);
        timeLine.deleteStateInTimeLine(timeLine.getLatestState());
        assertEquals(1,
                timeLine.statesNum(),
                "deleted state and check the size decreased");
    }

    @Test
    void DeleteMutipleStatesInTimeLine() {
        timeLine.addStateInTimeLine(state0);
        timeLine.addStateInTimeLine(state1);
        timeLine.addStateInTimeLine(state2);
        timeLine.addStateInTimeLine(state3);
        timeLine.deleteStateInTimeLine(timeLine.getLatestState());
        timeLine.deleteStateInTimeLine(timeLine.getLatestState());
        timeLine.deleteStateInTimeLine(timeLine.getLatestState());
        assertEquals(1,
                timeLine.statesNum(),
                "deleted state and check the size decreased");
    }
}