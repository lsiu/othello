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
        Location location = locationConverter.convert(move);
        if (board.get(location) != LocationStatus.EMPTY) {
            MoveDisplayUtils.throwExceptionOnInvalidMove(move, turn);
            return;
        }

        OthelloBoard othelloBoard = board.copy();
        boolean piecesTurned = TurnSurroundingPiecesUtils.turnSurroundingPieces(othelloBoard, location, turn);
        if (!piecesTurned) {
            MoveDisplayUtils.throwExceptionOnInvalidMove(move, turn);
        }
        othelloBoard.mark(location, turn);
        board = othelloBoard;
        if (CanPlayMakeAMoveUtils.canPlayerMakeAMove(turn.opposite(), board)) {
            turn = turn.opposite();
        } else {
            if (CanPlayMakeAMoveUtils.canPlayerMakeAMove(turn, board)) {
                String moveOutput = MoveDisplayUtils.generateMoveOutput(move, turn, board);
                throw new GameException(String.format("%s\n\nplayer %s cannot make a move, back to %s turn", moveOutput, turn.opposite(), turn));
            } else {
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
    }


}
