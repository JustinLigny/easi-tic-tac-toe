package com.justinligny.tictactoe.application.domain.model;

import jakarta.validation.constraints.Min;
import lombok.Getter;

import java.util.Arrays;
import java.util.stream.Stream;

@Getter
public final class Board {

    @Min(value = 3, message = "Size must be greater than or equal to 3")
    private final int size;

    private final Cell[][] cells;

    Board(final int size) {
        this.size = size;
        this.cells = new Cell[size][size];

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                this.cells[row][col] = new Cell(row, col);
            }
        }
    }

    public Cell getCell(final int row, final int col) {
        if (row > size || col > size)
            throw new IllegalArgumentException("Row or col out of bounds");
        return cells[row][col];
    }

    public Stream<Cell> cellStream() {
        return Arrays.stream(cells).flatMap(Arrays::stream);
    }

    public boolean isFull() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (cells[i][j].getPlayer().isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cells[i][j].setPlayer(null);
            }
        }
    }
}
