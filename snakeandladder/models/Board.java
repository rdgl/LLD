package snakeandladder.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
    private int size;
    private List<Snake> snakes;
    private List<Ladder> ladders;
    private Map<Integer, Integer> jumpMap;

    public Board(int size) {
        this.size = size;
        this.snakes = new ArrayList<>();
        this.ladders = new ArrayList<>();
        this.jumpMap = new HashMap<>();
    }

    public void addSnake(Snake snake) {
        snakes.add(snake);
        jumpMap.put(snake.getStart(), snake.getEnd());
    }

    public void addLadder(Ladder ladder) {
        ladders.add(ladder);
        jumpMap.put(ladder.getStart(), ladder.getEnd());
    }

    public int getNewPosition(int currentPosition) {
        if (jumpMap.containsKey(currentPosition)) {
            return jumpMap.get(currentPosition);
        }
        return currentPosition;
    }

    public int getSize() {
        return size;
    }
}
