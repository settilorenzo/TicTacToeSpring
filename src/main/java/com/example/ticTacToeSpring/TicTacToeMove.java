package com.example.ticTacToeSpring;

import javax.persistence.*;

enum CellStatus {E, X, O} // <- define the possible values of the board

enum Player {X, O} // <- define the possible values of the current player

@Entity(name = "move")
public class TicTacToeMove {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    public Long getId() { return id; }

    public String gameTableSerialized;
    public Player currentPlayer;

    public TicTacToeMove() {
        this.currentPlayer = Player.X;
        this.gameTableSerialized = "E,E,E;E,E,E;E,E,E";
    }

    public TicTacToeMove(String gameTableSerialized, Player currentPlayer) {
        this.gameTableSerialized = gameTableSerialized;
        this.currentPlayer = currentPlayer;
    }

}
