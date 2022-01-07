package com.notnaveedkhan.tictactoeserver.controllers;

import com.notnaveedkhan.tictactoeserver.dtos.TicTacToeMovementRequest;
import com.notnaveedkhan.tictactoeserver.dtos.TicTacToeResponse;
import com.notnaveedkhan.tictactoeserver.services.TicTacToeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TicTacToeController {

    private final TicTacToeService ticTacToeService;

    public TicTacToeController(TicTacToeService ticTacToeService) {
        this.ticTacToeService = ticTacToeService;
    }

    @GetMapping("/game/status")
    public ResponseEntity<TicTacToeResponse> getGameStatus(@RequestParam long id) {
        TicTacToeResponse response = ticTacToeService.getTicTacToeById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/game/move")
    public ResponseEntity<TicTacToeResponse> playMove(@RequestBody TicTacToeMovementRequest request) {
        TicTacToeResponse response = ticTacToeService.playMove(request);
        return ResponseEntity.ok(response);
    }

}
