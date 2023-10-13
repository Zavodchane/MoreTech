package ru.zavodchane.moretech.presentation.map.mapview

import android.content.Context
import org.osmdroid.config.Configuration
import org.osmdroid.views.MapView
import ru.zavodchane.moretech.OSMMapView

fun setupMapView(ctx : Context, packageName : String) {
   Configuration.getInstance().userAgentValue = packageName
   OSMMapView = MapView(ctx)
}