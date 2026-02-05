package battleship.util; //ahmed ali

import battleship.simulation.MatchResult;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class CSVLogger {

    private static final String RESULTS_FILE = "game_results.csv";
    private static final String STATS_FILE = "strategy_wins.csv";
    private static final String MOVES_FILE = "detailed_game_log.csv";
    private static final String DETAIL_STATS_FILE = "game_stats.csv";

    private static final Map<String, Integer> strategyWins = new HashMap<>();
    private static int drawCount = 0;

    static {
        String[] headers = {
                "P1 Strategy,P2 Strategy,Winner",
                "Strategy,Wins,Draws",
                "Game #,Move Log",
                "Game #,P1 Strategy,P2 Strategy,Winner,Turns,P1 Hits,P2 Hits,P1 Ships Left,P2 Ships Left"
        };
        String[] files = {RESULTS_FILE, STATS_FILE, MOVES_FILE, DETAIL_STATS_FILE};

        // Initialize win count
        for (String s : new String[]{"Random", "Greedy", "Smart", "Strategic", "Aggressive"}) {
            strategyWins.put(s, 0);
        }

        // Set up headers
        for (int i = 0; i < files.length; i++) {
            ensureHeader(files[i], headers[i]);
        }
    }

    private static void ensureHeader(String filename, String header) {
        File file = new File(filename);
        if (!file.exists() || file.length() == 0) {
            try (PrintWriter writer = new PrintWriter(file)) {
                writer.println(header);
            } catch (IOException e) {
                System.err.println("Error initializing file: " + filename);
            }
        }
    }

    public static void logGame(String p1, String p2, String winner) {
        appendLine(RESULTS_FILE, String.join(",", p1, p2, winner));
    }

    public static void updateStrategyStats(String winner) {
        if ("Draw".equalsIgnoreCase(winner)) {
            drawCount++;
        } else {
            strategyWins.merge(winner, 1, Integer::sum);
        }
        saveStats();
    }

    private static void saveStats() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(STATS_FILE))) {
            writer.println("Strategy,Wins,Draws");
            for (var entry : strategyWins.entrySet()) {
                writer.printf("%s,%d,%d%n", entry.getKey(), entry.getValue(), drawCount);
            }
        } catch (IOException e) {
            System.err.println("Error saving strategy stats.");
        }
    }

    public static void logDetailedMoves(int gameNumber, MatchResult result) {
        for (String move : result.getMoveLog()) {
            appendLine(MOVES_FILE, "Game " + gameNumber + ",\"" + move + "\"");
        }
    }

    public static void logDetailedStats(int gameNumber, MatchResult result) {
        String line = String.join(",", String.valueOf(gameNumber),
                result.getPlayer1().getName(),
                result.getPlayer2().getName(),
                result.getWinner(),
                String.valueOf(result.getTotalTurns()),
                String.valueOf(result.getP1Hits()),
                String.valueOf(result.getP2Hits()),
                String.valueOf(result.getP1ShipsLeft()),
                String.valueOf(result.getP2ShipsLeft()));
        appendLine(DETAIL_STATS_FILE, line);
    }

    private static void appendLine(String filename, String content) {
        try (FileWriter writer = new FileWriter(filename, true)) {
            writer.write(content + "\n");
        } catch (IOException e) {
            System.err.println("Failed to write to " + filename);
        }
    }
}
