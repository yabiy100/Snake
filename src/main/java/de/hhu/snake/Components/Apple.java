package de.hhu.snake.Components;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Random;


@NoArgsConstructor
@Getter
@Setter
public class Apple {
    private Possition possition;

    public void spawn(List<Possition> freeFields) {
        possition = freeFields.get(new Random().nextInt(freeFields.size()));
    }
}
