package com.justinligny.tictactoe.adapter.out;

import com.justinligny.tictactoe.application.domain.model.Game;
import com.justinligny.tictactoe.application.port.out.CreateGamePort;
import com.justinligny.tictactoe.application.port.out.LoadCurrentGamePort;
import com.justinligny.tictactoe.application.port.out.SaveGamePort;
import org.springframework.stereotype.Component;

@Component
class GameAdapter implements CreateGamePort, LoadCurrentGamePort, SaveGamePort {

    private Game currentGame;

    @Override
    public Game create(Game game) {
        return this.currentGame = game;
    }

    @Override
    public Game loadCurrentGame() {
        return currentGame;
    }

    @Override
    public Game save(Game game) {
        return this.currentGame = game;
    }
}
