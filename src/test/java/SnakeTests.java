import de.hhu.snake.Components.Apple;
import de.hhu.snake.Components.Direction;
import de.hhu.snake.Components.Gameboard;
import de.hhu.snake.Components.Possition;
import de.hhu.snake.Components.Snake;
import de.hhu.snake.Components.Tail;
import de.hhu.snake.main;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = main.class)
public class SnakeTests {

    private final int STEP_SIZE = 20;
    private Possition startPossition;
    private Snake snake;

    @BeforeEach
    void init() {
        startPossition = new Possition(100, 100);
        snake = new Snake(Direction.NORTH, startPossition);
    }

    @Test
    void movePossitionNorth() {
        startPossition.move(Direction.NORTH);
        assertEquals(100 - STEP_SIZE, startPossition.getY());
    }

    @Test
    void movePossitionEast() {
        startPossition.move(Direction.EAST);
        assertEquals(100 + STEP_SIZE, startPossition.getX());
    }

    @Test
    void movePossitionSouth() {
        Possition one = new Possition(100, 100);
        one.move(Direction.SOUTH);
        assertEquals(100 + STEP_SIZE, one.getY());
    }

    @Test
    void movePossitionWest() {
        startPossition.move(Direction.WEST);
        assertEquals(100 - STEP_SIZE, startPossition.getX());
    }

    @Test
    void getTailPossitions() {
        Tail backEnd = new Tail(new Possition(140, 120));
        assertEquals(120, backEnd.getYPossitions()[0]);
        assertEquals(140, backEnd.getXPossitions()[0]);
    }

    @Test
    void moveSnakeNorth() {
        Snake schroer = new Snake(Direction.EAST, new Possition(100, 100));
        schroer.move(Direction.NORTH);
        assertEquals(new Possition(100, 100 - STEP_SIZE), schroer.getPossition());
    }

    @Test
    void moveSnakeEast() {
        Snake schroer = new Snake(Direction.SOUTH, new Possition(100, 100));
        schroer.move(Direction.EAST);
        assertEquals(new Possition(100 + STEP_SIZE, 100), schroer.getPossition());
    }

    @Test
    void moveSnakeSouth() {
        Snake schroer = new Snake(Direction.WEST, new Possition(100, 100));
        schroer.move(Direction.SOUTH);
        assertEquals(new Possition(100, 100 + STEP_SIZE), schroer.getPossition());
    }

    @Test
    void moveSnakeWest() {
        Snake schroer = new Snake(Direction.NORTH, new Possition(100, 100));
        schroer.move(Direction.WEST);
        assertEquals(new Possition(100 - STEP_SIZE, 100), schroer.getPossition());
    }

    @Test
    void respawnApple() {
        Apple eve = new Apple();
        List<Possition> freePossitions = new ArrayList<>();
        freePossitions.add(new Possition(20, 40));
        freePossitions.add(new Possition(80, 60));
        eve.spawn(freePossitions);
        assertTrue(new Possition(20, 40).equals(eve.getPossition())
                || new Possition(80, 60).equals(eve.getPossition()));
    }

    @Test
    void createGameboard() {
        Gameboard board = new Gameboard(200, 200, snake);
    }

    @Test
    void spawnAppleGameboard() {
        Gameboard board = new Gameboard(200, 200, snake);
        board.spawnApple();
        assertNotEquals(snake.getPossition(), board.getApple().getPossition());
    }

    @Test
    void SnakeIsDead() {
        Snake conrad = new Snake(Direction.SOUTH, new Possition(0, 60));
        Gameboard board = new Gameboard(200, 200, conrad);
        snake.move(Direction.EAST);
        assertFalse(board.snakeIsAlive());
    }
}
