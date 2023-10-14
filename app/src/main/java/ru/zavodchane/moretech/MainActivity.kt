package ru.zavodchane.moretech

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import org.osmdroid.bonuspack.clustering.RadiusMarkerClusterer
import org.osmdroid.views.MapView
import ru.zavodchane.moretech.presentation.VTBBranchDisplayApp
import ru.zavodchane.moretech.presentation.VTBBranchDisplayViewModel
import ru.zavodchane.moretech.presentation.map.clustering.setupATMMarkerClusterer
import ru.zavodchane.moretech.presentation.map.clustering.setupBuildingMarkerClusterer
import ru.zavodchane.moretech.presentation.map.mapview.setupMapView

lateinit var OSMMapView : MapView
lateinit var buildingRadiusMarkerClusterer: RadiusMarkerClusterer
lateinit var atmRadiusMarkerClusterer: RadiusMarkerClusterer

class MainActivity : ComponentActivity() {
   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)

      setupMapView(this, packageName)
      setupBuildingMarkerClusterer(this)
      setupATMMarkerClusterer(this)

      setContent {
         val viewModel = viewModel<VTBBranchDisplayViewModel>()
         VTBBranchDisplayApp(vm = viewModel, mv = OSMMapView)
      }
   }

   override fun onResume() {
      super.onResume()
      OSMMapView.onResume()
   }

   override fun onPause() {
      super.onPause()
      OSMMapView.onPause()
   }
}