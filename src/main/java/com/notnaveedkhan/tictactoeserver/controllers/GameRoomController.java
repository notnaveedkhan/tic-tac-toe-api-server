package com.notnaveedkhan.tictactoeserver.controllers;

import com.notnaveedkhan.tictactoeserver.dtos.GameRoomCreationRequest;
import com.notnaveedkhan.tictactoeserver.dtos.GameRoomCreationResponse;
import com.notnaveedkhan.tictactoeserver.dtos.GameRoomStatusResponse;
import com.notnaveedkhan.tictactoeserver.dtos.GameStatusResponse;
import com.notnaveedkhan.tictactoeserver.services.GameRoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GameRoomController {

    private final GameRoomService gameRoomService;

    public GameRoomController(GameRoomService gameRoomService) {
        this.gameRoomService = gameRoomService;
    }

    @PostMapping("/create/game-room")
    public ResponseEntity<GameRoomCreationResponse> createGameRoom(
            @RequestBody GameRoomCreationRequest request
            ) {

        GameRoomCreationResponse response = gameRoomService
                .createGameRoom(request);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/join/game-room")
    public ResponseEntity<GameRoomCreationResponse> joinGameRoom(
            @RequestParam long id,
            @RequestBody GameRoomCreationRequest request
    ) {

        GameRoomCreationResponse response = gameRoomService.joinGameRoom(id, request);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/details/game-room")
    public ResponseEntity<GameRoomStatusResponse> getGameRoomByRoomId(@RequestParam long id) {
        GameRoomStatusResponse response = gameRoomService.getGameRoomByRoomId(id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/game-room")
    public void deleteGameRoomById(@RequestParam long id, @RequestParam String key) {
        gameRoomService.deleteGameRoomById(id, key);
    }

    @PutMapping("/kick/game-room")
    public ResponseEntity<GameRoomStatusResponse> kickJoineeFromGameRoom(@RequestParam long id,
                                                                         @RequestParam String key) {

        GameRoomStatusResponse response = gameRoomService.kickJoineeFromGameRoom(id, key);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/free/game-room")
    public ResponseEntity<List<GameRoomStatusResponse>> getFreeGameRooms() {
        List<GameRoomStatusResponse> response = gameRoomService.getFreeGameRooms();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/start/game-room")
    public ResponseEntity<GameStatusResponse> startGame(@RequestParam long id,
                                                        @RequestParam String key) {
        GameStatusResponse response = gameRoomService.getStartGame(id, key);
        return ResponseEntity.ok(response);
    }

}
