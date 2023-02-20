package com.kolanse.tiktaktoe

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


/**
 * This is the Viewmodel in charge of Game play.
 * Also functions as to help connect two Devices
 * Thinking .... could be a seperate viewmodel ..
 * but since this is just for tutorial would leave it to just one viewmodel
 */

@HiltViewModel
class GameViewModel @Inject constructor(): ViewModel() {




    fun initBoard(){

    }
}