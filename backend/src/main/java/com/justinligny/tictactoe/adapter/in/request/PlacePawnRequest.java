package com.justinligny.tictactoe.adapter.in.request;

import com.justinligny.tictactoe.application.port.in.PlacePawnCommand;

public record PlacePawnRequest(int row, int column) {

    public PlacePawnCommand toCommand() {
        return new PlacePawnCommand(row, column);
    }
}
