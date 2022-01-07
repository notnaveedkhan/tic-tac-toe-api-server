package com.notnaveedkhan.tictactoeserver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.notnaveedkhan.tictactoeserver.entities.GameRoom;

import java.util.List;

public interface GameRoomRepository extends JpaRepository <GameRoom, Long> {

    GameRoom findById(long id);
    List<GameRoom> findByFinishedAndJoineeSessionKeyIsNull(boolean finished);
    GameRoom findByHostSessionKey(String hostSessionKey);
    GameRoom findByJoineeSessionKey(String joineeSessionKey);

}
