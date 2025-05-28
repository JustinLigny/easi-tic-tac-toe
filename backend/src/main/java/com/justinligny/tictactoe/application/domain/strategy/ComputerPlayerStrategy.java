package com.justinligny.tictactoe.application.domain.strategy;

import com.justinligny.tictactoe.application.domain.model.Cell;
import com.justinligny.tictactoe.application.domain.model.Game;

@FunctionalInterface
public interface ComputerPlayerStrategy {

    Cell chooseCell(Game game);
}
