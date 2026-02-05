package battleship.simulation; //ahmed ali 

import java.util.List;

public class SimulationInDetail {
    private final String player1;
    private final String player2;
    private final String winner;
    private final int totalTurns;
    private final int p1Hits;
    private final int p2Hits;
    private final int p1Misses;
    private final int p2Misses;
    private final List<String> moveLog;

    public SimulationInDetail(String p1, String p2, String winner, int turns, int p1Hits, int p2Hits, int p1Misses, int p2Misses, List<String> log) {
        this.player1 = p1;
        this.player2 = p2;
        this.winner = winner;
        this.totalTurns = turns;
        this.p1Hits = p1Hits;
        this.p2Hits = p2Hits;
        this.p1Misses = p1Misses;
        this.p2Misses = p2Misses;
        this.moveLog = log;
    }

    public String getPlayer1() { return player1; }
    public String getPlayer2() { return player2; }
    public String getWinner() { return winner; }
    public int getTotalTurns() { return totalTurns; }
    public int getP1Hits() { return p1Hits; }
    public int getP2Hits() { return p2Hits; }
    public int getP1Misses() { return p1Misses; }
    public int getP2Misses() { return p2Misses; }
    public List<String> getMoveLog() { return moveLog; }

    @Override
    public String toString() {
        return player1 + " vs " + player2 + " => Winner: " + winner;
    }
}
