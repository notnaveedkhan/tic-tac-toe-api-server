package com.notnaveedkhan.tictactoeserver.dtos;

import com.notnaveedkhan.tictactoeserver.entities.GameRoom;

public class GameRoomStatusResponse {

    private long id;
    private String roomName;
    private String hostName;
    private String hostEmail;
    private String hostSymbol;
    private String joineeName;
    private String joineeEmail;
    private String joineeSymbol;
    private boolean finished;

    public GameRoomStatusResponse() { }

    public GameRoomStatusResponse(GameRoom gameRoom) {
        this.id = gameRoom.getId();
        this.roomName = gameRoom.getRoomName();

        this.hostName = gameRoom.getHostName();
        this.hostEmail = gameRoom.getHostEmail();
        this.hostSymbol = gameRoom.getHostSymbol();

        this.joineeName = gameRoom.getJoineeName();
        this.joineeEmail = gameRoom.getJoineeEmail();
        this.joineeSymbol = gameRoom.getJoineeSymbol();

        this.finished = gameRoom.isFinished();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getHostEmail() {
        return hostEmail;
    }

    public void setHostEmail(String hostEmail) {
        this.hostEmail = hostEmail;
    }

    public String getJoineeName() {
        return joineeName;
    }

    public void setJoineeName(String joineeName) {
        this.joineeName = joineeName;
    }

    public String getJoineeEmail() {
        return joineeEmail;
    }

    public void setJoineeEmail(String joineeEmail) {
        this.joineeEmail = joineeEmail;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getHostSymbol() {
        return hostSymbol;
    }

    public void setHostSymbol(String hostSymbol) {
        this.hostSymbol = hostSymbol;
    }

    public String getJoineeSymbol() {
        return joineeSymbol;
    }

    public void setJoineeSymbol(String joineeSymbol) {
        this.joineeSymbol = joineeSymbol;
    }
}
