package com.justinligny.tictactoe.application.domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CellTest {

    private Cell systemUnderTest;

    private Player player;

    @BeforeEach
    void setUp() {
        systemUnderTest = new Cell(1, 1);
        player = new Player("Human", PlayerSymbol.X);
    }

    @Test
    void shouldReturnEmptyOptionalWhenNoPlayerAssigned() {
        var result = systemUnderTest.getPlayer();

        assertThat(result).isEmpty();
    }

    @Test
    void shouldReturnOptionalWithPlayerWhenPlayerIsAssigned() {
        systemUnderTest.setPlayer(player);

        var result = systemUnderTest.getPlayer();

        assertThat(result).contains(player);
    }

    @Test
    void shouldReturnFalseWhenCellIsNotOccupied() {
        var result = systemUnderTest.isCellOccupied();

        assertThat(result).isFalse();
    }

    @Test
    void shouldReturnTrueWhenCellIsOccupied() {
        systemUnderTest.setPlayer(player);

        var result = systemUnderTest.isCellOccupied();

        assertThat(result).isTrue();
    }
}
