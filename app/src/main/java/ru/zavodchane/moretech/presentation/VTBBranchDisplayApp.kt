package ru.zavodchane.moretech.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import org.osmdroid.views.MapView
import ru.zavodchane.moretech.data.atmMockList
import ru.zavodchane.moretech.data.buildingMockList
import ru.zavodchane.moretech.presentation.bottomsheetcontent.BranchesInfoContent
import ru.zavodchane.moretech.presentation.map.MapViewComposable
import ru.zavodchane.moretech.ui.theme.MoreTechTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VTBBranchDisplayApp( vm : VTBBranchDisplayViewModel, mv : MapView ) {
   MoreTechTheme {
      val bottomSheetScaffoldState = rememberBottomSheetScaffoldState()
      val configuration = LocalConfiguration.current
      val screenHeight = configuration.screenHeightDp.dp
      var bottomSheetHeight by remember { mutableStateOf(screenHeight / 2) }
      BottomSheetScaffold(
         sheetPeekHeight = 50.dp,
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
                  onBuildingInfoDismiss = { bottomSheetHeight = screenHeight / 2 }
               )
            }
         }
      ) { MapViewComposable(buildings = buildingMockList, atms = atmMockList, mv = mv) }
   }
}