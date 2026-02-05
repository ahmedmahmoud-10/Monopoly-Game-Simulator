package battleship.player; //ahmed ali

import battleship.ship.*;

import java.util.*;

public class RandomPlayer extends Player {
    private final Random random = new Random();
    private final Set<String> previousMoves = new HashSet<>();

    public RandomPlayer(String name) {
        super(name);
    }

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
        do {
            row = random.nextInt(10);
            col = random.nextInt(10);
            coord = row + "-" + col;
        } while (!previousMoves.add(coord)); // ensure move is unique

        return new int[]{row, col};
    }
}
