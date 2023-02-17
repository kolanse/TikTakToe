package com.kolanse.tiktaktoe.board

import android.util.Log
import com.kolanse.tiktaktoe.model.BoardState
import com.kolanse.tiktaktoe.model.TilePlay
import com.kolanse.tiktaktoe.model.TileState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


/**
 * This class manages the Board, takes in user Input and and processes it to get the current state of the board.
 */
class Board {

    private val _tilesFlow = MutableStateFlow<MutableList<TileState>>(mutableListOf())
    val tilesFlow: StateFlow<MutableList<TileState>> = _tilesFlow

    private val boardState = BoardState.GAME_ON

     val tiles = mutableListOf<TileState>()


    init {
        repeat(9) {
            tiles.add(TileState.EMPTY)
        }
        _tilesFlow.value = tiles
    }



    fun playTile(tilePlay: TilePlay){

        if (tilePlay.gamePlayed != TileState.EMPTY){
            if (tiles[tilePlay.boardPosition.ordinal] == TileState.EMPTY){
                tiles.add(tilePlay.boardPosition.ordinal, TileState.O)

                System.out.println("the tiles first $tiles")
                _tilesFlow.value = tiles
            }
        }

    }





}