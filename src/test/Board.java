package test;

public class Board {
    private int OPieceNum = 0;
    private int XPieceNum = 0;
    private Piece[][] board;

    public Board(int rows, int cols) {
        board = new Piece[rows][cols];
    }

    public void init() {
        int middleRow = board.length / 2;
        int middleCol = board[middleRow].length / 2;
        board[middleRow][middleCol] = new Piece(PieceType.O);
        board[middleRow + 1][middleCol] = new Piece(PieceType.X);
        board[middleRow + 1][middleCol + 1] = new Piece(PieceType.O);
        board[middleRow][middleCol + 1] = new Piece(PieceType.X);
        OPieceNum = 2;
        XPieceNum = 2;
    }

    public boolean placePiece(int row, int col, PieceType pieceType) {
        if (board[row][col] != null)
            return false;
        int[] results = new int[4];
        results[0] = flipSection(row - 1, col, pieceType, Direction.up);
        results[1] = flipSection(row + 1, col, pieceType, Direction.down);
        results[2] = flipSection(row, col - 1, pieceType, Direction.left);
        results[3] = flipSection(row, col + 1, pieceType, Direction.right);
        int flipped = 0;
        for (int result : results) {
            if (result > 0)
                flipped += result;
        }
        if (flipped < 0)
            return false;
        board[row][col] = new Piece(pieceType);
        updateScore(pieceType, flipped + 1);
        return true;
    }

    public int flipSection(int row, int col, PieceType pieceType, Direction d) {
        int r = 0, c = 0;
        switch (d) {
            case up:
                r = -1;
                break;
            case down:
                r = 1;
                break;
            case left:
                c = -1;
                break;
            case right:
                c = 1;
                break;
        }
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length || board[row][col] == null)
            return -1;
        if (board[row][col].getPieceType() == pieceType)
            return 0;
        int flipped = flipSection(row + r, col - c, pieceType, d);
        if (flipped < 0)
            return -1;
        board[row][col].flip();
        return flipped + 1;
    }

    public void updateScore(PieceType newPieceType, int newPieces) {
        if (newPieceType == PieceType.X) {
            XPieceNum += newPieces;
            OPieceNum -= newPieces - 1;
        } else {
            XPieceNum -= newPieces - 1;
            OPieceNum += newPieces;
        }
    }

    public int getScoreForColor(PieceType c) {
        if (c == PieceType.O)
            return OPieceNum;
        else
            return XPieceNum;
    }

    private class Piece {
        private PieceType pieceType;

        public Piece(PieceType c) {
            pieceType = c;
        }

        public void flip() {
            if (pieceType == PieceType.O) {
                pieceType = PieceType.X;
            } else
                pieceType = PieceType.O;
        }

        public PieceType getPieceType() {
            return pieceType;
        }
    }

    public enum Direction {
        left, right, up, down
    }

    public boolean isFull() {
        for (int i = 1; i < 9; i++) {
            for (int j = 1; j < 9; j++) {
                if (!(board[i][j].getPieceType().equals("X") || board[i][j].getPieceType().equals("O")))
                    return false;
            }
        }
        return true;
    }
}

