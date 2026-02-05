package battleship.simulation; //ahmed

import battleship.engine.GameSimulation;
import battleship.util.CSVLogger;
import java.util.ArrayList;
import java.util.List;

public class SimulationExperiment {
    public static void main(String[] args) {
        GameSimulation simulation = new GameSimulation();
        PlayerStandingSummary summary = new PlayerStandingSummary();
        List<SimulationInDetail> details = new ArrayList<>();

        int totalGames = 50;

        for (int i = 0; i < totalGames; i++) {
            String[] players = simulation.getTwoRandomStrategies();
            String winner = simulation.simulateMatch(players[0], players[1]);

            summary.recordResult(winner);
            CSVLogger.logGame(players[0], players[1], winner);
            CSVLogger.updateStrategyStats(winner);
        }

        summary.printSummary();
    }
}