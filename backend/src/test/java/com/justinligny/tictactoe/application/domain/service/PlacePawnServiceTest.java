package com.justinligny.tictactoe.application.domain.service;

import com.justinligny.tictactoe.application.domain.model.Game;
import com.justinligny.tictactoe.application.domain.model.GameStatus;
import com.justinligny.tictactoe.application.domain.model.Player;
import com.justinligny.tictactoe.application.domain.model.PlayerSymbol;
import com.justinligny.tictactoe.application.domain.strategy.ComputerPlayerStrategy;
import com.justinligny.tictactoe.application.port.in.PlacePawnCommand;
import com.justinligny.tictactoe.application.port.out.LoadCurrentGamePort;
import com.justinligny.tictactoe.application.port.out.SaveGamePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class PlacePawnServiceTest {

    @Mock
    private LoadCurrentGamePort loadCurrentGamePort;
    @Mock
    private SaveGamePort saveGamePort;
    @Mock
    private ComputerPlayerStrategy computerPlayerStrategy;

    @InjectMocks
    private PlacePawnService systemUnderTest;

    private Game game;
    private Player human;
    private Player computer;

    @BeforeEach
    void setUp() {
        human = new Player("Alice", PlayerSymbol.X);
        game = Game.newGame(3, 3, human);
        computer = game.getComputerPlayer();

        when(loadCurrentGamePort.loadCurrentGame()).thenReturn(game);
        lenient().when(saveGamePort.save(any())).thenAnswer(invocation -> invocation.getArgument(0));
    }

    @Test
    void shouldReturnUnchangedGameWhenGameNotInProgress() {
        game.setGameStatus(GameStatus.DRAW);

        Game result = systemUnderTest.placePawn(new PlacePawnCommand(0, 0));

        verifyNoInteractions(computerPlayerStrategy);
        assertThat(result).isSameAs(game);
    }

    @Test
    void shouldThrowExceptionWhenCellIsAlreadyOccupied() {
        game.getBoard().getCell(0, 0).setPlayer(human);

        assertThatThrownBy(() -> systemUnderTest.placePawn(new PlacePawnCommand(0, 0)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Cell is not available");
    }

    @Test
    void shouldSetGameStatusToHumanWinWhenHumanWins() {
        game.getBoard().getCell(0, 0).setPlayer(human);
        game.getBoard().getCell(0, 1).setPlayer(human);

        Game result = systemUnderTest.placePawn(new PlacePawnCommand(0, 2));

        assertThat(result.getGameStatus()).isEqualTo(GameStatus.HUMAN_WIN);
        verifyNoInteractions(computerPlayerStrategy);
    }

    @Test
    void shouldSetGameStatusToDrawWhenBoardIsFullAfterHumanMove() {
        fillBoardExceptRow2Col2(game);
        Game result = systemUnderTest.placePawn(new PlacePawnCommand(2, 2));

        assertThat(result.getGameStatus()).isEqualTo(GameStatus.DRAW);
        verifyNoInteractions(computerPlayerStrategy);
    }

    @Test
    void shouldSetGameStatusToComputerWinWhenComputerWins() {
        game.getBoard().getCell(1, 0).setPlayer(computer);
        game.getBoard().getCell(1, 1).setPlayer(computer);

        when(computerPlayerStrategy.chooseCell(game))
                .thenReturn(game.getBoard().getCell(1, 2));

        Game result = systemUnderTest.placePawn(new PlacePawnCommand(0, 0));

        assertThat(result.getGameStatus()).isEqualTo(GameStatus.COMPUTER_WIN);
    }

    @Test
    void shouldSetGameStatusToDrawWhenBoardIsFullAfterComputerMove() {
        fillBoardExceptRow2Col2(game);

        Game result = systemUnderTest.placePawn(new PlacePawnCommand(2, 2));

        assertThat(result.getGameStatus()).isEqualTo(GameStatus.DRAW);
    }


    @Test
    void shouldPlayBothHumanAndComputerMovesWhenNoWinOrDraw() {
        when(computerPlayerStrategy.chooseCell(game)).thenReturn(game.getBoard().getCell(2, 2));

        Game result = systemUnderTest.placePawn(new PlacePawnCommand(0, 0));

        assertThat(result.getGameStatus()).isEqualTo(GameStatus.IN_PROGRESS);
        assertThat(game.getBoard().getCell(0, 0).getPlayer()).contains(human);
        assertThat(game.getBoard().getCell(2, 2).getPlayer()).contains(computer);
    }

    // Helper (Cell 2/2 always available)
    private void fillBoardExceptRow2Col2(Game game) {
        var board = game.getBoard();
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                if (i == 2 && j == 2) continue;
                var symbol = ((i + j) % 2 == 0) ? PlayerSymbol.X : PlayerSymbol.O;
                board.getCell(i, j).setPlayer(new Player("Filler", symbol));
            }
        }
    }
}
