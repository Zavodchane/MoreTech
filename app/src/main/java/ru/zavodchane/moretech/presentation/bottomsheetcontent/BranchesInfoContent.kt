package ru.zavodchane.moretech.presentation.bottomsheetcontent

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import org.osmdroid.util.GeoPoint
import ru.zavodchane.moretech.R
import ru.zavodchane.moretech.data.ClientFilters
import ru.zavodchane.moretech.data.ClientType
import ru.zavodchane.moretech.data.VTBBuilding
import ru.zavodchane.moretech.presentation.bottomsheetcontent.filtering.FiltersGrouped
import ru.zavodchane.moretech.ui.theme.CoolGrey10
import ru.zavodchane.moretech.ui.theme.CoolGrey8
import ru.zavodchane.moretech.ui.theme.Pantone228C20
import ru.zavodchane.moretech.ui.theme.defaultVTBColor
import kotlin.math.roundToInt

@Composable
fun BranchesInfoContent(
   buildings : List<VTBBuilding>,
   setCurrentlyDisplayedBuildings : (List<VTBBuilding>) -> Unit,
   onBuildingCardClick : (GeoPoint) -> Unit,
   changeHeightOnCardClick : () -> Unit,
   onBuildingInfoDismiss : () -> Unit,
   onClientTypeChange : (ClientType) -> Unit,
   currentClientType : ClientType,
   currentClientFilters: State<ClientFilters>
) {
   var displayBuildingInfo by remember { mutableStateOf(false) }
   var displayedBuilding   by remember { mutableStateOf<VTBBuilding?>(null)}

   var query       by remember { mutableStateOf("") }
   var queriedList by remember { mutableStateOf(buildings) }

   val focusManager = LocalFocusManager.current

   LazyColumn(
      modifier = Modifier
          .fillMaxSize()
          .padding(horizontal = 10.dp)
   ) {
      if (!displayBuildingInfo) {
         item {
            BasicTextField(
               modifier = Modifier
                   .fillMaxWidth()
                   .padding(vertical = 5.dp)
                   .background(Color.Gray, RoundedCornerShape(10.dp))
                   .padding(10.dp),
               value = query,
               onValueChange = { query = it; queriedList = search(query, buildings, currentClientFilters.value); setCurrentlyDisplayedBuildings(queriedList) },
               singleLine = true,
               keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
               keyboardActions = KeyboardActions( onDone = { focusManager.clearFocus() } )
            )
            FiltersGrouped(
               onClientTypeChange = onClientTypeChange,
               currentClientType = currentClientType,
               onFilterUpdate = { queriedList = search(query, buildings, currentClientFilters.value); setCurrentlyDisplayedBuildings(queriedList) }
            )
         }
         items(queriedList) { building ->
            val interactionSource = remember { MutableInteractionSource() }
            Card(
               modifier = Modifier
                   .fillMaxWidth()
                   .padding(vertical = 5.dp)
//                  .border(1.dp, defaultVTBColor, RoundedCornerShape(5.dp))
                   .clickable(interactionSource, null) {
                       onBuildingCardClick(GeoPoint(building.latitude, building.longitude))
                   },
               shape = RoundedCornerShape(5.dp),
               colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
               Row(
                  modifier = Modifier
                      .fillMaxWidth()
                      .background(defaultVTBColor),
                  horizontalArrangement = Arrangement.SpaceBetween,
                  verticalAlignment = Alignment.CenterVertically
               ) {
                  Text(
                     text = building.address,
                     color = Color.White,
                     modifier = Modifier.padding(start = 12.dp)
                  )
                  Text(
                     text = "${building.distance} м",
                     modifier = Modifier.padding(end = 12.dp),
                     color = Color.White
                  )
               }
               Row {
                   Column(
                       modifier = Modifier.clickable(interactionSource, null) {
                           onBuildingCardClick(GeoPoint(building.latitude, building.longitude))
                           changeHeightOnCardClick()
                           displayBuildingInfo = true
                           displayedBuilding = building
                       }
                   ) {
                       if (building.metroStation != null) {
                           Row {
                               Image(
                                   painter = painterResource(id = R.drawable.metro),
                                   contentDescription = null,
                                   modifier = Modifier
                                       .size(20.dp)
                               )
                               Text(
                                   text = "${building.metroStation.joinToString(separator = ", ")}",
                                   modifier = Modifier.padding(start = 12.dp),
                                   color = CoolGrey10
                               )
                           }

                       }
                       Text(
                           text = "${(building.workload_online * 100).roundToInt()}%",
                           modifier = Modifier.padding(start = 12.dp),
                           color = CoolGrey10 )
                   }
                   Column (modifier = Modifier.
                       fillMaxWidth(),
                       horizontalAlignment = Alignment.End,
                       verticalArrangement = Arrangement.SpaceBetween

                   ){
                       Image(
                           painter = painterResource(id = R.drawable.marshrut),
                           contentDescription = null,
                           modifier = Modifier
                               .size(50.dp)
                       )
                   }
               }

            }
         }
      } else {
         item {
            if (displayedBuilding != null) {
               Row(
                  modifier = Modifier.fillMaxWidth(),
                  horizontalArrangement = Arrangement.End,
                   verticalAlignment = Alignment.Top
               ) {
                  IconButton(onClick = {
                     displayBuildingInfo=false
                     displayedBuilding=null
                     onBuildingInfoDismiss()
                  }) {
                     Icon(imageVector = Icons.Rounded.Close, contentDescription = null,
                         modifier = Modifier.size(50.dp))
                  }
               }
               Text(text = displayedBuilding!!.address,
                   modifier = Modifier.padding(start = 12.dp),
                   color = CoolGrey10)
            }
         }
      }
   }
}

private fun search(
   query : String,
   items : List<VTBBuilding>,
   filters : ClientFilters) : List<VTBBuilding>
{
   return items.filter { building -> building.isMatchingSearchQuery(query, filters) }
}