import de.hhu.snake.Components.Apple;
import de.hhu.snake.Components.Direction;
import de.hhu.snake.Components.Gameboard;
import de.hhu.snake.Components.Possition;
import de.hhu.snake.Components.Snake;
import de.hhu.snake.Components.Tail;
import de.hhu.snake.main;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = main.class)
public class BoardTests {

    private final int STEP_SIZE = 20;
    private Possition startPossition;
    private Snake snake;
    @Mock
    private Gameboard fullBoard;

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
    void spawnAppleLastFreePossition() {
        List<Possition> possitions = new ArrayList<>();
        possitions.add(new Possition(10, 30));
        possitions.add(new Possition(30, 30));
        Tail shortTail = new Tail();
        shortTail.setPossitions(possitions);
        Snake shortSnake = new Snake(Direction.NORTH, new Possition(30, 10));
        shortSnake.setTail(shortTail);
        Gameboard fullBoard = new Gameboard(40, 40, shortSnake);

        fullBoard.spawnApple();

        assertEquals(new Possition(10, 10), fullBoard.getApple().getPossition());
    }
}
