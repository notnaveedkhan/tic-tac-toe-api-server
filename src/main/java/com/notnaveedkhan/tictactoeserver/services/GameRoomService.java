package com.notnaveedkhan.tictactoeserver.services;

import com.notnaveedkhan.tictactoeserver.dtos.*;
import com.notnaveedkhan.tictactoeserver.entities.GameRoom;
import com.notnaveedkhan.tictactoeserver.entities.TicTacToe;
import com.notnaveedkhan.tictactoeserver.exceptions.AlreadyExistsException;
import com.notnaveedkhan.tictactoeserver.exceptions.BadRequestException;
import com.notnaveedkhan.tictactoeserver.exceptions.NotAuthorizedException;
import com.notnaveedkhan.tictactoeserver.exceptions.NotFoundException;
import com.notnaveedkhan.tictactoeserver.repositories.GameRoomRepository;
import com.notnaveedkhan.tictactoeserver.utils.ApplicationUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameRoomService {

    private final GameRoomRepository gameRoomRepository;
    private final TicTacToeService ticTacToeService;

    public GameRoomService(GameRoomRepository gameRoomRepository, TicTacToeService ticTacToeService) {
        this.gameRoomRepository = gameRoomRepository;
        this.ticTacToeService = ticTacToeService;
    }

    public GameRoomCreationResponse createGameRoom(GameRoomCreationRequest request) {
        GameRoom gameRoom = new GameRoom();
        gameRoom.setRoomName(request.getRoomName());
        gameRoom.setHostName(request.getName());
        gameRoom.setHostEmail(request.getEmail());
        gameRoom.setHostSymbol("X");
        gameRoom.setHostSessionKey(ApplicationUtils.getRandomStringOfLength(8));
        gameRoomRepository.save(gameRoom);
        return new GameRoomCreationResponse(gameRoom, true);
    }

    public GameRoomCreationResponse joinGameRoom(long gameRoomId, GameRoomCreationRequest request) {
        GameRoom gameRoom = gameRoomRepository.findById(gameRoomId);

        if (gameRoom == null) {
            throw new NotFoundException(String.format("Game room not found against id: %d ", gameRoomId));
        }

        if (gameRoom.getJoineeSessionKey() != null) {
            throw new AlreadyExistsException(String.format("Someone else(%s) has already joined this game room",
                    gameRoom.getJoineeName()));
        }

        if (
                gameRoom.getHostEmail().equals(request.getEmail())
                        ||
                gameRoom.getHostName().equals(request.getName())
        ) {

            throw new AlreadyExistsException(
                    String.format("Someone else(%s) has already joined this game room with same name/email",
                    gameRoom.getHostName()));
        }

        gameRoom.setJoineeName(request.getName());
        gameRoom.setJoineeEmail(request.getEmail());
        gameRoom.setJoineeSymbol("O");
        gameRoom.setJoineeSessionKey(ApplicationUtils.getRandomStringOfLength(8));
        gameRoomRepository.save(gameRoom);
        return new GameRoomCreationResponse(gameRoom, false);
    }

    public GameRoomStatusResponse getGameRoomByRoomId(long gameRoomId) {
        GameRoom gameRoom = gameRoomRepository.findById(gameRoomId);

        if (gameRoom == null) {
            throw new NotFoundException(String.format("Game room not found against id: %d ", gameRoomId));
        }

        return new GameRoomStatusResponse(gameRoom);
    }

    public GameRoomStatusResponse kickJoineeFromGameRoom(long gameRoomId, String key) {
        GameRoom gameRoom = gameRoomRepository.findById(gameRoomId);

        if (gameRoom == null) {
            throw new NotFoundException(String.format("Game room not found against id: %d ", gameRoomId));
        }

        if (!gameRoom.getHostSessionKey().equals(key)) {
            throw new NotAuthorizedException(
                    String.format("Unauthorized request to remove joinee from room id %d", gameRoomId));
        }

        if (gameRoom.isFinished()) {
            throw new BadRequestException("Can not remove joinee from room when game is finished");
        }

        gameRoom.setJoineeName(null);
        gameRoom.setJoineeEmail(null);
        gameRoom.setJoineeSymbol(null);
        gameRoom.setJoineeSessionKey(null);

        gameRoomRepository.save(gameRoom);

        return new GameRoomStatusResponse(gameRoom);
    }

    public List<GameRoomStatusResponse> getFreeGameRooms() {
        List<GameRoom> gameRooms = gameRoomRepository.findByFinishedAndJoineeSessionKeyIsNull(false);
        List<GameRoomStatusResponse> response = new ArrayList<>();
        for (GameRoom gameRoom: gameRooms) {
            response.add(new GameRoomStatusResponse(gameRoom));
        }
        return response;
    }

    public void deleteGameRoomById(long gameRoomId, String key) {
        GameRoom gameRoom = gameRoomRepository.findById(gameRoomId);

        if (gameRoom == null) {
            throw new NotFoundException(String.format("Game room not found against id: %d ", gameRoomId));
        }

        if (!gameRoom.getHostSessionKey().equals(key)) {
            throw new NotAuthorizedException(
                    String.format("Unauthorized request to delete game room by id %d", gameRoomId));
        }

        if (gameRoom.isFinished()) {
            throw new BadRequestException("Can not delete game room when it is finished");
        }

        gameRoomRepository.deleteById(gameRoomId);
    }

    public GameStatusResponse getStartGame(long gameRoomId, String key) {
        GameRoom gameRoom = gameRoomRepository.findById(gameRoomId);

        if (gameRoom == null) {
            throw new NotFoundException(String.format("Game room not found against id: %d ", gameRoomId));
        }

        if (!gameRoom.getHostSessionKey().equals(key)) {
            throw new NotAuthorizedException(
                    String.format("Unauthorized request to start the game by game room id %d", gameRoomId));
        }

        if (gameRoom.getJoineeSessionKey() == null) {
            throw new BadRequestException("Can not start game room without joinee");
        }

        if (gameRoom.isFinished()) {
            throw new BadRequestException("Can not start game room when it is finished");
        }

        if (gameRoom.getTicTacToe() != null) {
            throw new AlreadyExistsException("Game is already started");
        }

        TicTacToe ticTacToe = new TicTacToe();
        ticTacToe.setFinished(false);
        ticTacToe.setName(gameRoom.getJoineeName());
        ticTacToeService.saveTicTacToe(ticTacToe);

        gameRoom.setTicTacToe(ticTacToe);
        gameRoomRepository.save(gameRoom);

        GameRoomStatusResponse gameRoomStatusResponse = new GameRoomStatusResponse(gameRoom);
        TicTacToeResponse ticTacToeResponse = new TicTacToeResponse(ticTacToe);

        return new GameStatusResponse(gameRoomStatusResponse, ticTacToeResponse);
    }
}
