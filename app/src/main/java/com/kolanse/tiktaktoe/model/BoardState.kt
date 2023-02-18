package com.kolanse.tiktaktoe.model


/**
 * This Class holds the state for the Game Board , Either X wins, 0 wins or a draw
 */
enum class BoardState {
    X_WINS,
    O_WINS,
    DRAW,
    GAME_ON
}