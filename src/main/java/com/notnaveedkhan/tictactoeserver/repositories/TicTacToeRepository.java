package com.notnaveedkhan.tictactoeserver.repositories;

import com.notnaveedkhan.tictactoeserver.entities.TicTacToe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicTacToeRepository extends JpaRepository <TicTacToe, Long> {

    TicTacToe findById(long id);
    List<TicTacToe> findByFinishedAndTie(boolean finished, boolean tie);
    List<TicTacToe> findByWinnerAndFinishedAndTie(String winner, boolean finished, boolean tie);

}
