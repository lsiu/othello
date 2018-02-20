package com.github.lsiu.othello.game;

import com.github.lsiu.othello.game.move.TurnSurroundingPiecesUtils;
import lombok.Getter;

@Getter
public class OthelloGame {

    private final LocationConverter locationConverter = new LocationConverter();
    private OthelloBoard board;
    private LocationStatus turn;

    private OthelloGame() {
        board = new OthelloBoard();
        turn = LocationStatus.BLACK;
    }

    public static OthelloGame newGame() {
        return new OthelloGame();
    }

    public void move(String move) {
        Location location = locationConverter.convert(move);
        OthelloBoard othelloBoard = board.copy();
        boolean piecesTurned = TurnSurroundingPiecesUtils.turnSurroundingPieces(othelloBoard, location, turn);
        if (!piecesTurned) {
            throw new GameException(String.format("Player '%s' move: %s\nInvalid move. Please try again.", turn, move));
        }
        othelloBoard.mark(location, turn);
        board = othelloBoard;
        turn = turn.opposite();
    }


}
