package ru.zavodchane.moretech.presentation.map

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import org.osmdroid.views.MapView
import ru.zavodchane.moretech.data.VTBATM
import ru.zavodchane.moretech.data.VTBBuilding
import ru.zavodchane.moretech.presentation.map.mapview.addMarker
import ru.zavodchane.moretech.presentation.map.mapview.setMapConfig

@Composable
fun MapViewComposable(buildings : List<VTBBuilding>, atms : List<VTBATM>, mv : MapView ) {
   AndroidView (
      modifier = Modifier.fillMaxSize(),
      factory = { mv }
   ) { mapView ->
      mapView.apply { Log.d("ANDROID_MAPVIEW", "Map View recomposition") }
   }

   LaunchedEffect(
      key1 = Unit,
      block = {
         mv.apply {
            setMapConfig()
            for (vtbBuilding in buildings) { addMarker(vtbBuilding) }
            for (vtbAtm in atms) { addMarker(vtbAtm) }
         }

         // TODO: Надо это вообще или нет???
//         val mapEventsOverlay = MapEventsOverlay(
//            object : MapEventsReceiver {
//               override fun singleTapConfirmedHelper(p: GeoPoint?): Boolean {
//                  return true
//               }
//
//               override fun longPressHelper(p: GeoPoint?): Boolean { return false }
//            }
//         )
      }
   )
}