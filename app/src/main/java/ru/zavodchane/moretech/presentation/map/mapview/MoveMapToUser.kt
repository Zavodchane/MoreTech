package ru.zavodchane.moretech.presentation.map.mapview

import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView

fun MapView.moveMapToUser(userGeoPoint: GeoPoint) {
   controller.animateTo(userGeoPoint)
}