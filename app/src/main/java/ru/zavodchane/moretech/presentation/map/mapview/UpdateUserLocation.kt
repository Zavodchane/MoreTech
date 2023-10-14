package ru.zavodchane.moretech.presentation.map.mapview

import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import ru.zavodchane.moretech.currentLocationFlow

fun MapView.updateUserLocation(userMarker: Marker) {
   userMarker.apply {
      position = currentLocationFlow.value?.let { userLocation ->
         GeoPoint(userLocation.latitude, userLocation.longitude)
      }
   }
   invalidate()
}