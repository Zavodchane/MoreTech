package ru.zavodchane.moretech.presentation.map.mapview

import androidx.core.content.res.ResourcesCompat
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import ru.zavodchane.moretech.R
import ru.zavodchane.moretech.buildingRadiusMarkerClusterer
import ru.zavodchane.moretech.data.VTBATM
import ru.zavodchane.moretech.data.VTBBuilding

fun MapView.addMarker(building : VTBBuilding) {
   val buildingMarker = Marker(this)
   val buildingIcon = if (building.officeType.contains("привилегия", ignoreCase = true))
   { ResourcesCompat.getDrawable(resources, R.drawable.vtb_point_premium, null) } else // TODO: Изменить маркер на привилегию
   { ResourcesCompat.getDrawable(resources, R.drawable.vtb_point, null) }

   buildingMarker.apply {
      position = GeoPoint(building.latitude, building.longitude)
      icon = buildingIcon
      title = building.salePointName
      setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
      this.subDescription = building.address
      this.id = building.salePointName
   }

   buildingRadiusMarkerClusterer.add(buildingMarker)
   invalidate()
}

fun MapView.addMarker(atm : VTBATM) {
   val atmMarker = Marker(this)
   atmMarker.apply {
      position = GeoPoint(atm.latitude, atm.longitude)
      icon = ResourcesCompat.getDrawable(resources, R.drawable.vtb_atm, null)
      title = atm.address
      setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
      this.subDescription = atm.address
      this.id = atm.address
   }

   buildingRadiusMarkerClusterer.add(atmMarker)
   invalidate()
}