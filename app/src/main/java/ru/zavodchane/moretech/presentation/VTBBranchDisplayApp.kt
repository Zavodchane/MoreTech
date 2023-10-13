package ru.zavodchane.moretech.presentation

import androidx.compose.runtime.Composable
import org.osmdroid.views.MapView
import ru.zavodchane.moretech.data.atmMockList
import ru.zavodchane.moretech.data.buildingMockList
import ru.zavodchane.moretech.presentation.map.MapViewComposable

@Composable
fun VTBBranchDisplayApp( mv : MapView ) {
   MapViewComposable(buildings = buildingMockList, atms = atmMockList, mv = mv)
}