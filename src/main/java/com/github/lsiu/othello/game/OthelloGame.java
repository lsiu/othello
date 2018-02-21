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
                    throwWinningMessage(LocationStatus.DARK, xCount, oCount, move);
                } else if (xCount == oCount) {
                    String moveOutput = MoveDisplayUtils.generateMoveOutput(move, turn, board);
                    throw new GameException(String.format("%s\n\n" +
                            "No further moves available\n" +
                            "Game tied ( %s vs %s )", moveOutput, xCount, oCount));
                } else {
                    throwWinningMessage(LocationStatus.LIGHT, oCount, xCount, move);
                }
            }
        }
    }

    private void throwWinningMessage(LocationStatus winner, int winnerCount, int loserCount, String move) {
        String moveOutput = MoveDisplayUtils.generateMoveOutput(move, turn, board);
        throw new GameException(String.format("%s\n\n" +
                        "No further moves available\n" +
                        "Player '%s' wins ( %s vs %s )", moveOutput, winner,
                winnerCount, loserCount));
    }

    private LocationStatus determineWinner(int xCount, int oCount) {
        if (xCount > oCount) {
            return LocationStatus.DARK;
        } else if (xCount == oCount) {
            return null;
        } else {
            return LocationStatus.LIGHT;
        }
    }

    private void throwExceptionOnInvalidMove(String move) {
        throw new GameException(String.format("Player '%s' move: %s\nInvalid move. Please try again.", turn, move));
    }


}
