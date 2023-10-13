package ru.zavodchane.moretech.presentation

import androidx.compose.runtime.Composable
import org.osmdroid.views.MapView
import ru.zavodchane.moretech.data.mockList

@Composable
fun VTBBranchDisplayApp( mv : MapView ) {
   MapViewComposable(places = mockList, mv = mv)
}