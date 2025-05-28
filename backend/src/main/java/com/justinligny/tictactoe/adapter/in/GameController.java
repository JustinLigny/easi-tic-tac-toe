package com.justinligny.tictactoe.adapter.in;

import com.justinligny.tictactoe.adapter.in.request.PlacePawnRequest;
import com.justinligny.tictactoe.adapter.in.request.StartGameRequest;
import com.justinligny.tictactoe.adapter.in.response.BoardResponse;
import com.justinligny.tictactoe.adapter.in.response.CellResponse;
import com.justinligny.tictactoe.adapter.in.response.GameResponse;
import com.justinligny.tictactoe.adapter.in.response.PlayerResponse;
import com.justinligny.tictactoe.application.domain.model.Board;
import com.justinligny.tictactoe.application.domain.model.Cell;
import com.justinligny.tictactoe.application.domain.model.Game;
import com.justinligny.tictactoe.application.domain.model.Player;
import com.justinligny.tictactoe.application.port.in.GetCurrentGameUseCase;
import com.justinligny.tictactoe.application.port.in.PlacePawnUseCase;
import com.justinligny.tictactoe.application.port.in.RestartGameUseCase;
import com.justinligny.tictactoe.application.port.in.StartGameUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "api/v1/game")
@RequiredArgsConstructor
class GameController {

    private final StartGameUseCase startGameUseCase;

    private final PlacePawnUseCase placePawnUseCase;

    private final GetCurrentGameUseCase getCurrentGameUseCase;

    private final RestartGameUseCase restartGameUseCase;

    @PostMapping(path = "start")
    ResponseEntity<GameResponse> startGame(@RequestBody final StartGameRequest startGameRequest) {
        var game = startGameUseCase.startGame(startGameRequest.toCommand());

        var response = toResponse(game);

        return ResponseEntity.ok().body(response);
    }

    @PostMapping(path = "place-pawn")
    ResponseEntity<GameResponse> placePawn(@RequestBody final PlacePawnRequest placePawnRequest) {
        var game = placePawnUseCase.placePawn(placePawnRequest.toCommand());

        var response = toResponse(game);

        return ResponseEntity.ok().body(response);
    }

    @GetMapping
    ResponseEntity<GameResponse> getCurrentGame() {
        var game = getCurrentGameUseCase.getCurrentGame();

        var response = toResponse(game);

        return ResponseEntity.ok().body(response);
    }

    @PutMapping(path = "restart")
    ResponseEntity<GameResponse> restartGame() {
        var game = restartGameUseCase.restartGame();

        var response = toResponse(game);

        return ResponseEntity.ok().body(response);
    }

    // ---- Utils for mapping domain into response ----

    private GameResponse toResponse(Game game) {
        return new GameResponse(
                game.getWinCondition(),
                toResponse(game.getBoard()),
                toResponse(game.getHumanPlayer()),
                toResponse(game.getComputerPlayer()),
                toResponse(game.getCurrentPlayer()),
                game.getGameStatus().name()
        );
    }

    private PlayerResponse toResponse(Player player) {
        return new PlayerResponse(
                player.getName(),
                player.getSymbol().name()
        );
    }

    // Surcharge pour Optional<Player>
    private PlayerResponse toResponse(Optional<Player> optionalPlayer) {
        return optionalPlayer
                .map(this::toResponse)
                .orElse(null);
    }

    private BoardResponse toResponse(Board board) {
        return new BoardResponse(
                board.getSize(),
                board.cellStream()
                        .map(this::toResponse)
                        .collect(Collectors.toList())
        );
    }

    private CellResponse toResponse(Cell cell) {
        return new CellResponse(
                cell.getRow(),
                cell.getColumn(),
                toResponse(cell.getPlayer())
        );
    }
}
