import de.hhu.snake.Components.Apple;
import de.hhu.snake.Components.Direction;
import de.hhu.snake.Components.Gameboard;
import de.hhu.snake.Components.Possition;
import de.hhu.snake.Components.Snake;
import de.hhu.snake.Components.Tail;
import de.hhu.snake.main;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest(classes = main.class)
public class SnakeTests {

    private final int STEP_SIZE = 20;
    private Possition startPossition;
    private Snake snake;
    @Mock
    private Gameboard fullBoard;
    private Gameboard mediumBoard;
    private Apple adam;

    @BeforeEach
    void init() {
        startPossition = new Possition(40, 40);
        snake = new Snake(Direction.NORTH, startPossition);
        mediumBoard = new Gameboard(100, 100, snake);
        adam = new Apple();
        adam.setPossition(new Possition(10, 10));
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
        schroer.move(Direction.NORTH, adam.getPossition());
        assertEquals(100, schroer.getPossition().getX());
        assertEquals(80, schroer.getPossition().getY());
    }

    @Test
    void moveSnakeEast() {
        Snake schroer = new Snake(Direction.SOUTH, new Possition(100, 100));
        schroer.move(Direction.EAST, null);
        assertEquals(120, schroer.getPossition().getX());
        assertEquals(100, schroer.getPossition().getY());
    }

    @Test
    void moveSnakeSouth() {
        Snake schroer = new Snake(Direction.WEST, new Possition(100, 100));
        schroer.move(Direction.SOUTH, null);
        assertEquals(100, schroer.getPossition().getX());
        assertEquals(120, schroer.getPossition().getY());
    }

    @Test
    void moveSnakeWest() {
        Snake schroer = new Snake(Direction.NORTH, new Possition(100, 100));
        schroer.move(Direction.WEST, null);
        assertEquals(80, schroer.getPossition().getX());
        assertEquals(100, schroer.getPossition().getY());
    }

    @Test
    void moveSnakeParkour() {
        Snake mueller = new Snake(Direction.EAST, new Possition(100, 100));
        Gameboard catan = new Gameboard(400, 400, mueller);
        catan.moveSnake(Direction.NORTH);
        catan.moveSnake(Direction.NORTH);
        catan.moveSnake(Direction.WEST);
        catan.moveSnake(Direction.NORTH);
        catan.moveSnake(Direction.EAST);
        catan.moveSnake(Direction.EAST);
        assertEquals(new Possition(100 + STEP_SIZE, 100 - 3 * STEP_SIZE), mueller.getPossition());
        assertEquals(100 + STEP_SIZE, mueller.getPossition().getX());
        assertEquals(100 - 3 * STEP_SIZE, mueller.getPossition().getY());
    }

    @Disabled("isnt implemented yet")
    @Test
    void SnakeIsDead() {
        Snake conrad = new Snake(Direction.SOUTH, new Possition(0, 60));
        Gameboard board = new Gameboard(200, 200, conrad);
        board.moveSnake(Direction.EAST);
        assertFalse(board.snakeIsAlive());
    }

    @Test
    void SnakeIsGrowing() {
        adam.setPossition(new Possition(40, 20));
        mediumBoard.setApple(adam);

        mediumBoard.moveSnake(Direction.NORTH);

        assertEquals(1, snake.getTail().getPossitions().size());
        assertEquals(40, snake.getTail().getXPossitions()[0]);
        assertEquals(40, snake.getTail().getYPossitions()[0]);

    }
}
