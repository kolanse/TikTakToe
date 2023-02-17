package com.kolanse.tiktaktoe.Board

import com.kolanse.tiktaktoe.board.Board
import com.kolanse.tiktaktoe.model.BoardPosition
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
    fun `assert that a a game play stores successfully in the correct board`() {
        val board = Board()

        board.playTile(TilePlay(BoardPosition.FIVE, TileState.O))
        val tiles = board.tilesFlow.value

        Assert.assertEquals(TileState.O, tiles.get(4))
    }

}