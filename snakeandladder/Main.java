package snakeandladder;

import snakeandladder.models.*;

public class Main {
    public static void main(String[] args) {
        Board board = new Board(100);
        
        // Add Snakes
        board.addSnake(new Snake(17, 7));
        board.addSnake(new Snake(54, 34));
        board.addSnake(new Snake(62, 19));
        board.addSnake(new Snake(64, 60));
        board.addSnake(new Snake(87, 24));
        board.addSnake(new Snake(93, 73));
        board.addSnake(new Snake(95, 75));
        board.addSnake(new Snake(99, 78));

        // Add Ladders
        board.addLadder(new Ladder(4, 14));
        board.addLadder(new Ladder(9, 31));
        board.addLadder(new Ladder(20, 38));
        board.addLadder(new Ladder(28, 84));
        board.addLadder(new Ladder(40, 59));
        board.addLadder(new Ladder(51, 67));
        board.addLadder(new Ladder(63, 81));
        board.addLadder(new Ladder(71, 91));

        Game game = new Game(board, 1);
        game.addPlayer(new Player("drax", 1));
        game.addPlayer(new Player("berit", 2));

        System.out.println("Starting Snake and Ladder Game...");
        game.start();
    }
}
