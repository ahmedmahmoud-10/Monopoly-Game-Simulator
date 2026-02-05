package battleship; //tamer

import battleship.engine.GameEngine;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class GameEngineTest {
    @Test
    void testSimulationRunsWithoutError() {
        GameEngine engine = new GameEngine();
        assertDoesNotThrow(() -> engine.runSimulations(1));
    }
}
