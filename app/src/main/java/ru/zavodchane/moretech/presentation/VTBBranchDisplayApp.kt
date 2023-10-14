package ru.zavodchane.moretech.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import org.osmdroid.views.MapView
import ru.zavodchane.moretech.data.atmMockList
import ru.zavodchane.moretech.data.buildingMockList
import ru.zavodchane.moretech.presentation.map.MapViewComposable
import ru.zavodchane.moretech.ui.theme.CoolGrey6
import ru.zavodchane.moretech.ui.theme.MoreTechTheme
import ru.zavodchane.moretech.ui.theme.defaultVTBcolor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VTBBranchDisplayApp( mv : MapView ) {
   MoreTechTheme {
      val bottomSheetScaffoldState = rememberBottomSheetScaffoldState()
      val configuration = LocalConfiguration.current
      val screenHeight = configuration.screenHeightDp.dp
      BottomSheetScaffold(
         sheetContainerColor = Color.White,
         scaffoldState = bottomSheetScaffoldState,
         sheetContent = {
            LazyColumn(
               modifier = Modifier
                  .height(screenHeight / 2)
                  .fillMaxWidth()
                  .background(CoolGrey6),
//               verticalArrangement = Arrangement.SpaceAround,
               horizontalAlignment = Alignment.CenterHorizontally
            ) {
               val listOfVTB = buildingMockList
               listOfVTB.forEach{
                  item { Card(
                     modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                     shape = RoundedCornerShape(5.dp),
                     elevation = CardDefaults.elevatedCardElevation(5.dp),
                     colors = CardDefaults.cardColors(containerColor = Color.White)
                  ) {
                     Row(
                        modifier = Modifier.background(defaultVTBcolor)
                     ) {
                        Text(
                           text = it.address,
                           color = Color.White
                           )
                     }
                     Column() {
                        Text(text = it.address)
                        Text(text = it.status)
                        Text(text = "Метро .........")
                     }

                  }


                  }
//                  Row(
//                     modifier = Modifier.background(Color.Blue ).size(dp),
//                     horizontalArrangement = Arrangement.SpaceAround
//                  ){
//                     Text(text = it)
//                  }
               }
            }
         }
      ) {
         MapViewComposable(buildings = buildingMockList, atms = atmMockList, mv = mv)
      }
   }
}