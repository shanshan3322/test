package test;

import java.util.ArrayList;

public class AutoPlay {
    private Player[] players;
    private Player lastPlayer = null;
    private static AutoPlay instance;
    public ArrayList<Location> remainingMoves = new ArrayList<>();

    public static AutoPlay getInstance() {
        if (instance == null)
            instance = new AutoPlay();
        return instance;
    }

    public void init(Player[] ps) {
        players = ps;
        lastPlayer = ps[1];
    }

    private AutoPlay() {
        for (int i = 1; i < 9; i++) {
            for (int j = 1; j < 9; j++) {
                Location loc = new Location(i, j);
                remainingMoves.add(loc);
            }
        }
    }

    public void change() {
        for (int i = 0; i < remainingMoves.size(); i++) {
            int t = randomIntInRange(i, remainingMoves.size() - 1);
            Location other = remainingMoves.get(t);
            Location current = remainingMoves.get(i);
            remainingMoves.set(t, current);
            remainingMoves.set(i, other);
        }
    }

    public boolean playRandom() {
        change();
        lastPlayer = lastPlayer == players[0] ? players[1] : players[0];
        for (int i = 0; i < remainingMoves.size(); i++) {
            Location loc = remainingMoves.get(i);
            boolean success = lastPlayer.playPiece(loc.getRow(), loc.getCol());
            if (success) {
                return true;
            }
        }
        return false;
    }

    public boolean isOver(){
        if (players[0].getScore() == 0 || players[1].getScore() == 0){
            return true;
        }
        return false;
    }

    public static int randomIntInRange(int min, int max) {
        return randomInt(max + 1 - min) + min;
    }

    public static int randomInt(int n) {
        return (int) (Math.random() * n);
    }

    private class Location {
        private int row;
        private int col;

        public Location(int r, int c) {
            row = r;
            col = c;
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }
    }
}