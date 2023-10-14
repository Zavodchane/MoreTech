package ru.zavodchane.moretech.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import kotlinx.coroutines.flow.asStateFlow
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import ru.zavodchane.moretech.OSMMapView
import ru.zavodchane.moretech.R
import ru.zavodchane.moretech.currentLocationFlow
import ru.zavodchane.moretech.data.buildingMockList
import ru.zavodchane.moretech.locationInitialized
import ru.zavodchane.moretech.presentation.bottomsheetcontent.BranchesInfoContent
import ru.zavodchane.moretech.presentation.map.MapViewComposable
import ru.zavodchane.moretech.presentation.map.mapview.moveMapToUser
import ru.zavodchane.moretech.presentation.map.splash.Splash
import ru.zavodchane.moretech.ui.theme.MoreTechTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VTBBranchDisplayApp( vm : VTBBranchDisplayViewModel, mv : MapView ) {
   MoreTechTheme {
      val bottomSheetScaffoldState = rememberBottomSheetScaffoldState()
      val configuration = LocalConfiguration.current
      val screenHeight = configuration.screenHeightDp.dp
      var bottomSheetHeight by remember { mutableStateOf(screenHeight / 2) }
      val currentClientTypeState = vm.clientType.collectAsState()
      val currentClientFiltersState = vm.clientFilters.collectAsState()

      val sheetPeekHeight = 50.dp
      BottomSheetScaffold(
         sheetPeekHeight = sheetPeekHeight,
         scaffoldState = bottomSheetScaffoldState,
         sheetContent = {
            Column(
               modifier = Modifier
                  .height(bottomSheetHeight)
                  .fillMaxWidth(),
               verticalArrangement = Arrangement.Top,
               horizontalAlignment = Alignment.CenterHorizontally
            ) {
               BranchesInfoContent(
                  buildings = buildingMockList,
                  onBuildingCardClick = vm::animateToLocation,
                  changeHeightOnCardClick = { bottomSheetHeight = screenHeight - screenHeight / 9 },
                  onBuildingInfoDismiss = { bottomSheetHeight = screenHeight / 2 },
                  onClientTypeChange = vm::changeClientType,
                  currentClientType = currentClientTypeState.value,
                  currentClientFilters = currentClientFiltersState
               )
            }
         }
      ) {
         Box {
            val currentUserLocationState = currentLocationFlow.asStateFlow().collectAsState()
            val currentUserGeoPoint = currentUserLocationState.value?.let { currentUserLocation ->
               GeoPoint(currentUserLocation.latitude, currentUserLocation.longitude)
            }
            if (locationInitialized) {
               val interactionSource = remember { MutableInteractionSource() }
               Image(
                  modifier = Modifier
                     .zIndex(10f)
                     .align(Alignment.BottomEnd)
                     .clickable(interactionSource, null) {
                        if (currentUserGeoPoint != null) {
                           OSMMapView.moveMapToUser(currentUserGeoPoint)
                        }
                     }
                     .padding(bottom = sheetPeekHeight + 10.dp, end = 10.dp),
                  imageVector = ImageVector.vectorResource(R.drawable.to_user),
                  contentDescription = null
               )
               MapViewComposable(buildings = buildingMockList, atms = listOf() /*atmMockList*/, mv = mv)
            }
            else { Splash() }
         }
      }
   }
}