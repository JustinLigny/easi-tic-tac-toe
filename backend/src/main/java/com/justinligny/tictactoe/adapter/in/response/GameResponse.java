package com.justinligny.tictactoe.adapter.in.response;

public record GameResponse(
        int winCondition,
        BoardResponse board,
        PlayerResponse humanPlayer,
        PlayerResponse computerPlayer,
        PlayerResponse currentPlayer,
        String status) {
}
