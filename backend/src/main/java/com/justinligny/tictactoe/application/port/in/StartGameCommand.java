package com.justinligny.tictactoe.application.port.in;

public record StartGameCommand(
        int gridSize,
        int winCondition,
        CreatePlayerCommand createPlayerCommand) {
}
