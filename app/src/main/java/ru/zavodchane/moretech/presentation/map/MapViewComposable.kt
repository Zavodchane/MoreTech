package ru.zavodchane.moretech.presentation.map

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import ru.zavodchane.moretech.OSMMapView
import ru.zavodchane.moretech.data.VTBATM
import ru.zavodchane.moretech.data.VTBBuilding
import ru.zavodchane.moretech.presentation.map.mapview.addMarker
import ru.zavodchane.moretech.presentation.map.mapview.addUserMarker
import ru.zavodchane.moretech.presentation.map.mapview.setMapConfig
import ru.zavodchane.moretech.userMarker

@Composable
fun MapViewComposable(
   buildings : List<VTBBuilding>,
   atms : List<VTBATM>,
   updateMarkers : (List<VTBBuilding>) -> Unit
) {
   AndroidView (
      modifier = Modifier.fillMaxSize(),
      factory = { OSMMapView }
   ) { mapView ->
      mapView.apply { Log.d("ANDROID_MAPVIEW", "Map View recomposition") }
   }

   LaunchedEffect(
      key1 = Unit,
      block = {
         OSMMapView.apply {
            setMapConfig()
            for (vtbBuilding in buildings) { addMarker(vtbBuilding) }
//            for (vtbAtm in atms) { addMarker(vtbAtm) }
            OSMMapView.addUserMarker(userMarker)
         }
      }
   )
   updateMarkers(buildings)
}