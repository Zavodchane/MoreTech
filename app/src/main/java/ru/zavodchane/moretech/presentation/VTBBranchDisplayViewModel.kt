package ru.zavodchane.moretech.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.osmdroid.util.GeoPoint
import ru.zavodchane.moretech.OSMMapView
import ru.zavodchane.moretech.currentLocationFlow
import ru.zavodchane.moretech.data.ClientType
import ru.zavodchane.moretech.data.FilterCheckboxType
import ru.zavodchane.moretech.data.PhysicalClientFilters

class VTBBranchDisplayViewModel () : ViewModel() {
   private val _currentLocation = currentLocationFlow
   val currentLocation = _currentLocation.asStateFlow()

   private val _clientType = MutableStateFlow(ClientType.PHYSICAL_ENTITY)
   val clientType = _clientType.asStateFlow()

   private val _physicalClientFiltersState = MutableStateFlow<PhysicalClientFilters>(
      PhysicalClientFilters()
   )
   val physicalClientFiltersState = _physicalClientFiltersState.asStateFlow()

   fun changeClientType(ct: ClientType) {
      _clientType.value = ct
      Log.i("ClientType", "Client type changed to ${clientType.value.name}")
   }

   fun animateToLocation(point : GeoPoint) {
      OSMMapView.controller.animateTo(point)
      Log.i("MapAnimation", "Animating to ${point.latitude} -- ${point.longitude}")
   }

   fun changeCheckboxState(state: Boolean, stateType: FilterCheckboxType) {
      viewModelScope.launch(Dispatchers.IO) {
         _physicalClientFiltersState.update {
            when(stateType) {
               FilterCheckboxType.RKO -> it.copy(rko = state)
               FilterCheckboxType.HAS_RAMP -> it.copy(hasRamp = state)
               FilterCheckboxType.DEPOSIT_BOXES -> it.copy(depositBoxes = state)
               FilterCheckboxType.DEPOSIT_IN_RUBLES -> it.copy(depositInRubles = state)
               FilterCheckboxType.DEPOSIT_IN_FOREIGN_CURRENCY -> it.copy(depositInForeignCurrency = state)
               FilterCheckboxType.DEPOSIT_IN_PRECIOUS_METALS -> it.copy(depositInPreciousMetals = state)
               FilterCheckboxType.OPERATIONS_WITH_PRECIOUS_METALS -> it.copy(operationsWithPreciousMetals = state)
            }
         }
      }
   }
}