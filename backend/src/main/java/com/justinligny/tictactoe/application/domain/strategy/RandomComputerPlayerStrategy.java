package com.justinligny.tictactoe.application.domain.strategy;

import com.justinligny.tictactoe.application.domain.model.Cell;
import com.justinligny.tictactoe.application.domain.model.Game;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
class RandomComputerPlayerStrategy implements ComputerPlayerStrategy {

    private final Random random = new Random();

    @Override
    public Cell chooseCell(Game game) {
        int boardSize = game.getBoard().getSize();
        Cell cell;

        do {
            int row = random.nextInt(boardSize);
            int column = random.nextInt(boardSize);

            cell = game.getBoard().getCell(row, column);
        } while (cell == null || cell.isCellOccupied());

        return cell;
    }
}
