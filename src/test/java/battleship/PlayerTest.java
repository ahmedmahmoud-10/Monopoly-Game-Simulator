package battleship; //ahmed ali

import battleship.player.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    @Test
    void testAllStrategiesPlaceShipsAndMakeMoves() { 
        Player[] strategies = {
                new RandomPlayer("Random"),
                new GreedyPlayer("Greedy"),
                new SmartPlayer("Smart"),
                new StrategicPlayer("Strategic"),
                new AggressivePlayer("Aggressive")
        };

        for (Player strategy : strategies) {
            assertDoesNotThrow(() -> {
                strategy.placeShips();
                int[] move = strategy.makeMove();
                assertTrue(move[0] >= 0 && move[0] < 10, "Row out of bounds: " + strategy.getName());
                assertTrue(move[1] >= 0 && move[1] < 10, "Col out of bounds: " + strategy.getName());
            }, "Failed with strategy: " + strategy.getName());
        }
    }
}
