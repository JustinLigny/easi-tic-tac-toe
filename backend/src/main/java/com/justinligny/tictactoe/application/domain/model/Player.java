package com.justinligny.tictactoe.application.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public final class Player {

    private final String name;

    private final PlayerSymbol symbol;

    public static Player createComputerPlayer(final Player humanPlayer) {
        return new Player("Computer", humanPlayer.symbol.getOppositeSymbol());
    }
}
