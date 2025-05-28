package com.justinligny.tictactoe.application.domain.service;

import com.justinligny.tictactoe.application.domain.model.Game;
import com.justinligny.tictactoe.application.domain.model.Player;
import com.justinligny.tictactoe.application.domain.model.PlayerSymbol;
import com.justinligny.tictactoe.application.port.in.StartGameCommand;
import com.justinligny.tictactoe.application.port.in.StartGameUseCase;
import com.justinligny.tictactoe.application.port.out.CreateGamePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class StartGameService implements StartGameUseCase {

    private final CreateGamePort createGamePort;

    @Override
    public Game startGame(StartGameCommand startGameCommand) {
        var game = Game.newGame(
                startGameCommand.gridSize(),
                startGameCommand.winCondition(),
                new Player(
                        startGameCommand.createPlayerCommand().name(),
                        PlayerSymbol.valueOf(startGameCommand.createPlayerCommand().symbol())
                )
        );

        return createGamePort.create(game);
    }
}
