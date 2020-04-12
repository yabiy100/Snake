package de.hhu.snake.Components;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Possition {
    int X;
    int Y;
    final int STEP_SIZE = 20;

    public void move(Direction direction) {
        switch (direction) {
            case NORTH:
                Y -= STEP_SIZE;
                break;
            case EAST:
                X += STEP_SIZE;
                break;
            case SOUTH:
                Y += STEP_SIZE;
                break;
            case WEST:
                X -= STEP_SIZE;
                break;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Possition)) {
            return false;
        }
        Possition toCompare = (Possition) o;
        return X == toCompare.getX() && Y == toCompare.getY();
    }

    public Possition copy() {
        return new Possition(X, Y);
    }
}
