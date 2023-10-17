package ru.zavodchane.moretech.presentation.bottomsheetcontent.display

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import org.osmdroid.util.GeoPoint
import ru.zavodchane.moretech.R
import ru.zavodchane.moretech.data.vtbbuilding.VTBBuilding
import ru.zavodchane.moretech.ui.theme.defaultVTBColor
import kotlin.math.roundToInt

@Composable
fun VTBBuildingDisplayCard(
   building : VTBBuilding,
   onBuildingCardClick : (GeoPoint) -> Unit,
   onInfoDisplay : () -> Unit
) {
   val interactionSource = remember { MutableInteractionSource() }
   Card(
      modifier = Modifier
         .fillMaxWidth()
         .padding(vertical = 5.dp)
         .border(
            1.dp,
            defaultVTBColor,
            RoundedCornerShape(10.dp)
         ),
      shape = RoundedCornerShape(5.dp),
      colors = CardDefaults.cardColors(containerColor = Color.White)
   ) {
      Row(
         modifier = Modifier
            .fillMaxWidth()
            .background(defaultVTBColor)
            .clickable(interactionSource, null) {
               onBuildingCardClick(GeoPoint(building.latitude, building.longitude))
            },
         horizontalArrangement = Arrangement.SpaceBetween,
         verticalAlignment = Alignment.CenterVertically
      ) {
         Text(
            text = building.address,
            color = Color.White,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
         )
      }
      Row {
         Column(
            modifier = Modifier.clickable(interactionSource, null) {
               onBuildingCardClick(GeoPoint(building.latitude, building.longitude))
               onInfoDisplay()
            }
         ) {

            Row(verticalAlignment = Alignment.CenterVertically) {
               Text(
                  modifier = Modifier.padding(horizontal = 10.dp),
                  text = "${(building.getActualNormalizedWorkload() * 100).roundToInt()} %"
               )
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
         }
         Column(
            modifier = Modifier
               .fillMaxWidth(),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.SpaceBetween
         ) {
            val activity = LocalContext.current as Activity
            Image(
               imageVector = ImageVector.vectorResource(R.drawable.ic_map_marker),
               contentDescription = null,
               modifier = Modifier
                  .size(40.dp)
                  .clickable(
                     interactionSource = interactionSource,
                     indication = null
                  ) {
                     val mapsIntent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://www.google.com/maps/search/?api=1&query=${building.latitude}%2C${building.longitude}")
                     )
                     activity.startActivity(mapsIntent)
                  }
                  .padding(10.dp)
            )
         }
      }
   }
}