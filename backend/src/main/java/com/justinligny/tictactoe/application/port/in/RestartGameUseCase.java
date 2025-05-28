package com.justinligny.tictactoe.application.port.in;

import com.justinligny.tictactoe.application.domain.model.Game;

@FunctionalInterface
public interface RestartGameUseCase {

    Game restartGame();
}
