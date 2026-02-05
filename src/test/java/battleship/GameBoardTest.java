package battleship; //tamer

import battleship.board.Board;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class GameBoardTest {

    @Test
    void testInitialBoardEmpty() {
        Board board = new Board();
        assertFalse(board.attack(0, 0), "Attacking empty board should return false");
    }

    @Test
    void testBoardLoseCondition() {
        Board board = new Board();
        // Fix assumes board.hasLost() only returns true if ships exist AND are all sunk
        assertFalse(board.hasLost(), "Board should not be lost with no ships placed");
    }
}