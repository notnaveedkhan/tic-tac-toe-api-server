package com.notnaveedkhan.tictactoeserver.dtos;

public class TicTacToeMovementRequest {

    private int ticTacToeId;
    private int gameRoomId;
    private String key;
    private String email;
    private String name;
    private int row;
    private int column;

    public int getTicTacToeId() {
        return ticTacToeId;
    }

    public void setTicTacToeId(int ticTacToeId) {
        this.ticTacToeId = ticTacToeId;
    }

    public int getGameRoomId() {
        return gameRoomId;
    }

    public void setGameRoomId(int gameRoomId) {
        this.gameRoomId = gameRoomId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }
}
