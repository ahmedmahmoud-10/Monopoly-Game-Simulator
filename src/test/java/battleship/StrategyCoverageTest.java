
package battleship; //ahmed ali

import battleship.player.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StrategyCoverageTest {

    @Test
    void testAllStrategiesRun() {
        Player[] players = {
                new RandomPlayer("Random"),
                new GreedyPlayer("Greedy"),
                new SmartPlayer("Smart"),
                new StrategicPlayer("Strategic"),
                new AggressivePlayer("Aggressive")
        };

        for (Player p : players) {
            p.placeShips();
            int[] move = p.makeMove();
            assertNotNull(move);
            assertEquals(2, move.length);
        }
    }
}
