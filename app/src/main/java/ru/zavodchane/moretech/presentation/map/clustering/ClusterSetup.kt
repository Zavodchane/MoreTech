package ru.zavodchane.moretech.presentation.map.clustering

import android.content.Context
import android.graphics.Color
import org.osmdroid.bonuspack.clustering.RadiusMarkerClusterer
import org.osmdroid.bonuspack.utils.BonusPackHelper
import ru.zavodchane.moretech.R
import ru.zavodchane.moretech.atmRadiusMarkerClusterer
import ru.zavodchane.moretech.buildingRadiusMarkerClusterer

fun setupATMMarkerClusterer(ctx : Context) {
   atmRadiusMarkerClusterer = RadiusMarkerClusterer(ctx)
   atmRadiusMarkerClusterer.setRadius(40)
   atmRadiusMarkerClusterer.setMaxClusteringZoomLevel(25)
   atmRadiusMarkerClusterer.textPaint.color = Color.BLACK
   atmRadiusMarkerClusterer.setIcon(BonusPackHelper.getBitmapFromVectorDrawable(ctx, R.drawable.vtb_point_multi))
}

fun setupBuildingMarkerClusterer(ctx : Context) {
   buildingRadiusMarkerClusterer = RadiusMarkerClusterer(ctx)
   buildingRadiusMarkerClusterer.setRadius(50)
   buildingRadiusMarkerClusterer.setMaxClusteringZoomLevel(25)
   buildingRadiusMarkerClusterer.textPaint.color = Color.BLACK
   buildingRadiusMarkerClusterer.setIcon(BonusPackHelper.getBitmapFromVectorDrawable(ctx, R.drawable.vtb_point_multi))
}