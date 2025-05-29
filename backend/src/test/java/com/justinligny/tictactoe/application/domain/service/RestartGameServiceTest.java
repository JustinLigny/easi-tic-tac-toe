package com.justinligny.tictactoe.application.domain.service;

import com.justinligny.tictactoe.application.domain.model.Game;
import com.justinligny.tictactoe.application.port.out.LoadCurrentGamePort;
import com.justinligny.tictactoe.application.port.out.SaveGamePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class RestartGameServiceTest {

    @Mock
    private LoadCurrentGamePort loadCurrentGamePort;

    @Mock
    private SaveGamePort saveGamePort;

    @InjectMocks
    private RestartGameService systemUnderTest;

    @Mock
    private Game game;

    @BeforeEach
    void setUp() {
        when(loadCurrentGamePort.loadCurrentGame()).thenReturn(game);
        when(saveGamePort.save(game)).thenReturn(game);
    }

    @Test
    void shouldRestartAndSaveGame() {
        var result = systemUnderTest.restartGame();

        verify(loadCurrentGamePort).loadCurrentGame();
        verify(game).restart();
        verify(saveGamePort).save(game);

        assertThat(result).isSameAs(game);
    }
}
