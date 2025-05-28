package com.justinligny.tictactoe.application.domain.service;

import com.justinligny.tictactoe.application.domain.model.Game;
import com.justinligny.tictactoe.application.port.in.RestartGameUseCase;
import com.justinligny.tictactoe.application.port.out.LoadCurrentGamePort;
import com.justinligny.tictactoe.application.port.out.SaveGamePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class RestartGameService implements RestartGameUseCase {

    private final LoadCurrentGamePort loadCurrentGamePort;

    private final SaveGamePort saveGamePort;

    @Override
    public Game restartGame() {
        var game = loadCurrentGamePort.loadCurrentGame();

        game.restart();

        return saveGamePort.save(game);
    }
}
