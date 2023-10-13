package ru.zavodchane.moretech

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import org.osmdroid.bonuspack.clustering.RadiusMarkerClusterer
import org.osmdroid.views.MapView
import ru.zavodchane.moretech.presentation.VTBBranchDisplayApp
import ru.zavodchane.moretech.presentation.map.clustering.setupATMMarkerClusterer
import ru.zavodchane.moretech.presentation.map.clustering.setupBuildingMarkerClusterer
import ru.zavodchane.moretech.presentation.map.mapview.setupMapView
import ru.zavodchane.moretech.ui.theme.MoreTechTheme

lateinit var OSMMapView : MapView
lateinit var permissionContract : ActivityResultLauncher<Array<String>>
lateinit var buildingRadiusMarkerClusterer: RadiusMarkerClusterer
lateinit var atmRadiusMarkerClusterer: RadiusMarkerClusterer

class MainActivity : ComponentActivity() {
   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)

      permissionContract = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions() )
      { Toast.makeText(this, "Permissions", Toast.LENGTH_LONG).show() }

      setupMapView(this, packageName)
      setupBuildingMarkerClusterer(this)
      setupATMMarkerClusterer(this)

      setContent {
         MoreTechTheme {
            Surface(
               modifier = Modifier.fillMaxSize(),
               color = MaterialTheme.colorScheme.background
            ) { VTBBranchDisplayApp(mv = OSMMapView) }
         }
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