package com.justinligny.tictactoe.application.domain.service;

import com.justinligny.tictactoe.application.domain.model.Game;
import com.justinligny.tictactoe.application.domain.model.Player;
import com.justinligny.tictactoe.application.domain.model.PlayerSymbol;
import com.justinligny.tictactoe.application.port.in.CreatePlayerCommand;
import com.justinligny.tictactoe.application.port.in.StartGameCommand;
import com.justinligny.tictactoe.application.port.out.CreateGamePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class StartGameServiceTest {

    @Mock
    private CreateGamePort createGamePort;

    @InjectMocks
    private StartGameService systemUnderTest;

    private StartGameCommand command;
    
    private Game expectedGame;

    @BeforeEach
    void setUp() {
        command = new StartGameCommand(
                5,
                3,
                new CreatePlayerCommand("Jane", "X")
        );

        expectedGame = Game.newGame(
                5,
                3,
                new Player("Jane", PlayerSymbol.X)
        );

        when(createGamePort.create(any(Game.class))).thenReturn(expectedGame);
    }

    @Test
    void shouldCreateNewGameFromCommandAndReturnIt() {
        var result = systemUnderTest.startGame(command);

        verify(createGamePort).create(any(Game.class));

        assertThat(result).isEqualTo(expectedGame);
    }
}
