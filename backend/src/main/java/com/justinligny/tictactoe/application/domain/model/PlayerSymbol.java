package com.justinligny.tictactoe.application.domain.model;

public enum PlayerSymbol {

    X,

    O;

    public PlayerSymbol getOppositeSymbol() {
        return this == X ? O : X;
    }
}
