package org.antonkhmarun.enums;

import java.util.Random;

public enum State {
    ALABAMA("Alabama"),
    COLORADO("Colorado"),
    IOWA("Iowa"),
    MONTANA("Montana"),
    RHODAE("Rhode"),
    TEXAS("Texas"),
    US_VIRGIN_ISLAND("US Virgin Islands"),
    WYOMING("Wyoming");

    private final String state;

    State(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public static State getRandomState() {
        State[] states = State.values();
        int randIndex = new Random().nextInt(states.length);
        return states[randIndex];
    }
}
