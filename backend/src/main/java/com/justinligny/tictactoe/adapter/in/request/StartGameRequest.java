package com.justinligny.tictactoe.adapter.in.request;

import com.justinligny.tictactoe.application.port.in.CreatePlayerCommand;
import com.justinligny.tictactoe.application.port.in.StartGameCommand;

public record StartGameRequest(int gridSize, int winCondition, CreatePlayerRequest player) {

    public StartGameCommand toCommand() {
        return new StartGameCommand(gridSize, winCondition, player.toCommand());
    }

    record CreatePlayerRequest(String name, String symbol) {

        public CreatePlayerCommand toCommand() {
            return new CreatePlayerCommand(name, symbol);
        }
    }
}
