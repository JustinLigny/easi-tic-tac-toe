package com.justinligny.tictactoe.application.domain.service;

import com.justinligny.tictactoe.application.domain.model.Game;
import com.justinligny.tictactoe.application.port.in.GetCurrentGameUseCase;
import com.justinligny.tictactoe.application.port.out.LoadCurrentGamePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class GetCurrentGameService implements GetCurrentGameUseCase {

    private final LoadCurrentGamePort loadCurrentGamePort;

    @Override
    public Game getCurrentGame() {
        return loadCurrentGamePort.loadCurrentGame();
    }
}
