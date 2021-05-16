package Prezoom.model;

import Prezoom.controller.EditingLayoutController;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StateTest {
    private State state0;
    private State state1;
    private Circle circle;
    private Ellipse ellipse;
    private Line line;
    private Rectangle rectangle;

    @BeforeEach
    void setUp() {
        state0 = new State();
        state1 = new State(state0);
    }

    @Test
    void addNoItemInState() {
        assertEquals(0,
                state0.getAllStates().size(),
                "After adding new state check the state number");
    }

    @DisplayName("check addItemInState method")
    @Test
    void addItemInState() {
        state1.addItemInState(circle);
        assertEquals(1,
                state1.getAllStates().size(),
                "After adding new state check the state number");
    }

    @DisplayName("check addItemInState method with multiple items")
    @Test
    void addMultipleItemInState() {
        state1.addItemInState(circle);
        state1.addItemInState(ellipse);
        state1.addItemInState(line);

        assertEquals(3,
                state1.getAllStates().size(),
                "After adding new state check the state number");
    }

    @DisplayName("check getCurrentNumString")
    @Test
    void getCurrentNumString() {
        assertEquals("State 0",
                state0.getCurrentNumString(),
                "Get current state number");
    }
}