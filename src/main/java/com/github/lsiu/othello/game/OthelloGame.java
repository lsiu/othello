package com.github.lsiu.othello.game;

import com.github.lsiu.othello.game.move.TurnSurroundingPiecesUtils;
import lombok.Getter;

@Getter
public class OthelloGame {

    private OthelloBoard board;
    private LocationStatus turn;

    private OthelloGame() {
        board = new OthelloBoard();
        turn = LocationStatus.BLACK;
    }

    public static OthelloGame newGame() {
        return new OthelloGame();
    }

    public void move(Location location) {
        OthelloBoard othelloBoard = board.copy();
        boolean piecesTurned = TurnSurroundingPiecesUtils.turnSurroundingPieces(othelloBoard, location, turn);
        if (!piecesTurned) {
            throw new GameException("Invalid move. Please try again.");
        }
        othelloBoard.mark(location, turn);
        board = othelloBoard;
        turn = turn.opposite();
    }


}
