package de.hhu.snake.Components;

import lombok.Data;

@Data
public class Snake {
    private Direction direction;
    private Possition possition;
    private Tail tail;
    private boolean isAlive = true;

    public Snake(Direction direction, Possition possition) {
        this.direction = direction;
        this.possition = possition;
    }

    public void move(Direction toMove, Possition applePossition) {
        //check if Snake would do a 180 degree turn
        if (toMove.equals(Direction.NORTH) && direction == Direction.SOUTH
                || toMove.equals(Direction.EAST) && direction == Direction.WEST
                || toMove.equals(Direction.SOUTH) && direction == Direction.NORTH
                || toMove.equals(Direction.WEST) && direction == Direction.EAST) {
            return;
        }
        direction = toMove;
        if (isEatingApple(applePossition, toMove)) {
            growTail();
            possition.move(toMove);
            return;
        }
        if (tail != null) {
            tail.move(possition.copy());
        }
        possition.move(toMove);

    }

    private void growTail() {
        if (tail != null) {
            tail.grow(possition.copy());
            return;
        }
        tail = new Tail(possition.copy());
    }

    private boolean isEatingApple(Possition applePossition, Direction tomove) {
        Possition futurePossition = possition.copy();
        futurePossition.move(tomove);
        return futurePossition.equals(applePossition);
    }
}
