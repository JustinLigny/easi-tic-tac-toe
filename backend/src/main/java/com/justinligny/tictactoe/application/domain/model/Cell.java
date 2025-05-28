package com.justinligny.tictactoe.application.domain.model;

import jakarta.validation.constraints.PositiveOrZero;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cell {

    @EqualsAndHashCode.Include
    @PositiveOrZero(message = "Row cannot be negative")
    private final int row;

    @EqualsAndHashCode.Include
    @PositiveOrZero(message = "Column cannot be negative")
    private final int column;

    @Setter
    private Player player;

    Cell(final int row, final int column) {
        this.row = row;
        this.column = column;
        this.player = null;
    }

    public Optional<Player> getPlayer() {
        return Optional.ofNullable(player);
    }

    public boolean isCellOccupied() {
        return getPlayer().isPresent();
    }
}
