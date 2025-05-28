package com.justinligny.tictactoe.application.port.out;

import com.justinligny.tictactoe.application.domain.model.Game;

@FunctionalInterface
public interface SaveGamePort {

    Game save(Game game);
}
