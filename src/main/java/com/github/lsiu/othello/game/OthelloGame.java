package com.github.lsiu.othello.game;

import com.github.lsiu.othello.MoveDisplayUtils;
import com.github.lsiu.othello.game.move.CanPlayMakeAMoveUtils;
import com.github.lsiu.othello.game.move.CountPiecesUtils;
import com.github.lsiu.othello.game.move.TurnSurroundingPiecesUtils;
import lombok.Getter;

@Getter
public class OthelloGame {

    private final LocationConverter locationConverter = new LocationConverter();
    private OthelloBoard board;
    private LocationStatus turn;

    public OthelloGame() {
        this(new OthelloBoard(), LocationStatus.DARK);
    }

    public OthelloGame(OthelloBoard board, LocationStatus turn) {
        this.board = board;
        this.turn = turn;
    }

    public void move(String move) {
        Location location = convertLocation(move);

        validateLocation(move, location);

        putPieceAtLocationIfPossible(move, location);

        if (CanPlayMakeAMoveUtils.canPlayerMakeAMove(turn.opposite(), board)) {
            turn = turn.opposite();
        } else {
            if (CanPlayMakeAMoveUtils.canPlayerMakeAMove(turn, board)) {
                String moveOutput = MoveDisplayUtils.generateMoveOutput(move, turn, board);
                throw new GameException(String.format("%s\n\nplayer %s cannot make a move, back to %s turn", moveOutput, turn.opposite(), turn));
            } else {
                handleGameEnd(move);
            }
        }
    }

    private void putPieceAtLocationIfPossible(String move, Location location) {
        OthelloBoard othelloBoard = board.copy();
        boolean piecesTurned = TurnSurroundingPiecesUtils.turnSurroundingPieces(othelloBoard, location, turn);
        if (!piecesTurned) {
            MoveDisplayUtils.throwExceptionOnInvalidMove(move, turn);
        }
        othelloBoard.mark(location, turn);
        board = othelloBoard;
    }

    private void validateLocation(String move, Location location) {
        if (board.get(location) != LocationStatus.EMPTY) {
            MoveDisplayUtils.throwExceptionOnInvalidMove(move, turn);
        }
    }

    private Location convertLocation(String move) {
        Location location = null;
        try {
            location = locationConverter.convert(move);
        } catch (Exception e) {
            MoveDisplayUtils.throwExceptionOnInvalidMove(move, turn);
        }
        return location;
    }

    private void handleGameEnd(String move) {
        int xCount = CountPiecesUtils.count(board, LocationStatus.fromDisplayString("X"));
        int oCount = CountPiecesUtils.count(board, LocationStatus.fromDisplayString("O"));
        if (xCount > oCount) {
            MoveDisplayUtils.throwWinningMessage(LocationStatus.DARK, xCount, oCount, move, turn, board);
        } else if (xCount == oCount) {
            String moveOutput = MoveDisplayUtils.generateMoveOutput(move, turn, board);
            throw new GameException(String.format("%s\n\n" +
                    "No further moves available\n" +
                    "Game tied ( %s vs %s )", moveOutput, xCount, oCount));
        } else {
            MoveDisplayUtils.throwWinningMessage(LocationStatus.LIGHT, oCount, xCount, move, turn, board);
        }
    }


}
