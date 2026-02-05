package battleship.engine; //ahmed


import battleship.player.*;
import battleship.util.CSVLogger;
import java.util.List;
import java.util.Random;


public class GameEngine {

    private final List<Player> strategies = List.of( //creating an arrary list of players
            new RandomPlayer("Random"),
            new GreedyPlayer("Greedy"),
            new SmartPlayer("Smart"),
            new StrategicPlayer("Strategic"),
            new AggressivePlayer("Aggressive")
    );

    public void runSimulations(int rounds) { // method to run the simulation
        for (int i = 0; i < rounds; i++) {
            // Randomly pick two distinct strategies (players) for each game
            Random rand = new Random();
            Player p1 = strategies.get(rand.nextInt(strategies.size()));
            Player p2;
            do {
                p2 = strategies.get(rand.nextInt(strategies.size()));
            } while (p1.getName().equals(p2.getName()));

            System.out.println("Game " + (i + 1) + ": " + p1.getName() + " vs " + p2.getName());

            p1.placeShips();
            p2.placeShips();

            int turn = 0;
            // Continue playing until one or both players lose all ships
            while (!p1.getBoard().hasLost() && !p2.getBoard().hasLost()) {
                int[] move1 = p1.makeMove();
                p2.getBoard().attack(move1[0], move1[1]);

                int[] move2 = p2.makeMove();
                p1.getBoard().attack(move2[0], move2[1]);

                System.out.println("Turn " + (turn + 1));
                System.out.println(p1.getName() + " sees " + p2.getFleet().stream().filter(s -> !s.isSunk()).count() + " ships remaining.");
                System.out.println(p2.getName() + " sees " + p1.getFleet().stream().filter(s -> !s.isSunk()).count() + " ships remaining.");

                turn++;
                // End game if max turns reached (to avoid infinite loops)
                if (turn >= 200) {
                    boolean bothLost = p1.getBoard().hasLost() && p2.getBoard().hasLost();
                    System.out.println("Draw after 200 turns.");
                    CSVLogger.logGame(p1.getName(), p2.getName(), bothLost ? "Draw" : "No Result"); //csvlogger to add results in our csv
                    CSVLogger.updateStrategyStats("Draw");
                    break;
                }
            }

            //logs the result of the game

            if (p1.getBoard().hasLost() && p2.getBoard().hasLost()) {
                System.out.println("The game ends in a tie!");
                CSVLogger.logGame(p1.getName(), p2.getName(), "Draw");
                CSVLogger.updateStrategyStats("Draw");
            } else if (p1.getBoard().hasLost()) {
                System.out.println(p2.getName() + " wins!");
                CSVLogger.logGame(p1.getName(), p2.getName(), p2.getName());
                CSVLogger.updateStrategyStats(p2.getName());
            } else if (p2.getBoard().hasLost()) {
                System.out.println(p1.getName() + " wins!");
                CSVLogger.logGame(p1.getName(), p2.getName(), p1.getName());
                CSVLogger.updateStrategyStats(p1.getName());
            }
        }
    }
}