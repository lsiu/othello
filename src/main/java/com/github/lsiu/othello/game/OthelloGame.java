package com.github.lsiu.othello.game;

import com.github.lsiu.othello.game.move.CanPlayMakeAMoveUtils;
import com.github.lsiu.othello.game.move.CountPiecesUtils;
import com.github.lsiu.othello.game.move.TurnSurroundingPiecesUtils;
import lombok.Getter;

@Getter
public class OthelloGame {

    private final LocationConverter locationConverter = new LocationConverter();
    private OthelloBoard board;
    private LocationStatus turn;

    OthelloGame() {
        this(new OthelloBoard(), LocationStatus.DARK);
    }

    public OthelloGame(OthelloBoard board, LocationStatus turn) {
        this.board = board;
        this.turn = turn;
    }

    public void move(String move) {
        Location location = convertLocation(move);

        validateLocation(location);

        putPieceAtLocationIfPossible(location);

        if (CanPlayMakeAMoveUtils.canPlayerMakeAMove(turn.opposite(), board)) {
            turn = turn.opposite();
        } else {
            if (CanPlayMakeAMoveUtils.canPlayerMakeAMove(turn, board)) {
                throw new GameException(String.format("%s\n\nplayer %s cannot make a move, back to %s turn", board.toString(), turn.opposite(), turn));
            } else {
                handleGameEnd();
            }
        }
    }

    private void putPieceAtLocationIfPossible(Location location) {
        OthelloBoard othelloBoard = board.copy();
        boolean piecesTurned = TurnSurroundingPiecesUtils.turnSurroundingPieces(othelloBoard, location, turn);
        if (!piecesTurned) {
            throw new GameException("Invalid move. Please try again.");
        }
        othelloBoard.mark(location, turn);
        board = othelloBoard;
    }

    private void validateLocation(Location location) {
        if (board.get(location) != LocationStatus.EMPTY) {
            throw new GameException("Invalid move. Please try again.");
        }
    }

    private Location convertLocation(String move) {
        Location location;
        try {
            location = locationConverter.convert(move);
        } catch (Exception e) {
            throw new GameException("Invalid move. Please try again.");
        }
        return location;
    }

    private void handleGameEnd() {
        int xCount = CountPiecesUtils.count(board, LocationStatus.fromDisplayString("X"));
        int oCount = CountPiecesUtils.count(board, LocationStatus.fromDisplayString("O"));
        if (xCount > oCount) {
            throwGameWonException(LocationStatus.DARK, xCount, oCount, board);
        } else if (xCount == oCount) {
            throw new GameException(String.format("%s\n\n" +
                    "No further moves available\n" +
                    "Game tied ( %s vs %s )", board.toString(), xCount, oCount));
        } else {
            throwGameWonException(LocationStatus.LIGHT, oCount, xCount, board);
        }
    }

    private void throwGameWonException(LocationStatus winner, int winnerCount, int loserCount, OthelloBoard board) {
        throw new GameException(String.format("%s\n\n" +
                        "No further moves available\n" +
                        "Player '%s' wins ( %s vs %s )", board.toString(), winner,
                winnerCount, loserCount));
    }


}
