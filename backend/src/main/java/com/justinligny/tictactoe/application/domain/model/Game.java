package com.justinligny.tictactoe.application.domain.model;

import com.justinligny.tictactoe.common.validation.Validation;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

@Getter
public class Game {

    private final Board board;

    private final int winCondition;

    private final Player humanPlayer;

    private final Player computerPlayer;

    @Setter
    private Player currentPlayer;

    @Setter
    private GameStatus gameStatus;

    Game(final Board board,
         final int winCondition,
         final Player humanPlayer,
         final Player computerPlayer) {

        this.board = board;
        this.winCondition = winCondition;
        this.humanPlayer = humanPlayer;
        this.computerPlayer = computerPlayer;
        this.currentPlayer = humanPlayer;
        this.gameStatus = GameStatus.IN_PROGRESS;

        Validation.validate(this);
    }

    public static Game newGame(
            final int gridSize,
            final int winCondition,
            final Player human) {

        return new Game(
                new Board(gridSize),
                winCondition,
                human,
                Player.createComputerPlayer(human)
        );
    }

    public boolean isWinningMove(final int row, final int column) {
        final Player player = board.getCell(row, column).getPlayer().orElseThrow();

        return Arrays.stream(Direction.values())
                .anyMatch(direction -> countInDirection(row, column, player, direction) >= winCondition);
    }

    private int countInDirection(
            final int originRow,
            final int originColumn,
            final Player player,
            final Direction direction) {

        int count = 1;

        count += countConsecutive(originRow, originColumn, player, direction.getDRow(), direction.getDCol());
        count += countConsecutive(originRow, originColumn, player, -direction.getDRow(), -direction.getDCol());

        return count;
    }

    private int countConsecutive(
            final int originRow,
            final int originColumn,
            final Player player,
            final int dRow,
            final int dCol) {

        int count = 0;
        int boardSize = board.getSize();

        int currentRow = originRow + dRow;
        int currentColumn = originColumn + dCol;

        while (currentRow >= 0 && currentRow < boardSize && currentColumn >= 0 && currentColumn < boardSize) {
            var cell = board.getCell(currentRow, currentColumn);
            if (cell.getPlayer().isPresent() && cell.getPlayer().get().equals(player)) {
                count++;
                currentRow += dRow;
                currentColumn += dCol;
            } else {
                break;
            }
        }
        return count;
    }

    public void restart() {
        this.board.clear();
        this.currentPlayer = humanPlayer;
        this.gameStatus = GameStatus.IN_PROGRESS;
    }

    public boolean isNotInProgress() {
        return !this.gameStatus.equals(GameStatus.IN_PROGRESS);
    }
}
