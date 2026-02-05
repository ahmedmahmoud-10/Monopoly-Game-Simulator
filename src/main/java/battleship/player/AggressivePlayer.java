package battleship.player; //ahmed ali

import battleship.ship.*;

import java.util.*;

public class AggressivePlayer extends Player {
    private final Random random = new Random();
    private final Set<String> previousMoves = new HashSet<>();

    // Constructor that sets the strategy name
    public AggressivePlayer(String name) {
        super(name);
    }

    /**
     * Places all ships randomly on the board.
     * Ensures each ship is placed in a valid, non-overlapping location.
     */

    public void placeShips() {
        this.fleet = List.of(new Carrier(), new Battleship(), new Destroyer(), new Submarine(), new PatrolBoat());
        for (Ship ship : fleet) {
            boolean placed = false;
            while (!placed) {
                placed = board.placeShip(ship, random.nextInt(10), random.nextInt(10), random.nextBoolean());
            }
        }
    }

    public int[] makeMove() {
        int row, col;
        String coord;

        // prefer 0-4 aggressive zone
        for (int i = 0; i < 100; i++) {
            row = random.nextInt(5);
            col = random.nextInt(5);
            coord = row + "-" + col;
            if (previousMoves.add(coord)) {
                return new int[]{row, col};
            }
        }

        // fallback to full board
        do {
            row = random.nextInt(10);
            col = random.nextInt(10);
            coord = row + "-" + col;
        } while (!previousMoves.add(coord)); // Keep picking until a new coordinate is found

        return new int[]{row, col};
    }
}
