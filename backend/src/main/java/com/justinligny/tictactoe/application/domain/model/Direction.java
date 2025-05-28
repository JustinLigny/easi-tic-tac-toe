package com.justinligny.tictactoe.application.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Direction {
    HORIZONTAL(0, 1),

    VERTICAL(1, 0),

    DIAGONAL_DESC(1, 1),

    DIAGONAL_ASC(1, -1);

    private final int dRow;

    private final int dCol;
}
