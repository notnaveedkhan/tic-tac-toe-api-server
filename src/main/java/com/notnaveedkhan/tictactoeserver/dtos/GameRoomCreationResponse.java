package com.notnaveedkhan.tictactoeserver.dtos;

import com.notnaveedkhan.tictactoeserver.entities.GameRoom;

public class GameRoomCreationResponse {

    private long gameRoomId;
    private String roomName;
    private String name;
    private String email;
    private String sessionKey;
    private String symbol;

    public GameRoomCreationResponse() { }

    public GameRoomCreationResponse(GameRoom gameRoom, boolean isHost) {
        this.gameRoomId = gameRoom.getId();
        this.roomName = gameRoom.getRoomName();

        if (isHost) {
            this.name = gameRoom.getHostName();
            this.email = gameRoom.getHostEmail();
            this.sessionKey = gameRoom.getHostSessionKey();
            this.symbol = gameRoom.getHostSymbol();
        } else {
            this.name = gameRoom.getJoineeName();
            this.email = gameRoom.getJoineeEmail();
            this.sessionKey = gameRoom.getJoineeSessionKey();
            this.symbol = gameRoom.getJoineeSymbol();
        }
    }

    public long getGameRoomId() {
        return gameRoomId;
    }

    public void setGameRoomId(long gameRoomId) {
        this.gameRoomId = gameRoomId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
