package battleship.player; //ahmed

import battleship.board.Board;
import battleship.ship.Ship;

import java.util.List;

public abstract class Player implements PlayerInterface {
    protected String name;
    protected Board board;
    protected List<Ship> fleet;

    public Player(String name) {
        this.name = name;
        this.board = new Board();
    }

    public Board getBoard() {
        return board;
    }

    public String getName() {
        return name;
    }

    public List<Ship> getFleet() {
        if (fleet == null || fleet.isEmpty()) {
            throw new IllegalStateException("Fleet not initialized for player: " + name);
        }
        return fleet;
    }
}