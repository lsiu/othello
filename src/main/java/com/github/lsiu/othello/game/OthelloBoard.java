package com.github.lsiu.othello.game;

import org.springframework.stereotype.Component;

@Component
public class OthelloBoard {
    private LocationStatus[][] board = new LocationStatus[8][8];

    public void newGame() {
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
