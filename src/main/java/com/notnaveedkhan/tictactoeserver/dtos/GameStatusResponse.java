package com.notnaveedkhan.tictactoeserver.dtos;

public class GameStatusResponse {

    private GameRoomStatusResponse room;
    private TicTacToeResponse game;

    public GameStatusResponse() { }

    public GameStatusResponse(GameRoomStatusResponse room, TicTacToeResponse game) {
        this.room = room;
        this.game = game;
    }

    public GameRoomStatusResponse getRoom() {
        return room;
    }

    public void setRoom(GameRoomStatusResponse room) {
        this.room = room;
    }

    public TicTacToeResponse getGame() {
        return game;
    }

    public void setGame(TicTacToeResponse game) {
        this.game = game;
    }
}
