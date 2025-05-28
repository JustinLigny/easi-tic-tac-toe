package com.justinligny.tictactoe.application.domain.service;

import com.justinligny.tictactoe.application.domain.model.Game;
import com.justinligny.tictactoe.application.domain.model.GameStatus;
import com.justinligny.tictactoe.application.domain.strategy.ComputerPlayerStrategy;
import com.justinligny.tictactoe.application.port.in.PlacePawnCommand;
import com.justinligny.tictactoe.application.port.in.PlacePawnUseCase;
import com.justinligny.tictactoe.application.port.out.LoadCurrentGamePort;
import com.justinligny.tictactoe.application.port.out.SaveGamePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class PlacePawnService implements PlacePawnUseCase {

    private final LoadCurrentGamePort loadCurrentGamePort;

    private final SaveGamePort saveGamePort;

    private final ComputerPlayerStrategy computerPlayerStrategy;

    @Override
    public Game placePawn(final PlacePawnCommand command) {
        final var game = loadCurrentGamePort.loadCurrentGame();

        if (game.isNotInProgress()) {
            return save(game);
        }

        handleHumanMove(game, command);
        if (game.isNotInProgress()) {
            return save(game);
        }

        handleComputerMove(game);
        return save(game);
    }

    private void handleHumanMove(final Game game, final PlacePawnCommand command) {
        final var cell = game.getBoard().getCell(command.row(), command.column());

        if (cell == null || cell.isCellOccupied()) {
            throw new IllegalArgumentException("Cell is not available");
        }

        cell.setPlayer(game.getHumanPlayer());

        if (game.isWinningMove(command.row(), command.column())) {
            game.setGameStatus(GameStatus.HUMAN_WIN);
        } else if (game.getBoard().isFull()) {
            game.setGameStatus(GameStatus.DRAW);
        }
    }

    private void handleComputerMove(final Game game) {
        final var cell = computerPlayerStrategy.chooseCell(game);
        cell.setPlayer(game.getComputerPlayer());

        final int row = cell.getRow();
        final int column = cell.getColumn();

        if (game.isWinningMove(row, column)) {
            game.setGameStatus(GameStatus.COMPUTER_WIN);
        } else if (game.getBoard().isFull()) {
            game.setGameStatus(GameStatus.DRAW);
        }
    }

    private Game save(final Game game) {
        return saveGamePort.save(game);
    }
}