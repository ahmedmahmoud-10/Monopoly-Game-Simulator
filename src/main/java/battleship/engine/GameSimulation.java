package battleship.engine; //ahmed

import battleship.player.*;
import battleship.simulation.MatchResult;

import java.util.*;

public class GameSimulation {
    private final List<String> strategyNames = List.of("Random", "Greedy", "Smart", "Strategic", "Aggressive"); //using the array list

    public String[] getTwoRandomStrategies() { //this method will give us 2 random strategies and put them head to head
        Random rand = new Random();
        String p1 = strategyNames.get(rand.nextInt(strategyNames.size()));
        String p2;
        do {
            p2 = strategyNames.get(rand.nextInt(strategyNames.size()));
        } while (p1.equals(p2));
        return new String[]{p1, p2};
    }

    public String simulateMatch(String p1Name, String p2Name) {
        Player p1 = getNewInstance(p1Name);
        Player p2 = getNewInstance(p2Name);

        p1.placeShips();
        p2.placeShips();

        int turn = 0;
        int maxTurns = 150; //setting max turns to 150, more competitive game and avoids infinite turns

        // Alternate turns between p1 and p2

        while (!p1.getBoard().hasLost() && !p2.getBoard().hasLost() && turn < maxTurns) {
            int[] move1 = p1.makeMove();
            p2.getBoard().attack(move1[0], move1[1]);

            int[] move2 = p2.makeMove();
            p1.getBoard().attack(move2[0], move2[1]);

            turn++;
        }

        if (p1.getBoard().hasLost() && p2.getBoard().hasLost()) return "Draw";
        if (p1.getBoard().hasLost()) return p2.getName();
        if (p2.getBoard().hasLost()) return p1.getName();
        return "Draw";
    }

    // more detailed simulation with turn logs
    public MatchResult simulateMatchWithDetails() {
        String[] names = getTwoRandomStrategies();
        Player p1 = getNewInstance(names[0]);
        Player p2 = getNewInstance(names[1]);

        p1.placeShips();
        p2.placeShips();

        List<String> moveLog = new ArrayList<>();
        int p1Hits = 0, p2Hits = 0;
        int turn = 0;
        int maxTurns = 150;

        while (!p1.getBoard().hasLost() && !p2.getBoard().hasLost() && turn < maxTurns) {
            int[] move1 = p1.makeMove();
            boolean hit1 = p2.getBoard().attack(move1[0], move1[1]);
            if (hit1) p1Hits++;
            moveLog.add(p1.getName() + " fires at (" + move1[0] + "," + move1[1] + ") → " + (hit1 ? "HIT" : "MISS"));

            int[] move2 = p2.makeMove();
            boolean hit2 = p1.getBoard().attack(move2[0], move2[1]);
            if (hit2) p2Hits++;
            moveLog.add(p2.getName() + " fires at (" + move2[0] + "," + move2[1] + ") → " + (hit2 ? "HIT" : "MISS"));

            turn++;
        }

        // Decide final winner
        String winner = "Draw";
        if (p1.getBoard().hasLost() && !p2.getBoard().hasLost()) winner = p2.getName();
        else if (p2.getBoard().hasLost() && !p1.getBoard().hasLost()) winner = p1.getName();

        return new MatchResult(
                p1, p2, winner, turn,
                p1Hits, p2Hits,
                countRemainingShips(p1), countRemainingShips(p2),
                moveLog
        );
    }

    private int countRemainingShips(Player player) {
        return (int) player.getFleet().stream().filter(s -> !s.isSunk()).count();
    }

    private Player getNewInstance(String strategyName) {
        return switch (strategyName) {
            case "Random" -> new RandomPlayer("Random");
            case "Greedy" -> new GreedyPlayer("Greedy");
            case "Smart" -> new SmartPlayer("Smart");
            case "Strategic" -> new StrategicPlayer("Strategic");
            case "Aggressive" -> new AggressivePlayer("Aggressive");
            default -> throw new IllegalArgumentException("Unknown strategy: " + strategyName);
        };
    }
}