package com.justinligny.tictactoe.application.port.out;

import com.justinligny.tictactoe.application.domain.model.Game;

@FunctionalInterface
public interface CreateGamePort {

    Game create(Game game);
}
