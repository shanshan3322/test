package test;

public class OthelloGame {
    private Player[] players;
    private Board board;
    private final int ROWS = 10;
    private final int COLS = 10;
    private static OthelloGame instance;

    private OthelloGame() {
        board = new Board(ROWS, COLS);
        players = new Player[2];
        players[0] = new Player(PieceType.X);
        players[1] = new Player(PieceType.O);

    }

    public static OthelloGame getInstance() {
        if (instance == null) {
            instance = new OthelloGame();
        }
        return instance;
    }

    public Board getBoard() {
        return board;
    }

    public Player[] getPlayers() {
        return players;
    }


    public boolean isOver() {
        if (board.isFull()) {
            return true;
        }
        return false;
    }
}
