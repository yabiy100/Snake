package de.hhu.snake.Components;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Gameboard {
    private int length;
    private int height;
    private Snake snake;
    private Apple apple;
    private int STEP_SIZE = 20;

    public Gameboard(int length, int height, Snake snake) {
        this.length = length;
        this.height = height;
        this.snake = snake;
        apple = new Apple();
    }

    public void spawnApple() {
        apple.spawn(getFreePossitions());
    }

    public boolean snakeIsAlive() {
        boolean inLength = snake.getPossition().getX() > 0 && snake.getPossition().getX() < length;
        boolean inHeight = snake.getPossition().getY() > 0 && snake.getPossition().getY() < height;
        return inLength && inHeight;
    }

    public void moveSnake(Direction direction) {
        snake.move(direction, apple.getPossition());
    }

    public List<Possition> getFreePossitions() {
        List<Possition> result = listAllPossitions();
        result.remove(snake.getPossition());
        if (snake.getTail() != null) {
            removeTailPossitions(result);
        }
        return result;
    }

    private void removeTailPossitions(List<Possition> result) {
        for (int i = 0; i < snake.getTail().getPossitions().size(); i++) {
            result.remove(snake.getTail().getPossitions().get(i));
        }
    }

    private List<Possition> listAllPossitions() {
        List<Possition> allPossitions = new ArrayList<>();
        for (int length = STEP_SIZE / 2; length < this.length; length += STEP_SIZE) {
            addHeightPossitions(allPossitions, length);

        }
        return allPossitions;
    }

    private void addHeightPossitions(List<Possition> allPossitions, int length) {
        for (int height = STEP_SIZE / 2; height < this.height; height += STEP_SIZE) {
            allPossitions.add(new Possition(length, height));
        }
    }
}
