package battleship.player; //ahmed

import battleship.board.Board;
import battleship.ship.Ship;

import java.util.List;

public interface PlayerInterface {
    void placeShips();
    int[] makeMove();
    String getName();
    Board getBoard();
    List<Ship> getFleet();
}
