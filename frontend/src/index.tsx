import React from 'react';
import ReactDOM from 'react-dom';
//import axios from "axios";

type Move = { title: string, content: string };

//const move = async (i: number, j: number) => {
//  axios,get("/sudjbks/dlcnklz
//}

const Square = /*({cell, row, col}: { cell: Cell, row: number, col: number })*/ () => {
    return <button>-</button>
}

const App = () => {
    return <div style={{textAlign: "center"}}>
        <title>Let's Play TicTacToe</title>
        <h4>Do you want to play TicTacToe? :D</h4>
        <div>
            <Square/>
            <Square/>
            <Square/>
        </div>
        <div>
            <Square/>
            <Square/>
            <Square/>
        </div>
        <div>
            <Square/>
            <Square/>
            <Square/>
        </div>

    </div>
}

ReactDOM.render(
    <React.StrictMode>
        <App></App>
    </React.StrictMode>,
    document.getElementById('root')
);
