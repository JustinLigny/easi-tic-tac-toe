package com.justinligny.tictactoe.application.domain.service;

import com.justinligny.tictactoe.application.domain.model.Game;
import com.justinligny.tictactoe.application.domain.model.Player;
import com.justinligny.tictactoe.application.domain.model.PlayerSymbol;
import com.justinligny.tictactoe.application.port.out.LoadCurrentGamePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class GetCurrentGameServiceTest {

    @Mock
    private LoadCurrentGamePort loadCurrentGamePort;

    @InjectMocks
    private GetCurrentGameService systemUnderTest;

    private Game expectedGame;

    @BeforeEach
    void setUp() {
        expectedGame = Game.newGame(
                3,
                3,
                new Player("Jane", PlayerSymbol.X)
        );

        when(loadCurrentGamePort.loadCurrentGame()).thenReturn(expectedGame);
    }

    @Test
    void shouldReturnCurrentGameFromPort() {
        var result = systemUnderTest.getCurrentGame();

        verify(loadCurrentGamePort).loadCurrentGame();

        assertThat(result).isEqualTo(expectedGame);
    }
}
