package ru.zavodchane.moretech.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import org.osmdroid.views.MapView
import ru.zavodchane.moretech.data.atmMockList
import ru.zavodchane.moretech.data.buildingMockList
import ru.zavodchane.moretech.presentation.map.MapViewComposable
import ru.zavodchane.moretech.presentation.util.permissions
import ru.zavodchane.moretech.ui.theme.MoreTechTheme

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Composable
fun VTBBranchDisplayApp( vm : ViewModel, mv : MapView ) {
   val permissionState = rememberMultiplePermissionsState(permissions = permissions())
   if (!permissionState.allPermissionsGranted) { SideEffect { permissionState.launchMultiplePermissionRequest() } }

   MoreTechTheme {
      val bottomSheetScaffoldState = rememberBottomSheetScaffoldState()
      val configuration = LocalConfiguration.current
      val screenHeight = configuration.screenHeightDp.dp
      BottomSheetScaffold(
         scaffoldState = bottomSheetScaffoldState,
         sheetContent = {
            Column(
               modifier = Modifier
                  .height(screenHeight / 2)
                  .fillMaxWidth(),
               verticalArrangement = Arrangement.Center,
               horizontalAlignment = Alignment.CenterHorizontally
            ) { Text("Test Content!") }
         }
      ) { MapViewComposable(buildings = buildingMockList, atms = atmMockList, mv = mv) }
   }
}