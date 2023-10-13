package ru.zavodchane.moretech.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.osmdroid.views.MapView
import ru.zavodchane.moretech.data.atmMockList
import ru.zavodchane.moretech.data.buildingMockList
import ru.zavodchane.moretech.presentation.map.MapViewComposable
import ru.zavodchane.moretech.ui.theme.MoreTechTheme

@Composable
fun VTBBranchDisplayApp( mv : MapView ) {
   MoreTechTheme {
      Surface(
         modifier = Modifier.fillMaxSize(),
         color = MaterialTheme.colorScheme.background
      ) { MapViewComposable(buildings = buildingMockList, atms = atmMockList, mv = mv) }
   }
}