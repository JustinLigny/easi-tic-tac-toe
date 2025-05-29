package com.justinligny.tictactoe.application.domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoardTest {

    private Board systemUnderTest;

    @BeforeEach
    void setUp() {
        systemUnderTest = new Board(3);
    }

    // ---- getCell() ----

    @Test
    void shouldReturnCorrectCellWhenWithinBounds() {
        Cell cell = systemUnderTest.getCell(2, 1);

        assertThat(cell.getRow()).isEqualTo(2);
        assertThat(cell.getColumn()).isEqualTo(1);
    }

    @Test
    void shouldThrowExceptionWhenRowOutOfBounds() {
        assertThatThrownBy(() -> systemUnderTest.getCell(3, 0))
                .isInstanceOf(ArrayIndexOutOfBoundsException.class);
    }

    @Test
    void shouldThrowExceptionWhenColumnOutOfBounds() {
        assertThatThrownBy(() -> systemUnderTest.getCell(0, 3))
                .isInstanceOf(ArrayIndexOutOfBoundsException.class);
    }

    @Test
    void shouldNotThrowWhenCellIsAtEdge() {
        assertThatCode(() -> systemUnderTest.getCell(2, 2))
                .doesNotThrowAnyException();
    }

    // ----------- isFull() -----------

    @Test
    void shouldReturnFalseWhenBoardHasEmptyCells() {
        var result = systemUnderTest.isFull();

        assertThat(result).isFalse();
    }

    @Test
    void shouldReturnTrueWhenAllCellsAreFilled() {
        Player player = new Player("Test", PlayerSymbol.X);

        for (int i = 0; i < systemUnderTest.getSize(); i++) {
            for (int j = 0; j < systemUnderTest.getSize(); j++) {
                systemUnderTest.getCell(i, j).setPlayer(player);
            }
        }

        var result = systemUnderTest.isFull();

        assertThat(result).isTrue();
    }

    @Test
    void shouldReturnFalseWhenOnlyOneCellIsEmpty() {
        Player player = new Player("Test", PlayerSymbol.X);

        for (int i = 0; i < systemUnderTest.getSize(); i++) {
            for (int j = 0; j < systemUnderTest.getSize(); j++) {
                systemUnderTest.getCell(i, j).setPlayer(player);
            }
        }
        systemUnderTest.getCell(0, 0).setPlayer(null);

        var result = systemUnderTest.isFull();

        assertThat(result).isFalse();
    }
}
