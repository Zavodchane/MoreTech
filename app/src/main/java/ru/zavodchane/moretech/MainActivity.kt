package ru.zavodchane.moretech

import android.Manifest
import android.annotation.SuppressLint
import android.content.IntentSender
import android.location.Location
import android.os.Bundle
import android.os.Looper
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationAvailability
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.location.Priority
import kotlinx.coroutines.flow.MutableStateFlow
import org.osmdroid.bonuspack.clustering.RadiusMarkerClusterer
import org.osmdroid.config.Configuration
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import ru.zavodchane.moretech.config.LOCATION_RETRIEVAL_INTERVAL
import ru.zavodchane.moretech.config.RESOLUTION_REQUEST_CODE
import ru.zavodchane.moretech.presentation.VTBBranchDisplayApp
import ru.zavodchane.moretech.presentation.VTBBranchDisplayViewModel
import ru.zavodchane.moretech.presentation.map.clustering.setupATMMarkerClusterer
import ru.zavodchane.moretech.presentation.map.clustering.setupBuildingMarkerClusterer
import ru.zavodchane.moretech.presentation.map.mapview.updateUserLocation
import ru.zavodchane.moretech.presentation.util.permissions

lateinit var OSMMapView : MapView
lateinit var buildingRadiusMarkerClusterer: RadiusMarkerClusterer
lateinit var atmRadiusMarkerClusterer: RadiusMarkerClusterer
lateinit var fusedLocationClient: FusedLocationProviderClient
lateinit var userMarker: Marker
var currentLocationFlow: MutableStateFlow<Location?> = MutableStateFlow(null)

class MainActivity : ComponentActivity() {
   @SuppressLint("MissingPermission") // Запуск зависимой функции происходит только после проверки разрешений
   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)

      fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

      // MapView setup
      Configuration.getInstance().userAgentValue = packageName
      OSMMapView = MapView(this)

      // Clusterers setup
      setupBuildingMarkerClusterer(this)
      setupATMMarkerClusterer(this)

      // UserMarker init
      userMarker = Marker(OSMMapView)

      val locationPermissionRequest = registerForActivityResult(
         ActivityResultContracts.RequestMultiplePermissions()
      ) { permissions ->
         when {
            permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
               setupLocation(userMarker)
               return@registerForActivityResult
            }
            permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
               setupLocation(userMarker)
               return@registerForActivityResult
            } else -> { /* TODO */ }
         }
      }
      locationPermissionRequest.launch(permissions())

      setContent {
         val viewModel = viewModel<VTBBranchDisplayViewModel>()
         VTBBranchDisplayApp(vm = viewModel, mv = OSMMapView)
      }
   }

   @SuppressLint("MissingPermission") // Запуск функции происходит только после проверки разрешений
   private fun setupLocation(userMarker: Marker) {
      val locationRequest = LocationRequest.Builder(
         Priority.PRIORITY_HIGH_ACCURACY,
         LOCATION_RETRIEVAL_INTERVAL
      ).build()
      val builder = LocationSettingsRequest.Builder().addLocationRequest(locationRequest)
      val client = LocationServices.getSettingsClient(this)
      val task = client.checkLocationSettings(builder.build())

      task.addOnSuccessListener {
         val locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
               super.onLocationResult(locationResult)
               locationResult ?: return
               for (location in locationResult.locations) {
                  currentLocationFlow.value = location
                  OSMMapView.updateUserLocation(userMarker)
                  Log.i("CurrentLocation", "${currentLocationFlow.value?.latitude} -- ${currentLocationFlow.value?.longitude}")
               }
            }

            override fun onLocationAvailability(locationAvailability: LocationAvailability) {
               super.onLocationAvailability(locationAvailability)
               Log.i("LocationAvailability", "${locationAvailability.isLocationAvailable}")
            }
         }
         fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper())
         Log.i("LocationSettings", "Granted")
      }

      task.addOnFailureListener { exception ->
         Log.i("LocationSettings", "Not granted! Showing dialog...")
         if (exception is ResolvableApiException){
            try { exception.startResolutionForResult(this@MainActivity, RESOLUTION_REQUEST_CODE)
            } catch (sendEx: IntentSender.SendIntentException) {/* Ignore the error. */}
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