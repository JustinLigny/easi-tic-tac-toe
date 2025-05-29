package com.justinligny.tictactoe.application.domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GameTest {

    private Game systemUnderTest;

    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player("Human", PlayerSymbol.X);
        systemUnderTest = Game.newGame(5, 3, player);
    }

    // ---- Success ----

    @Test
    void shouldReturnTrueWhenWinningMoveVertical() {
        systemUnderTest.getBoard().getCell(0, 0).setPlayer(player);
        systemUnderTest.getBoard().getCell(1, 0).setPlayer(player);
        systemUnderTest.getBoard().getCell(2, 0).setPlayer(player);

        var result = systemUnderTest.isWinningMove(2, 0);

        assertThat(result).isTrue();
    }

    @Test
    void shouldReturnTrueWhenWinningMoveHorizontal() {
        systemUnderTest.getBoard().getCell(3, 1).setPlayer(player);
        systemUnderTest.getBoard().getCell(3, 2).setPlayer(player);
        systemUnderTest.getBoard().getCell(3, 3).setPlayer(player);

        var result = systemUnderTest.isWinningMove(3, 3);

        assertThat(result).isTrue();
    }

    @Test
    void shouldReturnTrueWhenWinningMoveDiagonalDescending() {
        systemUnderTest.getBoard().getCell(0, 0).setPlayer(player);
        systemUnderTest.getBoard().getCell(1, 1).setPlayer(player);
        systemUnderTest.getBoard().getCell(2, 2).setPlayer(player);

        var result = systemUnderTest.isWinningMove(2, 2);

        assertThat(result).isTrue();
    }

    @Test
    void shouldReturnTrueWhenWinningMoveDiagonalAscending() {
        systemUnderTest.getBoard().getCell(2, 0).setPlayer(player);
        systemUnderTest.getBoard().getCell(1, 1).setPlayer(player);
        systemUnderTest.getBoard().getCell(0, 2).setPlayer(player);

        var result = systemUnderTest.isWinningMove(0, 2);

        assertThat(result).isTrue();
    }

    // ---- Fail - Only 2 pawns ----

    @Test
    void shouldReturnFalseWhenOnlyTwoVerticalPawns() {
        systemUnderTest.getBoard().getCell(1, 0).setPlayer(player);
        systemUnderTest.getBoard().getCell(2, 0).setPlayer(player);

        var result = systemUnderTest.isWinningMove(2, 0);

        assertThat(result).isFalse();
    }

    @Test
    void shouldReturnFalseWhenOnlyTwoHorizontalPawns() {
        systemUnderTest.getBoard().getCell(3, 2).setPlayer(player);
        systemUnderTest.getBoard().getCell(3, 3).setPlayer(player);

        var result = systemUnderTest.isWinningMove(3, 3);

        assertThat(result).isFalse();
    }

    @Test
    void shouldReturnFalseWhenOnlyTwoDiagonalDescPawns() {
        systemUnderTest.getBoard().getCell(1, 1).setPlayer(player);
        systemUnderTest.getBoard().getCell(2, 2).setPlayer(player);

        var result = systemUnderTest.isWinningMove(2, 2);

        assertThat(result).isFalse();
    }

    @Test
    void shouldReturnFalseWhenOnlyTwoDiagonalAscPawns() {
        systemUnderTest.getBoard().getCell(1, 1).setPlayer(player);
        systemUnderTest.getBoard().getCell(2, 0).setPlayer(player);

        var result = systemUnderTest.isWinningMove(2, 0);

        assertThat(result).isFalse();
    }

    // ---- Fail - Not consecutive pawns ----

    @Test
    void shouldReturnFalseWhenThreeVerticalPawnsNotConsecutive() {
        systemUnderTest.getBoard().getCell(0, 0).setPlayer(player);
        systemUnderTest.getBoard().getCell(2, 0).setPlayer(player);
        systemUnderTest.getBoard().getCell(4, 0).setPlayer(player);

        var result = systemUnderTest.isWinningMove(4, 0);

        assertThat(result).isFalse();
    }

    @Test
    void shouldReturnFalseWhenThreeHorizontalPawnsNotConsecutive() {
        systemUnderTest.getBoard().getCell(3, 0).setPlayer(player);
        systemUnderTest.getBoard().getCell(3, 2).setPlayer(player);
        systemUnderTest.getBoard().getCell(3, 4).setPlayer(player);

        var result = systemUnderTest.isWinningMove(3, 4);

        assertThat(result).isFalse();
    }

    @Test
    void shouldReturnFalseWhenThreeDiagonalDescPawnsNotConsecutive() {
        systemUnderTest.getBoard().getCell(0, 0).setPlayer(player);
        systemUnderTest.getBoard().getCell(2, 2).setPlayer(player);
        systemUnderTest.getBoard().getCell(4, 4).setPlayer(player);

        var result = systemUnderTest.isWinningMove(4, 4);

        assertThat(result).isFalse();
    }

    @Test
    void shouldReturnFalseWhenThreeDiagonalAscPawnsNotConsecutive() {
        systemUnderTest.getBoard().getCell(4, 0).setPlayer(player);
        systemUnderTest.getBoard().getCell(2, 2).setPlayer(player);
        systemUnderTest.getBoard().getCell(0, 4).setPlayer(player);

        var result = systemUnderTest.isWinningMove(0, 4);

        assertThat(result).isFalse();
    }

    // ---- restart() ----
    @Test
    void shouldClearBoardWhenGameIsRestarted() {
        systemUnderTest.restart();

        boolean allCellsEmpty = systemUnderTest.getBoard()
                .cellStream()
                .allMatch(cell -> cell.getPlayer().isEmpty());

        assertThat(allCellsEmpty).isTrue();
    }

    @Test
    void shouldResetCurrentPlayerToHumanWhenGameIsRestarted() {
        systemUnderTest.restart();

        assertThat(systemUnderTest.getCurrentPlayer()).isEqualTo(player);
    }

    @Test
    void shouldSetGameStatusToInProgressWhenGameIsRestarted() {
        systemUnderTest.restart();

        assertThat(systemUnderTest.getGameStatus()).isEqualTo(GameStatus.IN_PROGRESS);
    }
}
