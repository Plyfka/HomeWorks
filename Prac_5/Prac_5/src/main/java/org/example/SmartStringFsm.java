
package org.example;

public class SmartStringFsm {
    private State currentState = State.S;

    public State process(String input) {
        currentState = State.S;
        for (char c : input.toCharArray()) {
            if (currentState == State.F) continue;

            switch (currentState) {
                case S:
                    if (c == 'T') currentState = State.ONE;
                    break;
                case ONE:
                    if (c == 'E') currentState = State.TWO;
                    else if (c == 'T') currentState = State.ONE;
                    else currentState = State.S;
                    break;
                case TWO:
                    if (c == 'S') currentState = State.THREE;
                    else if (c == 'T') currentState = State.ONE;
                    else currentState = State.S;
                    break;
                case THREE:
                    if (c == 'T') currentState = State.F;
                    else if (c == 'T') currentState = State.ONE;
                    else currentState = State.S;
                    break;
            }
        }
        return currentState;
    }
}

