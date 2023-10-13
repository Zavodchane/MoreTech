package ru.zavodchane.moretech.presentation

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.res.ResourcesCompat
import org.osmdroid.events.MapEventsReceiver
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.MapEventsOverlay
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.gestures.RotationGestureOverlay
import ru.zavodchane.moretech.R
import ru.zavodchane.moretech.data.VTBBuilding

@Composable
fun MapViewComposable( places : List<VTBBuilding>, mv : MapView ) {
   AndroidView (
      modifier = Modifier.fillMaxSize(),
      factory = { mv }
   ) { mapView ->
      mapView.apply {
         setTileSource(TileSourceFactory.MAPNIK)
         setBuiltInZoomControls(false)
         setMultiTouchControls(true)
         controller.setCenter(GeoPoint(55.762936, 37.628845))
         controller.animateTo(GeoPoint(55.762936, 37.628845), 20.0, 10L)
         Log.d("ANDROID_MAPVIEW", "Map View recomposition")
      }
   }

   LaunchedEffect(
      key1 = Unit,
      block = {
         val mapEventsOverlay = MapEventsOverlay(
            object : MapEventsReceiver {
               override fun singleTapConfirmedHelper(p: GeoPoint?): Boolean {
                  return true
               }

               override fun longPressHelper(p: GeoPoint?): Boolean { return false }
            }
         )

         mv.apply {
            controller.zoomTo(6, 1000L)
            setMapConfig()

            for (place in places) {
               addMarker(place)
            }
         }
      }
   )
}

fun MapView.setMapConfig() {
   val rotationGestureOverlay = RotationGestureOverlay(this)
   overlays.add(rotationGestureOverlay)
}

fun MapView.addMarker(
   building : VTBBuilding
) {
   val marker = Marker(this)
   marker.apply {
      position = GeoPoint(building.latitude, building.longitude)
      icon = ResourcesCompat.getDrawable(resources, R.drawable.map_marker, null)
      title = building.salePointName
      setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_CENTER)
      this.subDescription = building.address
      this.id = building.salePointName
   }

   overlays.add(marker)
   invalidate()
}