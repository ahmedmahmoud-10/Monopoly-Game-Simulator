package battleship.player; //ahmed ali

import battleship.ship.*;

import java.util.*;

public class GreedyPlayer extends Player {
    private final Random random = new Random();
    private final Queue<int[]> targets = new LinkedList<>();// Queue to store potential target cells near a hit
    private final Set<String> previousMoves = new HashSet<>();

    public GreedyPlayer(String name) {
        super(name);
    }



    /**
     * Places all ships randomly on the board.
     * Keeps trying until each ship is placed successfully without overlap.
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
        int[] move;

        while (!targets.isEmpty()) {
            move = targets.poll();
            String coord = move[0] + "-" + move[1];
            if (previousMoves.add(coord)) {
                return move;
            }
        }

        // if target queue is empty or all are duplicates, go random
        int row, col;
        String coord;
        do {
            row = random.nextInt(10);
            col = random.nextInt(10);
            coord = row + "-" + col;
        } while (!previousMoves.add(coord));

        // After attacking, prepare to hit adjacent cells next
        addAdjacent(row, col);
        return new int[]{row, col};
    }

    private void addAdjacent(int r, int c) {
        int[][] dirs = {{-1,0}, {1,0}, {0,-1}, {0,1}};
        for (int[] d : dirs) {
            int nr = r + d[0];
            int nc = c + d[1];
            String newCoord = nr + "-" + nc;
            if (nr >= 0 && nr < 10 && nc >= 0 && nc < 10 && !previousMoves.contains(newCoord)) {
                targets.offer(new int[]{nr, nc});
            }
        }
    }
}
