package com.kolanse.tiktaktoe.ui.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch

@Composable
fun BottomSheetLayout() {
    val coroutineScope = rememberCoroutineScope()
//    val modalSheetState = rememberModalBottomSheetState(
//        initialValue = ModalBottomSheetValue.Hidden,
//        confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded },
//        skipHalfExpanded = true
//    )
//
//    ModalBottomSheetLayout(
//        sheetState = modalSheetState,
//        sheetContent = {
//            Column() {
//
//            }
//        }
//    ) {
//    }
}