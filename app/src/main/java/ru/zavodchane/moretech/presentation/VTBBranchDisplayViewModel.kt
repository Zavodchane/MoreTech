package ru.zavodchane.moretech.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.osmdroid.util.GeoPoint
import ru.zavodchane.moretech.OSMMapView
import ru.zavodchane.moretech.currentLocationFlow
import ru.zavodchane.moretech.data.ClientType

class VTBBranchDisplayViewModel () : ViewModel() {
   private val _currentLocation = currentLocationFlow
   val currentLocation = _currentLocation.asStateFlow()

   private val _clientType = MutableStateFlow(ClientType.PHYSICAL_ENTITY)
   val clientType = _clientType.asStateFlow()

   fun changeClientType(ct: ClientType) {
      _clientType.value = ct
      Log.i("ClientType", "Client type changed to ${clientType.value.name}")
   }

   fun animateToLocation(point : GeoPoint) {
      OSMMapView.controller.animateTo(point)
      Log.i("MapAnimation", "Animating to ${point.latitude} -- ${point.longitude}")
   }
}