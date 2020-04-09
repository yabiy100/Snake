package de.hhu.snake.Components;

import lombok.Data;

@Data
public class Snake {
    private Direction direction;
    private Possition possition;
    private Tail tail;
    private boolean isAlive;

    public Snake(Direction direction, Possition possition) {
        this.direction = direction;
        this.possition = possition;
    }

    public void move(Direction toMove) {
        //check if Snake would do a 180 degree turn
        if(toMove.equals(Direction.NORTH) && direction == Direction.SOUTH
            || toMove.equals(Direction.EAST) && direction == Direction.WEST
            || toMove.equals(Direction.SOUTH) && direction == Direction.NORTH
            || toMove.equals(Direction.WEST) && direction == Direction.EAST){
            return;
        }
        direction = toMove;
        possition.move(toMove);
    }

    public boolean isAlive() {
        return true;
    }
}
