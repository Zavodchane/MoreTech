package ru.zavodchane.moretech.presentation.map.clustering

import android.content.Context
import android.graphics.Color
import org.osmdroid.bonuspack.clustering.RadiusMarkerClusterer
import org.osmdroid.bonuspack.utils.BonusPackHelper
import ru.zavodchane.moretech.R
import ru.zavodchane.moretech.atmRadiusMarkerClusterer
import ru.zavodchane.moretech.buildingRadiusMarkerClusterer
import ru.zavodchane.moretech.config.MAX_CLUSTERING_ZOOM_LEVEL

fun setupATMMarkerClusterer(ctx : Context) {
   atmRadiusMarkerClusterer = RadiusMarkerClusterer(ctx)
   atmRadiusMarkerClusterer.setRadius(40)
   atmRadiusMarkerClusterer.setMaxClusteringZoomLevel(MAX_CLUSTERING_ZOOM_LEVEL)
   atmRadiusMarkerClusterer.textPaint.color = Color.BLACK
   atmRadiusMarkerClusterer.setIcon(BonusPackHelper.getBitmapFromVectorDrawable(ctx, R.drawable.vtb_point_multi))
}

fun setupBuildingMarkerClusterer(ctx : Context) {
   buildingRadiusMarkerClusterer = RadiusMarkerClusterer(ctx)
   buildingRadiusMarkerClusterer.setRadius(50)
   buildingRadiusMarkerClusterer.setMaxClusteringZoomLevel(MAX_CLUSTERING_ZOOM_LEVEL)
   buildingRadiusMarkerClusterer.textPaint.color = Color.BLACK
   buildingRadiusMarkerClusterer.setIcon(BonusPackHelper.getBitmapFromVectorDrawable(ctx, R.drawable.vtb_point_multi))
}