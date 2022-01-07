package com.notnaveedkhan.tictactoeserver.services;

import com.notnaveedkhan.tictactoeserver.dtos.TicTacToeMovementRequest;
import com.notnaveedkhan.tictactoeserver.dtos.TicTacToeResponse;
import com.notnaveedkhan.tictactoeserver.entities.GameRoom;
import com.notnaveedkhan.tictactoeserver.entities.TicTacToe;
import com.notnaveedkhan.tictactoeserver.exceptions.BadRequestException;
import com.notnaveedkhan.tictactoeserver.exceptions.NotAuthorizedException;
import com.notnaveedkhan.tictactoeserver.exceptions.NotFoundException;
import com.notnaveedkhan.tictactoeserver.repositories.GameRoomRepository;
import com.notnaveedkhan.tictactoeserver.repositories.TicTacToeRepository;
import org.springframework.stereotype.Service;

@Service
public class TicTacToeService {

    private final TicTacToeRepository ticTacToeRepository;
    private final GameRoomRepository gameRoomRepository;

    public TicTacToeService(TicTacToeRepository ticTacToeRepository, GameRoomRepository gameRoomRepository) {
        this.ticTacToeRepository = ticTacToeRepository;
        this.gameRoomRepository = gameRoomRepository;
    }

    public void saveTicTacToe(TicTacToe ticTacToe) {
        ticTacToeRepository.save(ticTacToe);
    }


    public TicTacToeResponse getTicTacToeById(long ticTacToeId) {
        TicTacToe ticTacToe = ticTacToeRepository.findById(ticTacToeId);
        if (ticTacToe == null) {
            throw new NotFoundException(String.format("TicTacToe not found against id %s", ticTacToeId));
        }
        return new TicTacToeResponse(ticTacToe);
    }


    public TicTacToeResponse playMove(TicTacToeMovementRequest request) {

        GameRoom gameRoom = gameRoomRepository.findById(request.getGameRoomId());
        if (gameRoom == null) {
            throw new NotFoundException(String.format("Game room not found against id: %d ",
                    request.getGameRoomId()));
        }

        TicTacToe ticTacToe = ticTacToeRepository.findById(request.getTicTacToeId());
        if (ticTacToe == null) {
            throw new NotFoundException(String.format("TicTacToe not found against id %s",
                    request.getTicTacToeId()));
        }

        if (gameRoom.isFinished() || ticTacToe.isFinished()) {
            throw new BadRequestException("Can not play move in finished game");
        }

        long checkTicTackToeId = gameRoom.getTicTacToe().getId();
        if (checkTicTackToeId != request.getTicTacToeId()) {
            throw new NotAuthorizedException("Can not play move in other's game room");
        }

        String playerName = getPlayerNameByKey(gameRoom, request.getKey());
        if (playerName == null) {
            throw new BadRequestException("Invalid key for playing move");
        }

        if (!playerName.equals(request.getName())) {
            throw new BadRequestException("Invalid movement");
        }

        String playerSymbol = getPlayerSymbolByKey(gameRoom, request.getKey());
        String otherPlayerName = getOtherPlayerNameByKey(gameRoom, request.getKey());

        validateRowAndColumnValues(request.getRow(), request.getColumn());
        playMove(request.getRow(), request.getColumn(), playerSymbol, ticTacToe);

        if (isSymbolWon(ticTacToe, playerSymbol)) {
            ticTacToe.setFinished(true);
            ticTacToe.setWinner(playerName);
            gameRoom.setFinished(true);
            gameRoomRepository.save(gameRoom);
        } else if (isTie(ticTacToe)) {
            ticTacToe.setWinner("NONE");
            ticTacToe.setFinished(true);
            ticTacToe.setTie(true);
            gameRoom.setFinished(true);
            gameRoomRepository.save(gameRoom);
        }

        ticTacToe.setName(otherPlayerName);

        ticTacToeRepository.save(ticTacToe);
        return new TicTacToeResponse(ticTacToe);
    }

    private boolean isTie(TicTacToe ticTacToe) {
        return (
                ticTacToe.getC1r1() != null
                && ticTacToe.getC1r2() != null
                && ticTacToe.getC1r3() != null

                && ticTacToe.getC2r1() != null
                && ticTacToe.getC2r2() != null
                && ticTacToe.getC2r3() != null

                && ticTacToe.getC3r1() != null
                && ticTacToe.getC3r2() != null
                && ticTacToe.getC3r3() != null
        );
    }

