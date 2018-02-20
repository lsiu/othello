package com.github.lsiu.othello.game;

import org.springframework.stereotype.Component;

@Component
public class OthelloBoard {
    public static final int WIDTH = 8;
    public static final int HEIGHT = 8;
    private LocationStatus[][] board = new LocationStatus[HEIGHT][WIDTH];

    OthelloBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = LocationStatus.EMPTY;
            }
        }
        board[3][3] = LocationStatus.WHITE;
        board[4][4] = LocationStatus.WHITE;
        board[3][4] = LocationStatus.BLACK;
        board[4][3] = LocationStatus.BLACK;
    }

    public void mark(Location location, LocationStatus status) {
        board[location.getRow()][location.getCol()] = status;
    }

    OthelloBoard copy() {
        OthelloBoard othelloBoard = new OthelloBoard();
        for (int i = 0; i < board.length; i++) {
            System.arraycopy(board[i], 0, othelloBoard.board[i], 0, board[i].length);
        }
        return othelloBoard;
    }

    public LocationStatus get(int col, int row) {
        return board[row][col];
    }

    public LocationStatus get(Location location) {
        return get(location.getCol(), location.getRow());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < board.length; i++) {
            int row = i + 1;
            sb.append(row).append(" ");
            for (int j = 0; j < board[i].length; j++) {
                sb.append(board[i][j].toString());
            }
            sb.append("\n");
        }
        sb.append("  abcdefgh");
        return sb.toString();
    }
}
