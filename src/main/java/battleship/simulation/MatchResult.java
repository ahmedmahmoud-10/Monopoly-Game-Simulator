package battleship.simulation; //ahmed ali

import battleship.player.Player;
import java.util.List;

public class MatchResult {
    private final Player player1;
    private final Player player2;
    private final String winner;
    private final int totalTurns;
    private final int p1Hits;
    private final int p2Hits;
    private final int p1ShipsLeft;
    private final int p2ShipsLeft;
    private final List<String> moveLog;

    public MatchResult(Player player1, Player player2, String winner, int totalTurns,
                       int p1Hits, int p2Hits, int p1ShipsLeft, int p2ShipsLeft, List<String> moveLog) {
        this.player1 = player1;
        this.player2 = player2;
        this.winner = winner;
        this.totalTurns = totalTurns;
        this.p1Hits = p1Hits;
        this.p2Hits = p2Hits;
        this.p1ShipsLeft = p1ShipsLeft;
        this.p2ShipsLeft = p2ShipsLeft;
        this.moveLog = moveLog;
    }

    public Player getPlayer1() { return player1; }
    public Player getPlayer2() { return player2; }
    public String getWinner() { return winner; }
    public int getTotalTurns() { return totalTurns; }
    public int getP1Hits() { return p1Hits; }
    public int getP2Hits() { return p2Hits; }
    public int getP1ShipsLeft() { return p1ShipsLeft; }
    public int getP2ShipsLeft() { return p2ShipsLeft; }
    public List<String> getMoveLog() { return moveLog; }
}
