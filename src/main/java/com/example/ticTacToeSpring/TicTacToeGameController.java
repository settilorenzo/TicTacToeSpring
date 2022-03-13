package com.example.ticTacToeSpring;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
public class TicTacToeGameController {

    private final TicTacToeMoveRepository ticTacToeMoveRepository;

    public TicTacToeGameController(TicTacToeMoveRepository ticTacToeMoveRepository) {
        this.ticTacToeMoveRepository = ticTacToeMoveRepository;
    }

    public static CellStatus[][] serializedToArray(String gameTable) {
        return Arrays.stream(gameTable.split(";"))
                .map(s -> Arrays.stream(s.split(",")).map(CellStatus::valueOf).toArray(CellStatus[]::new))
                .toArray(CellStatus[][]::new);
    }

    @PostMapping("/gameStatus/{i}/{j}")
    public GameLogic nextMove(@PathVariable int i, @PathVariable int j) {

        var gameTable = ticTacToeMoveRepository.findTopByOrderByIdDesc();
        GameLogic game = new GameLogic(gameTable.get());

        if (!(game.getTheWinner().isPresent() || game.isDraw())) {
            game.makeMove(i, j);
            ticTacToeMoveRepository.save(game.toEntity());
        }
        return game;
    }

    @GetMapping("/gameStatus/createGame")
    public GameLogic createGame() {
        var newGame = new TicTacToeMove();
        GameLogic game = new GameLogic(newGame);
        ticTacToeMoveRepository.save(game.toEntity());
        return game;
    }
}




