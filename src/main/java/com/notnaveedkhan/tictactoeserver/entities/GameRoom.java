package com.notnaveedkhan.tictactoeserver.entities;


import javax.persistence.*;

@Entity
@Table(name = "game_room")
public class GameRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "room_name")
    private String roomName;

    @Column(name = "host_name")
    private String hostName;

    @Column(name = "host_email")
    private String hostEmail;

    @Column(name = "host_symbol")
    private String hostSymbol;

    @Column(name = "joinee_name")
    private String joineeName;

    @Column(name = "joinee_email")
    private String joineeEmail;

    @Column(name = "joinee_symbol")
    private String joineeSymbol;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tic_tac_toe_id", referencedColumnName = "id")
    private TicTacToe ticTacToe;

    @Column(name = "host_session_key")
    private String hostSessionKey;

    @Column(name = "joinee_session_key")
    private String joineeSessionKey;

    @Column(name = "finished")
    private boolean finished;

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

    public TicTacToe getTicTacToe() {
        return ticTacToe;
    }

    public void setTicTacToe(TicTacToe ticTacToe) {
        this.ticTacToe = ticTacToe;
    }

    public String getHostSessionKey() {
        return hostSessionKey;
    }

    public void setHostSessionKey(String hostSessionKey) {
        this.hostSessionKey = hostSessionKey;
    }

    public String getJoineeSessionKey() {
        return joineeSessionKey;
    }

    public void setJoineeSessionKey(String joineeSessionKey) {
        this.joineeSessionKey = joineeSessionKey;
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
