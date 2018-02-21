package com.github.lsiu.othello.game;

import com.github.lsiu.othello.game.move.TurnSurroundingPiecesUtils;
import lombok.Getter;

@Getter
public class OthelloGame {

    private final LocationConverter locationConverter = new LocationConverter();
    private OthelloBoard board;
    private LocationStatus turn;

    public OthelloGame() {
        this(new OthelloBoard(), LocationStatus.BLACK);
    }

    public OthelloGame(OthelloBoard board, LocationStatus turn) {
        this.board = board;
        this.turn = turn;
    }

    public void move(String move) {
        Location location = locationConverter.convert(move);
        if (board.get(location) != LocationStatus.EMPTY) {
            throwExceptionOnInvalidMove(move);
            return;
        }

        OthelloBoard othelloBoard = board.copy();
        boolean piecesTurned = TurnSurroundingPiecesUtils.turnSurroundingPieces(othelloBoard, location, turn);
        if (!piecesTurned) {
            throwExceptionOnInvalidMove(move);
        }
        othelloBoard.mark(location, turn);
        board = othelloBoard;
        turn = turn.opposite();
    }

    private void throwExceptionOnInvalidMove(String move) {
        throw new GameException(String.format("Player '%s' move: %s\nInvalid move. Please try again.", turn, move));
    }


}
