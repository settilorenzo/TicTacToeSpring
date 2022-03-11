package com.example.ticTacToeSpring;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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


    @PostMapping("/gameStatus/nextMove")
    public GameLogic nextMove(@RequestParam int i, @RequestParam int j) {

        var gameTable = ticTacToeMoveRepository.findTopByOrderByIdDesc();
        GameLogic game = new GameLogic(gameTable.get());

        if (!(game.getTheWinner().isPresent() || game.isDraw())) {
            game.makeMove(i, j);
            ticTacToeMoveRepository.save(game.toEntity());
        }
        String msg = game.getTheWinner()
                .map(w -> w == Player.X ? "Ha vinto X!" : "Ha vinto O!")
                .orElse("Non ha vinto ancora nessuno :(");
        System.out.println(msg);
        return game;
    }

    @PostMapping("/gameStatus/createGame")
    public GameLogic createGame() {
        var newGame = new TicTacToeMove();
        GameLogic game = new GameLogic(newGame);
        ticTacToeMoveRepository.save(game.toEntity());
        return game;
    }

}




