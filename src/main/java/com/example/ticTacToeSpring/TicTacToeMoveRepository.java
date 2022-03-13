package com.example.ticTacToeSpring;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TicTacToeMoveRepository extends JpaRepository<TicTacToeMove, Long> {
    Optional<TicTacToeMove> findTopByOrderByIdDesc();
}
