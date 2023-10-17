package ru.zavodchane.moretech.presentation.bottomsheetcontent.display

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import ru.zavodchane.moretech.R
import ru.zavodchane.moretech.data.vtbbuilding.VTBBuilding
import ru.zavodchane.moretech.ui.theme.Pantone228C
import kotlin.math.roundToInt

@Composable
fun VTBBuildingInfo(building : VTBBuilding, onCloseClick : () -> Unit) {
   Column {

      Row(
         modifier = Modifier.fillMaxWidth(),
         horizontalArrangement = Arrangement.End
      ) {
         IconButton(onClick = onCloseClick) {
            Icon(imageVector = Icons.Rounded.Close, contentDescription = null)
         }
      }

      Row(modifier = Modifier.fillMaxWidth()){
         Text(text = "Адрес: ${building.address}")
      }

      Row(
         modifier = Modifier.fillMaxWidth(),
         verticalAlignment = Alignment.CenterVertically
      ) {
         Image(
            painter = painterResource(id = R.drawable.metro),
            contentDescription = null,
            modifier = Modifier.size(30.dp)
         )
         Text(
            text = if(building.metroStation != null) "${building.metroStation}" else "-",
            modifier = Modifier
               .fillMaxWidth(0.8f)
               .padding(start = 12.dp)
         )
      }

      Row(modifier = Modifier.fillMaxWidth()) {
         Text(text = "Загруженность: ${(building.getActualNormalizedWorkload() * 100).roundToInt()} %")
      }
      if (building.deposit_boxes){
         Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
         ) {
            Text(text = "Аренда ячеек: ")
            Icon(
               imageVector = ImageVector.vectorResource(R.drawable.check_circle),
               contentDescription = null,
               tint = Pantone228C
            )
         }
      }

      if (building.hasRamp) {
         Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
         ) {
            Text(text = "Доступно для лиц с огр. возможностями: ")
            Icon(
               imageVector = ImageVector.vectorResource(R.drawable.check_circle),
               contentDescription = null,
               tint = Pantone228C
            )
         }
      }

      if (building.deposit_in_rubles) {
         Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
         ) {
            Text(text = "Депозиты в рублях: ")
            Icon(
               imageVector = ImageVector.vectorResource(R.drawable.check_circle),
               contentDescription = null,
               tint = Pantone228C
            )
         }
      }

      if (building.deposit_in_foreign_currency) {
         Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
         ) {
            Text(text = "Депозиты в валюте: ")
            Icon(
               imageVector = ImageVector.vectorResource(R.drawable.check_circle),
               contentDescription = null,
               tint = Pantone228C
            )
         }
      }

      if (building.deposits_in_precious_metals) {
         Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
         ) {
            Text(text = "Депозиты в драг. металлах: ")
            Icon(
               imageVector = ImageVector.vectorResource(R.drawable.check_circle),
               contentDescription = null,
               tint = Pantone228C
            )
         }
      }

      if (building.operations_with_precious_metals) {
         Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
         ) {
            Text(text = "Операции с драг. металлами: ")
            Icon(
               imageVector = ImageVector.vectorResource(R.drawable.check_circle),
               contentDescription = null,
               tint = Pantone228C
            )
         }
      }

      Text(text = "Время работы:")
      HorizontalDivider(thickness = 1.dp, color = Pantone228C, modifier = Modifier.fillMaxWidth())
      for (openHours in building.openHoursIndividual) { Text(text = "${openHours.days} - ${openHours.hours}") }
      HorizontalDivider(thickness = 1.dp, color = Pantone228C, modifier = Modifier.fillMaxWidth())
   }
}