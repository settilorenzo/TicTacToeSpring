import React from 'react';
import ReactDOM from 'react-dom';
import axios from "axios";
import {Button, Chip} from "@mui/material";

enum Player { X = 'X', O = 'O'}

enum CellStatus { X = 'X', O = 'O', E = 'E'}

const createGame = () => axios.get('http://localhost:8080/gameStatus/createGame').then(r => r.data);

const nextMove = (i: number, j: number) =>
    axios.post(`http://localhost:8080/gameStatus/${i}/${j}`).then(r => r.data);

type Move = {
    currentPlayer: Player,
    gameTable: CellStatus[][],
    validMove: boolean,
    winner: Player | null,
    isDraw: boolean,
    gameOver: boolean
};

const App = () => {

    const [move, setMove] = React.useState<Move | null>(null);

    React.useEffect(() => void createGame().then(setMove), []);

    const Square = ({cell, row, col}: { cell: CellStatus, row: number, col: number }) => {
        return <Button onClick={() => cell === CellStatus.E && nextMove(row, col).then(setMove)}
                       color={cell === CellStatus.E ? 'primary' : 'success'}
                       style={{width: '80px', height: '80px', fontSize: "20px"}}
                       variant="contained">
            {cell === CellStatus.E ? '-' : cell === CellStatus.X ? 'X' : 'O'}
        </Button>
    }

    const Outcome = ({winner}: { winner: Player | null }) => <>
        <Button onClick={() => createGame().then(setMove)}
                color="primary"
                variant="contained"
                style={{margin: '20px'}}>
            Start a new game
        </Button>
    </>

    const CurrentPlayer = ({currentPlayer}: { currentPlayer: Player }) => <>
        <h4>Current player is:</h4>
        <Chip label={currentPlayer === Player.X ? '   X   ' : '   O   '}
              color={currentPlayer === Player.X ? 'error' : 'success'}
              variant="outlined"
              style={{margin: '20px', fontSize: "30px"}}/>
    </>

    if (!move) return <div>Loading...</div>;

    return <div style={{textAlign: "center"}}>
        <h4>Play a Tic-Tac-Toe game</h4>
        <div>
            <Square cell={move.gameTable[0][0]} row={0} col={0}/>
            <Square cell={move.gameTable[0][1]} row={0} col={1}/>
            <Square cell={move.gameTable[0][2]} row={0} col={2}/>
        </div>
        <div>
            <Square cell={move.gameTable[1][0]} row={1} col={0}/>
            <Square cell={move.gameTable[1][1]} row={1} col={1}/>
            <Square cell={move.gameTable[1][2]} row={1} col={2}/>
        </div>
        <div>
            <Square cell={move.gameTable[2][0]} row={2} col={0}/>
            <Square cell={move.gameTable[2][1]} row={2} col={1}/>
            <Square cell={move.gameTable[2][2]} row={2} col={2}/>
        </div>

        <div>
            <Outcome winner={move.winner}/>
        </div>

        <div>
            <CurrentPlayer currentPlayer={move.currentPlayer}/>
        </div>

    </div>

};

ReactDOM.render(
    <React.StrictMode>
        <App></App>
    </React.StrictMode>,
    document.getElementById('root')
);
