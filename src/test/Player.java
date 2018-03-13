package test;

public class Player {
    private PieceType pieceType;

    public Player(PieceType c) {
        pieceType = c;
    }

    public int getScore() {
        return OthelloGame.getInstance().getBoard().getScoreForColor(pieceType);
    }

    public boolean playPiece(int row, int col) {
        return OthelloGame.getInstance().getBoard().placePiece(row, col, pieceType);
    }
}
