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


    private val _boardStatus = MutableStateFlow<BoardState>(BoardState.GAME_ON)
    val boardStatus: StateFlow<BoardState> = _boardStatus

     val tiles = mutableListOf<TileState>()

    private val winningConditions = listOf(
        listOf(0,1,2), listOf(3,4,5), listOf(6,7,8),
        listOf(0,3,6), listOf(1,4,7), listOf(2, 5, 8),
        listOf(0, 4, 8), listOf(2, 4, 6)
    )


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
                _tilesFlow.value = tiles
            }
        }

    }


    /**
     * This checks the Board status and updates it
     */
    private fun checkBoardStatus(){


       val xBoardPositions = tiles.withIndex().filter { it.value == TileState.X }.map { it.index }
        val oBoardPositions = tiles.withIndex().filter { it.value == TileState.O }.map { it.index }
        val emptyBoardPositions = tiles.withIndex().filter { it.value == TileState.EMPTY }.map { it.index }

        if (xBoardPositions.matchesWinningCombinations()){
            _boardStatus.value = BoardState.X_WINS
            return
        }

        if (oBoardPositions.matchesWinningCombinations()){
            _boardStatus.value = BoardState.O_WINS
            return
        }


        if (emptyBoardPositions.isEmpty()){
            _boardStatus.value = BoardState.DRAW
            return
        }


    }


    /**
     * Checks to see whether board positions matches winning combination
     */
    private fun List<Int>.matchesWinningCombinations(): Boolean{

        if (this.size != 3){
          return false
        }

       winningConditions.forEach {
           if (it.containsAll(this)){
               return true
           }
       }

        return false
    }



}