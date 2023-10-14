package ru.zavodchane.moretech.presentation.bottomsheetcontent

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.osmdroid.util.GeoPoint
import ru.zavodchane.moretech.data.VTBBuilding
import ru.zavodchane.moretech.ui.theme.defaultVTBColor

@Composable
fun BranchesInfoContent(
   buildings : List<VTBBuilding>,
   onBuildingCardClick : (GeoPoint) -> Unit,
   changeHeightOnCardClick : () -> Unit
) {
   var displayBuildingInfo by remember { mutableStateOf(false) }
   var displayedBuilding   by remember { mutableStateOf<VTBBuilding?>(null)}

   LazyColumn(modifier = Modifier.fillMaxSize()) {
      if (!displayBuildingInfo) {
         items(buildings) { building ->
            val interactionSource = remember { MutableInteractionSource() }
            Card(
               modifier = Modifier
                  .fillMaxWidth()
                  .padding(5.dp)
                  .clickable(interactionSource, null) {
                     onBuildingCardClick(GeoPoint(building.latitude, building.longitude))
                  },
               shape = RoundedCornerShape(5.dp),
               colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
               Row(
                  modifier = Modifier.background(defaultVTBColor)
               ) {
                  Text(
                     text = building.address,
                     color = Color.White
                  )
               }
               Column(
                  modifier = Modifier.clickable(interactionSource, null) {
                     changeHeightOnCardClick()
                     displayBuildingInfo = true
                     displayedBuilding = building
                  }
               ) {
                  Text(text = building.address)
                  Text(text = building.status)
                  if (building.metroStation != null) {
                     Text(text = building.metroStation.joinToString(separator = ", "))
                  }
               }
            }
         }
      } else {
         item {
            if (displayedBuilding != null) {
               Text(text = displayedBuilding!!.address)
               // TODO: Инфа об отделении
            }
         }
      }
   }
}