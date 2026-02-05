package battleship.util; //ahmed ali

import java.io.*;
import java.util.*;

public class StrategyStatsTracker {
    private static final String FILE_PATH = "strategy_wins.csv";
    private static final Map<String, Integer> wins = new HashMap<>();
    private static int draws = 0;

    static {
        // Try loading existing stats from CSV
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line = reader.readLine(); // Skip header
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equalsIgnoreCase("Draws")) {
                    draws = Integer.parseInt(parts[1]);
                } else {
                    wins.put(parts[0], Integer.parseInt(parts[1]));
                }
            }
        } catch (IOException e) {
            System.out.println("No existing stats file found, starting fresh.");
        }
    }

    public static void incrementStat(String winner) {
        if (winner.equalsIgnoreCase("Draw")) {
            draws++;
        } else {
            wins.put(winner, wins.getOrDefault(winner, 0) + 1);
        }
        saveToFile();
    }

    private static void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) {
            writer.println("Strategy,Wins");
            for (Map.Entry<String, Integer> entry : wins.entrySet()) {
                writer.println(entry.getKey() + "," + entry.getValue());
            }
            writer.println("Draws," + draws);
        } catch (IOException e) {
            System.out.println("Failed to write strategy stats.");
        }
    }
}
