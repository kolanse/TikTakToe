package com.kolanse.tiktaktoe.Board

import com.kolanse.tiktaktoe.board.Board
import com.kolanse.tiktaktoe.model.BoardPosition
import com.kolanse.tiktaktoe.model.BoardState
import com.kolanse.tiktaktoe.model.TilePlay
import com.kolanse.tiktaktoe.model.TileState
import kotlinx.coroutines.flow.first
import org.junit.Assert
import org.junit.Test

class BoardClassTest {


    @Test
    fun `assert that board has 9 tiles on initialisation`() {
        val board = Board()

        val tiles = board.tilesFlow.value
        Assert.assertEquals(9, tiles.size)
    }


    @Test
    fun `assert that a  game play stores successfully in the correct board`() {
        val board = Board()

        board.playTile(TilePlay(BoardPosition.FIVE, TileState.O))
        val tiles = board.tilesFlow.value

        Assert.assertEquals(TileState.O, tiles.get(4))
    }

    @Test
    fun `assert that a tile already played can't be changed`() {
        val board = Board()

        board.playTile(TilePlay(BoardPosition.FIVE, TileState.O))
        board.playTile(TilePlay(BoardPosition.FIVE, TileState.X))
        val tiles = board.tilesFlow.value

        Assert.assertEquals(TileState.O, tiles.get(4))
    }


    @Test
    fun `assert that appropriate game play makes x win`() {
        val board = Board()

        board.playTile(TilePlay(BoardPosition.THREE, TileState.X))
        board.playTile(TilePlay(BoardPosition.ONE, TileState.X))
        board.playTile(TilePlay(BoardPosition.TWO, TileState.X))
        val boardStatus = board.boardStatus.value

        Assert.assertEquals(BoardState.X_WINS, boardStatus)
    }



    @Test
    fun `assert that appropriate game play makes o win`() {
        val board = Board()

        board.playTile(TilePlay(BoardPosition.FIVE, TileState.O))
        board.playTile(TilePlay(BoardPosition.ONE, TileState.O))
        board.playTile(TilePlay(BoardPosition.NINE, TileState.O))
        val boardStatus = board.boardStatus.value

        Assert.assertEquals(BoardState.O_WINS, boardStatus)
    }


    @Test
    fun `assert that appropriate game play leads to a draw`() {
        val board = Board()

        board.playTile(TilePlay(BoardPosition.FIVE, TileState.X))
        board.playTile(TilePlay(BoardPosition.ONE, TileState.O))
        board.playTile(TilePlay(BoardPosition.NINE, TileState.X))
        board.playTile(TilePlay(BoardPosition.EIGHT, TileState.O))
        board.playTile(TilePlay(BoardPosition.SEVEN, TileState.X))
        board.playTile(TilePlay(BoardPosition.SIX, TileState.O))
        board.playTile(TilePlay(BoardPosition.FIVE, TileState.X))
        board.playTile(TilePlay(BoardPosition.FOUR, TileState.O))
        board.playTile(TilePlay(BoardPosition.THREE, TileState.X))
        board.playTile(TilePlay(BoardPosition.TWO, TileState.O))
        val boardStatus = board.boardStatus.value

        Assert.assertEquals(BoardState.DRAW, boardStatus)
    }



    @Test
    fun `assert that appropriate game play leads to game on`() {
        val board = Board()

        board.playTile(TilePlay(BoardPosition.FIVE, TileState.X))
        board.playTile(TilePlay(BoardPosition.ONE, TileState.O))
        board.playTile(TilePlay(BoardPosition.NINE, TileState.X))
        board.playTile(TilePlay(BoardPosition.FOUR, TileState.O))
        board.playTile(TilePlay(BoardPosition.THREE, TileState.X))
        board.playTile(TilePlay(BoardPosition.TWO, TileState.O))
        val boardStatus = board.boardStatus.value

        Assert.assertEquals(BoardState.GAME_ON, boardStatus)
    }

    @Test
    fun `assert that a played tile value can't be changed`() {
        val board = Board()

        board.playTile(TilePlay(BoardPosition.FIVE, TileState.X))
        board.playTile(TilePlay(BoardPosition.FIVE, TileState.O))

        val tiles = board.tilesFlow.value

        Assert.assertEquals(TileState.X, tiles.get(4))
    }

}