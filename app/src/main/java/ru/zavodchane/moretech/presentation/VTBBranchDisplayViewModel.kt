package ru.zavodchane.moretech.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.zavodchane.moretech.data.ClientType

class VTBBranchDisplayViewModel () : ViewModel() {
   private val _currentLocation = MutableStateFlow<Location?>(null)
   val currentLocation = _currentLocation.asStateFlow()

   private val _clientType = MutableStateFlow<ClientType>(ClientType.PHYSICAL_ENTITY)
   val clientType = _clientType.asStateFlow()

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
}