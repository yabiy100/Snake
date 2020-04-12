package de.hhu.snake.Components;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Data
@NoArgsConstructor
public class Tail {

    List<Possition> possitions;

    public Tail(Possition startingPossiting) {
        possitions = new ArrayList<>();
        possitions.add(startingPossiting);
    }

    public int[] getXPossitions() {
        int[] XPossitions = new int[possitions.size()];
        IntStream.range(0, possitions.size())
                .forEach(i -> XPossitions[i] = possitions.get(i).getX());
        return XPossitions;
    }

    public int[] getYPossitions() {
        int[] YPossitions = new int[possitions.size()];
        IntStream.range(0, possitions.size())
                .forEach(i -> YPossitions[i] = possitions.get(i).getY());
        return YPossitions;
    }

    public void move(Possition possition) {
        if (!possitions.isEmpty()) {
            possitions.remove(possitions.size());
        }
        possitions.add(0, possition);
    }

    public void grow(Possition possition) {
        possitions.add(0, possition.copy());
    }
}
