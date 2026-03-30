package snakeandladder;

import snakeandladder.models.Board;
import snakeandladder.models.Player;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Game {
    private Board board;
    private Queue<Player> players;
    private int diceCount;
    private Random random;

    public Game(Board board, int diceCount) {
        this.board = board;
        this.players = new LinkedList<>();
        this.diceCount = diceCount;
        this.random = new Random();
    }

    public void addPlayer(Player player) {
        players.offer(player);
    }

    private int rollDice() {
        int total = 0;
        for (int i = 0; i < diceCount; i++) {
            total += random.nextInt(6) + 1;
        }
        return total;
    }

    public void start() {
        while (players.size() > 1) {
            Player currentPlayer = players.poll();
            int roll = rollDice();
            int newPosition = currentPlayer.getCurrentPosition() + roll;

            if (newPosition > board.getSize()) {
                System.out.println(currentPlayer.getName() + " rolled a " + roll + " but is out of bounds. Stays at " + currentPlayer.getCurrentPosition());
                players.offer(currentPlayer);
            } else if (newPosition == board.getSize()) {
                System.out.println(currentPlayer.getName() + " rolled a " + roll + " and reached the finish line! Current Position: " + newPosition);
                System.out.println(currentPlayer.getName() + " wins!");
                // Player won, don't add back to queue if we want multiple winners, 
                // but for a simple game, we can stop here or continue for other ranks.
            } else {
                int finalPosition = board.getNewPosition(newPosition);
                if (finalPosition < newPosition) {
                    System.out.println(currentPlayer.getName() + " rolled a " + roll + " and got bitten by a snake at " + newPosition + "! Goes to " + finalPosition);
                } else if (finalPosition > newPosition) {
                    System.out.println(currentPlayer.getName() + " rolled a " + roll + " and climbed a ladder at " + newPosition + "! Goes to " + finalPosition);
                } else {
                    System.out.println(currentPlayer.getName() + " rolled a " + roll + " and moved to " + finalPosition);
                }
                currentPlayer.setCurrentPosition(finalPosition);
                players.offer(currentPlayer);
            }
        }
    }
}
