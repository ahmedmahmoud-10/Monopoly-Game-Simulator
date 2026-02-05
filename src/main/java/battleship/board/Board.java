package battleship.board; //ahmed

import battleship.ship.Ship;

import java.util.*;

public class Board {
    private final char[][] grid = new char[10][10]; //10 x 10 game board
    private final List<Ship> ships = new ArrayList<>();

    public Board() {
        for (char[] row : grid)
            Arrays.fill(row, '~');
    }

    public boolean placeShip(Ship ship, int row, int col, boolean horiz) {
        List<String> coords = new ArrayList<>();

        // Check if the ship would go out of bounds
        if ((horiz && col + ship.getSize() > 10) || (!horiz && row + ship.getSize() > 10)) return false;

        for (int i = 0; i < ship.getSize(); i++) {
            int r = horiz ? row : row + i;
            int c = horiz ? col + i : col;
            if (grid[r][c] != '~') return false;
            coords.add(r + "-" + c);
        }

        // Mark ship position on the board and store its coordinates
        for (String coord : coords) {
            String[] split = coord.split("-");
            grid[Integer.parseInt(split[0])][Integer.parseInt(split[1])] = 'S';
        }
        ship.setCoordinates(coords);
        ships.add(ship);
        return true;
    }

    public boolean attack(int row, int col) {
        String coord = row + "-" + col;
        // Hit detected
        if (grid[row][col] == 'S') {
            grid[row][col] = 'X';

            // Find which ship was hit and register the hit
            for (Ship ship : ships) {
                if (ship.getCoordinates().contains(coord)) {
                    ship.hitAt(coord);
                    break;
                }
            }

            return true;
        }
        // Missed attack
        if (grid[row][col] == '~') {
            grid[row][col] = 'O';
        }
        return false;
    }

    public boolean hasLost() {
        // If there are no ships, player hasn't lost
        return !ships.isEmpty() && ships.stream().allMatch(Ship::isSunk);
    }
}
