package com.kolanse.tiktaktoe.model


/**
 * This data class handles a player Game Play
 */
data class TilePlay(
    val boardPosition: BoardPosition,
    val gamePlayed: TileState
)
