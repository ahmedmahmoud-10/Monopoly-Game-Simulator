package battleship.simulation; //ahmed ali

import java.util.HashMap;
import java.util.Map;

public class PlayerStandingSummary {
    private final Map<String, Integer> winCounts = new HashMap<>();
    private int totalGames = 0;
    private int draws = 0;

    public void recordResult(String winner) {
        totalGames++;
        if (winner.equalsIgnoreCase("Draw")) {
            draws++;
        } else {
            winCounts.put(winner, winCounts.getOrDefault(winner, 0) + 1);
        }
    }

    public void printSummary() {
        System.out.println("\n Simulation Summary:");
        for (var entry : winCounts.entrySet()) {
            System.out.println(entry.getKey() + " Wins: " + entry.getValue());
        }
        System.out.println("Draws: " + draws);
        System.out.println("Total Games: " + totalGames);
    }

    public Map<String, Integer> getWinCounts() {
        return winCounts;
    }

    public int getDraws() {
        return draws;
    }

    public int getTotalGames() {
        return totalGames;
    }
}
