package battleship.player; // ahmed ali

import battleship.ship.*;

import java.util.*;

public class StrategicPlayer extends Player {
    private int row = 0, col = 0;
    private final Set<String> previousMoves = new HashSet<>();

    public StrategicPlayer(String name) {
        super(name);
    }

    public void placeShips() {
        this.fleet = List.of(new Carrier(), new Battleship(), new Destroyer(), new Submarine(), new PatrolBoat());
        Random rand = new Random();
        for (Ship ship : fleet) {
            boolean placed = false;
            while (!placed) {
                placed = board.placeShip(ship, rand.nextInt(10), rand.nextInt(10), rand.nextBoolean());
            }
        }
    }

    public int[] makeMove() {
        for (int attempts = 0; attempts < 100; attempts++) {
            int[] move = new int[]{row, col};
            String coord = row + "-" + col;

            col += 2;
            if (col >= 10) {
                col = (col + 1) % 10;
                row = (row + 1) % 10;
            }

            if (previousMoves.add(coord)) {
                return move;
            }
        }

        // Fallback if all moves fail (should not happen)
        Random rand = new Random();
        int r, c;
        String fallback;
        do {
            r = rand.nextInt(10);
            c = rand.nextInt(10);
            fallback = r + "-" + c;
        } while (!previousMoves.add(fallback));
        return new int[]{r, c};
    }
}
