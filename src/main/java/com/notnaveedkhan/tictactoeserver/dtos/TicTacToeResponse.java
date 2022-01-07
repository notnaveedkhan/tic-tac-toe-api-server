package com.notnaveedkhan.tictactoeserver.dtos;

import com.notnaveedkhan.tictactoeserver.entities.TicTacToe;

public class TicTacToeResponse {

    private long id;
    private boolean finished;
    private boolean tie;
    private String winner;
    private String c1r1;
    private String c1r2;
    private String c1r3;
    private String c2r1;
    private String c2r2;
    private String c2r3;
    private String c3r1;
    private String c3r2;
    private String c3r3;
    private String name;

    public TicTacToeResponse() { }

    public TicTacToeResponse(TicTacToe ticTacToe) {
        this.id = ticTacToe.getId();
        this.finished = ticTacToe.isFinished();
        this.tie = ticTacToe.isTie();
        this.winner = ticTacToe.getWinner();
        this.c1r1 = ticTacToe.getC1r1();
        this.c1r2 = ticTacToe.getC1r2();
        this.c1r3 = ticTacToe.getC1r3();
        this.c2r1 = ticTacToe.getC2r1();
        this.c2r2 = ticTacToe.getC2r2();
        this.c2r3 = ticTacToe.getC2r3();
        this.c3r1 = ticTacToe.getC3r1();
        this.c3r2 = ticTacToe.getC3r2();
        this.c3r3 = ticTacToe.getC3r3();
        this.name = ticTacToe.getName();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public boolean isTie() {
        return tie;
    }

    public void setTie(boolean tie) {
        this.tie = tie;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public String getC1r1() {
        return c1r1;
    }

    public void setC1r1(String c1r1) {
        this.c1r1 = c1r1;
    }

    public String getC1r2() {
        return c1r2;
    }

    public void setC1r2(String c1r2) {
        this.c1r2 = c1r2;
    }

    public String getC1r3() {
        return c1r3;
    }

    public void setC1r3(String c1r3) {
        this.c1r3 = c1r3;
    }

    public String getC2r1() {
        return c2r1;
    }

    public void setC2r1(String c2r1) {
        this.c2r1 = c2r1;
    }

    public String getC2r2() {
        return c2r2;
    }

    public void setC2r2(String c2r2) {
        this.c2r2 = c2r2;
    }

    public String getC2r3() {
        return c2r3;
    }

    public void setC2r3(String c2r3) {
        this.c2r3 = c2r3;
    }

    public String getC3r1() {
        return c3r1;
    }

    public void setC3r1(String c3r1) {
        this.c3r1 = c3r1;
    }

    public String getC3r2() {
        return c3r2;
    }

    public void setC3r2(String c3r2) {
        this.c3r2 = c3r2;
    }

    public String getC3r3() {
        return c3r3;
    }

    public void setC3r3(String c3r3) {
        this.c3r3 = c3r3;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
