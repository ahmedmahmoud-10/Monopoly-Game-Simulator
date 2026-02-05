package battleship.simulation;

import battleship.engine.GameSimulation;
import battleship.util.CSVLogger;

public class DetailedSimulationRunner {
    public static void main(String[] args) {
        GameSimulation simulation = new GameSimulation();
        PlayerStandingSummary summary = new PlayerStandingSummary();

        int totalGames = 100; // Number of games to simulate.

        for (int i = 0; i < totalGames; i++) {
            MatchResult result = simulation.simulateMatchWithDetails();

            System.out.println("────────────────────────────────────────────");
            System.out.println("Game #" + (i + 1));
            System.out.println("Player 1: " + result.getPlayer1().getName());
            System.out.println("Player 2: " + result.getPlayer2().getName());
            System.out.println("Winner: " + result.getWinner());
            System.out.println("Total Turns: " + result.getTotalTurns());
            System.out.println(result.getPlayer1().getName() + " Hits: " + result.getP1Hits() + ", Ships Left: " + result.getP1ShipsLeft());
            System.out.println(result.getPlayer2().getName() + " Hits: " + result.getP2Hits() + ", Ships Left: " + result.getP2ShipsLeft());
            System.out.println("\nTurn-by-Turn Log:");
            for (String move : result.getMoveLog()) {
                System.out.println("• " + move);
            }

            summary.recordResult(result.getWinner());
            CSVLogger.logGame(result.getPlayer1().getName(), result.getPlayer2().getName(), result.getWinner());
            CSVLogger.updateStrategyStats(result.getWinner());
            CSVLogger.logDetailedMoves(i + 1, result);
            CSVLogger.logDetailedStats(i + 1, result);
        }

        System.out.println("\n Final Summary:");
        summary.printSummary();
    }
}
