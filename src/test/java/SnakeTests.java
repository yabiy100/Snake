import de.hhu.snake.Components.Direction;
import de.hhu.snake.Components.Possition;
import de.hhu.snake.Components.Snake;
import de.hhu.snake.Components.Tail;
import de.hhu.snake.main;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = main.class)
public class SnakeTests {

    private final int STEP_SIZE = 20;
    private Possition startPossition;

    @BeforeEach
    void init(){
        Possition startPossition = new Possition(100,100);
    }

    @Test
    void movePossitionNorth(){
        startPossition.move(Direction.NORTH);
        assertEquals(100 - STEP_SIZE, startPossition.getY());
    }

    @Test
    void movePossitionEast(){
        startPossition.move(Direction.EAST);
        assertEquals(100 + STEP_SIZE, startPossition.getX());
    }

    @Test
    void movePossitionSouth(){
        Possition one = new Possition(100,100);
        one.move(Direction.SOUTH);
        assertEquals(100 + STEP_SIZE, one.getY());
    }

    @Test
    void movePossitionWest(){
        startPossition.move(Direction.WEST);
        assertEquals(100 - STEP_SIZE, startPossition.getX());
    }

    @Test
    void getTailPossitions(){
        Tail backEnd = new Tail(new Possition(140, 120));
        assertEquals(120, backEnd.getYPossitions()[0]);
        assertEquals(140, backEnd.getXPossitions()[0]);
    }

    @Test
    void moveSnakeNorth(){
        Snake schroer = new Snake(Direction.EAST, new Possition(100, 100));
        schroer.move(Direction.NORTH);
        assertTrue(new Possition(100, 100 - STEP_SIZE).equals(schroer.getPossition()));
    }

    @Test
    void moveSnakeEast(){
        Snake schroer = new Snake(Direction.SOUTH, new Possition(100, 100));
        schroer.move(Direction.EAST);
        assertTrue(new Possition(100 + STEP_SIZE, 100).equals(schroer.getPossition()));
    }

    @Test
    void moveSnakeSouth(){
        Snake schroer = new Snake(Direction.WEST, new Possition(100, 100));
        schroer.move(Direction.SOUTH);
        assertTrue(new Possition(100, 100 + STEP_SIZE).equals(schroer.getPossition()));
    }

    @Test
    void moveSnakeWest(){
        Snake schroer = new Snake(Direction.NORTH, new Possition(100, 100));
        schroer.move(Direction.WEST);
        assertTrue(new Possition(100 - STEP_SIZE, 100).equals(schroer.getPossition()));
    }

    @Test
    void SnakeIsDead(){
        Snake gruenrock = new Snake(Direction.WEST, new Possition(0, 50));
        gruenrock.move(Direction.WEST);
        assertFalse(gruenrock.isAlive());
    }
}