    private boolean isSymbolWon(TicTacToe ticTacToe, String symbol) {
        // checking vertically
        if (ticTacToe.getC1r1().equals(symbol)
                && ticTacToe.getC1r2().equals(symbol)
                && ticTacToe.getC1r3().equals(symbol)
        ) {
            return true;
        }

        if (ticTacToe.getC2r1().equals(symbol)
                && ticTacToe.getC2r2().equals(symbol)
                && ticTacToe.getC2r3().equals(symbol)
        ) {
            return true;
        }

        if (ticTacToe.getC3r1().equals(symbol)
                && ticTacToe.getC3r2().equals(symbol)
                && ticTacToe.getC3r3().equals(symbol)
        ) {
            return true;
        }

        // checking horizontally
        if (ticTacToe.getC1r1().equals(symbol)
                && ticTacToe.getC2r1().equals(symbol)
                && ticTacToe.getC3r1().equals(symbol)
        ) {
            return true;
        }

        if (ticTacToe.getC1r2().equals(symbol)
                && ticTacToe.getC2r2().equals(symbol)
                && ticTacToe.getC3r2().equals(symbol)
        ) {
            return true;
        }

        if (ticTacToe.getC1r3().equals(symbol)
                && ticTacToe.getC2r3().equals(symbol)
                && ticTacToe.getC3r3().equals(symbol)
        ) {
            return true;
        }

        // checking diagonally
        if (ticTacToe.getC1r1().equals(symbol)
                && ticTacToe.getC2r2().equals(symbol)
                && ticTacToe.getC3r3().equals(symbol)
        ) {
            return true;
        }

        return (
                ticTacToe.getC3r1().equals(symbol)
                && ticTacToe.getC2r2().equals(symbol)
                && ticTacToe.getC1r3().equals(symbol)
        );
    }


    private void playMove(int row, int column, String playerSymbol, TicTacToe ticTacToe) {
        if (row == 1) {
            if (column == 1) {
                if (ticTacToe.getC1r1() == null) {
                    ticTacToe.setC1r1(playerSymbol);
                } else {
                    throwExceptionOnBadMove(row, column);
                }
            } else if (column == 2) {
                if (ticTacToe.getC2r1() == null) {
                    ticTacToe.setC2r1(playerSymbol);
                } else {
                    throwExceptionOnBadMove(row, column);
                }
            } else if (column == 3) {
                if (ticTacToe.getC3r1() == null) {
                    ticTacToe.setC3r1(playerSymbol);
                } else {
                    throwExceptionOnBadMove(row, column);
                }
            }
        } else if (row == 2) {
            if (column == 1) {
                if (ticTacToe.getC1r2() == null) {
                    ticTacToe.setC1r2(playerSymbol);
                } else {
                    throwExceptionOnBadMove(row, column);
                }
            } else if (column == 2) {
                if (ticTacToe.getC2r2() == null) {
                    ticTacToe.setC2r2(playerSymbol);
                } else {
                    throwExceptionOnBadMove(row, column);
                }
            } else if (column == 3) {
                if (ticTacToe.getC3r2() == null) {
                    ticTacToe.setC3r2(playerSymbol);
                } else {
                    throwExceptionOnBadMove(row, column);
                }
            }
        } else if (row == 3) {
            if (column == 1) {
                if (ticTacToe.getC1r3() == null) {
                    ticTacToe.setC1r3(playerSymbol);
                } else {
                    throwExceptionOnBadMove(row, column);
                }
            } else if (column == 2) {
                if (ticTacToe.getC2r3() == null) {
                    ticTacToe.setC2r3(playerSymbol);
                } else {
                    throwExceptionOnBadMove(row, column);
                }
            } else if (column == 3) {
                if (ticTacToe.getC3r3() == null) {
                    ticTacToe.setC3r3(playerSymbol);
                } else {
                    throwExceptionOnBadMove(row, column);
                }
            }
        }
    }

    private void throwExceptionOnBadMove(int row, int column) {
        throw new BadRequestException(String.format("Can not move on location (R, C) values (%d, %d)",
                row, column));
    }


    private String getPlayerSymbolByKey(GameRoom gameRoom, String key) {
        if (key.equals(gameRoom.getHostSessionKey()))
            return gameRoom.getHostSymbol();
        else if (key.equals(gameRoom.getJoineeSessionKey()))
            return gameRoom.getJoineeSymbol();
        return null;
    }

    private void validateRowAndColumnValues(int row, int column) {
        if (column > 3 || column < 1) {
            throw new BadRequestException("Invalid column value");
        }
        if (row > 3 || row < 1) {
            throw new BadRequestException("Invalid row value");
        }
    }

    private String getOtherPlayerNameByKey(GameRoom gameRoom, String key) {
        if (key.equals(gameRoom.getHostSessionKey()))
            return gameRoom.getJoineeName();
        else if (key.equals(gameRoom.getJoineeSessionKey()))
            return gameRoom.getHostName();
        return null;
    }

    private String getPlayerNameByKey(GameRoom gameRoom, String key) {
        if (key.equals(gameRoom.getHostSessionKey()))
            return gameRoom.getHostName();
        else if (key.equals(gameRoom.getJoineeSessionKey()))
            return gameRoom.getJoineeName();
        return null;
    }
}
