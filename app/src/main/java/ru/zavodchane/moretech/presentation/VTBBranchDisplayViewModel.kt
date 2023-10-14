package ru.zavodchane.moretech.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.util.Log
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.lifecycle.ViewModel
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.osmdroid.util.GeoPoint
import ru.zavodchane.moretech.OSMMapView
import ru.zavodchane.moretech.data.ClientType

class VTBBranchDisplayViewModel () : ViewModel() {
   private val _currentLocation = MutableStateFlow<Location?>(null)
   val currentLocation = _currentLocation.asStateFlow()

   private val _clientType = MutableStateFlow(ClientType.PHYSICAL_ENTITY)
   val clientType = _clientType.asStateFlow()

   @OptIn(ExperimentalMaterial3Api::class)
   var scaffoldState : BottomSheetScaffoldState? = null

   @SuppressLint("MissingPermission")
   fun getSingleRequestCurrentLocation(ctx : Context) {
      val fusedLocationProvider = LocationServices.getFusedLocationProviderClient(ctx)

      fusedLocationProvider.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, null)
         .addOnSuccessListener { receivedLocation ->
            _currentLocation.value = receivedLocation
            Log.w("CurrentLocation", "Current location received ${currentLocation.value?.latitude} -- ${currentLocation.value?.longitude}")
         }
   }

   fun changeClientType(ct: ClientType) {
      _clientType.value = ct
      Log.i("ClientType", "Client type changed to ${clientType.value.name}")
   }

   fun animateToLocation(point : GeoPoint) {
      OSMMapView.controller.animateTo(point)
      Log.i("MapAnimation", "Animating to ${point.latitude} -- ${point.longitude}")
   }
}