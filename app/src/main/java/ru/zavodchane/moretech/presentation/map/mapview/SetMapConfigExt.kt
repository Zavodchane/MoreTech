package ru.zavodchane.moretech.presentation.map.mapview

import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.gestures.RotationGestureOverlay
import ru.zavodchane.moretech.atmRadiusMarkerClusterer
import ru.zavodchane.moretech.buildingRadiusMarkerClusterer
import ru.zavodchane.moretech.config.INITIAL_ANIM_SPEED
import ru.zavodchane.moretech.config.INITIAL_GEO_POINT
import ru.zavodchane.moretech.config.INITIAL_ZOOM

fun MapView.setMapConfig() {
   setTileSource(TileSourceFactory.MAPNIK)

   setBuiltInZoomControls(false)
   setMultiTouchControls(true)

   controller.setCenter(INITIAL_GEO_POINT)
   controller.setZoom(INITIAL_ZOOM)
   controller.animateTo(INITIAL_GEO_POINT, INITIAL_ZOOM, INITIAL_ANIM_SPEED)

   overlays.add(buildingRadiusMarkerClusterer)
   overlays.add(atmRadiusMarkerClusterer)
   overlays.add(RotationGestureOverlay(this))
}