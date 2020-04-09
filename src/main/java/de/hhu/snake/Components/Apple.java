package de.hhu.snake.Components;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Random;


@NoArgsConstructor
@Getter
public class Apple {
    private Possition possition;

    public void respawn(List<Possition> freeFields) {
        possition = freeFields.get(new Random().nextInt(freeFields.size()));
    }
}
