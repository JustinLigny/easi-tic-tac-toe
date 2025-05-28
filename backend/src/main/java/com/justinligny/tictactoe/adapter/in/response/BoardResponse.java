package com.justinligny.tictactoe.adapter.in.response;

import java.util.List;

public record BoardResponse(int size, List<CellResponse> cells) {
}
