package ru.zavodchane.moretech.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.osmdroid.util.GeoPoint
import ru.zavodchane.moretech.OSMMapView
import ru.zavodchane.moretech.currentLocationFlow
import ru.zavodchane.moretech.data.ClientFilters
import ru.zavodchane.moretech.data.ClientType
import ru.zavodchane.moretech.data.FilterCheckboxTypes

class VTBBranchDisplayViewModel () : ViewModel() {
   private val _currentLocation = currentLocationFlow
   val currentLocation = _currentLocation.asStateFlow()

   private val _clientType = MutableStateFlow(ClientType.PHYSICAL_ENTITY)
   val clientType = _clientType.asStateFlow()

   private val _clientFilters = MutableStateFlow(ClientFilters())
   val clientFilters = _clientFilters.asStateFlow()

   fun changeClientType(ct: ClientType) {
      _clientType.value = ct
      Log.i("ClientType", "Client type changed to ${clientType.value.name}")
   }

   fun animateToLocation(point : GeoPoint) {
      OSMMapView.controller.animateTo(point)
      Log.i("MapAnimation", "Animating to ${point.latitude} -- ${point.longitude}")
   }

   fun updateFilter(filterType : FilterCheckboxTypes, value : Boolean) {
      _clientFilters.update { filters ->
         when(filterType) {
            FilterCheckboxTypes.RKO -> filters.copy(rko = value)
            FilterCheckboxTypes.HAS_RAMP -> filters.copy(hasRamp = value)
            FilterCheckboxTypes.DEPOSIT_BOXES -> filters.copy(depositBoxes = value)
            FilterCheckboxTypes.DEPOSIT_IN_RUBLES -> filters.copy(depositInRubles = value)
            FilterCheckboxTypes.DEPOSIT_IN_FOREIGN_CURRENCY -> filters.copy(depositInForeignCurrency = value)
            FilterCheckboxTypes.DEPOSIT_IN_PRECIOUS_METALS -> filters.copy(depositInPreciousMetals = value)
            FilterCheckboxTypes.OPERATIONS_WITH_PRECIOUS_METALS -> filters.copy(operationsWithPreciousMetals = value)
         }
      }
      Log.i("FilterState", "${clientFilters.value}")
   }
}