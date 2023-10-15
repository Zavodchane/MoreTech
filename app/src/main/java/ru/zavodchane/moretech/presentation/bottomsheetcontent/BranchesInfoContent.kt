package ru.zavodchane.moretech.presentation.bottomsheetcontent

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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import org.osmdroid.util.GeoPoint
import ru.zavodchane.moretech.R
import ru.zavodchane.moretech.data.ClientFilters
import ru.zavodchane.moretech.data.ClientType
import ru.zavodchane.moretech.data.vtbbuilding.VTBBuilding
import ru.zavodchane.moretech.presentation.bottomsheetcontent.display.VTBBuildingDisplayCard
import ru.zavodchane.moretech.presentation.bottomsheetcontent.display.VTBBuildingInfo
import ru.zavodchane.moretech.presentation.bottomsheetcontent.filtering.FiltersDialog
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

   Column(modifier = Modifier.fillMaxWidth()) {
      if (!displayBuildingInfo) {
         Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
         ) {
            var textFieldSize by remember { mutableStateOf(Size.Zero) }
            BasicTextField(
               modifier = Modifier
                  .fillMaxWidth(0.8f)
                  .padding(vertical = 5.dp)
                  .clip(RoundedCornerShape(10.dp))
                  .border(
                     1.dp,
                     defaultVTBColor,
                     RoundedCornerShape(10.dp)
                  )
                  .padding(10.dp)
                  .onGloballyPositioned { coordinates ->
                     textFieldSize = coordinates.size.toSize()
                  },
               value = query,
               onValueChange = { queryVal ->
                  query = queryVal
                  queriedList = search(
                     query,
                     buildings,
                     currentClientFilters.value
                  ).sortedBy { it.getDistanceToUser() }
                  setCurrentlyDisplayedBuildings(queriedList)
               },
               singleLine = true,
               keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
               keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
            )

            val interactionSource = remember { MutableInteractionSource() }
            var showFiltersDialog by remember { mutableStateOf(false) }
            Image(
               modifier = Modifier
                  .height(with(LocalDensity.current) { textFieldSize.height.toDp() + 20.dp })
                  .background(defaultVTBColor, RoundedCornerShape(10.dp))
                  .padding(5.dp)
                  .clickable(interactionSource, null) {
                     showFiltersDialog = true
                  },
               imageVector = if (currentClientFilters.value == ClientFilters())
                  ImageVector.vectorResource(R.drawable.filters_not_active) else ImageVector.vectorResource(
                  R.drawable.filters_active
               ),
               contentDescription = null
            )
            if (showFiltersDialog) {
               FiltersDialog(
                  onClientTypeChange = onClientTypeChange,
                  currentClientType = currentClientType,
                  onFilterUpdate = {
                     queriedList = search(
                        query,
                        buildings,
                        currentClientFilters.value
                     ).sortedBy { it.getDistanceToUser() }
                     setCurrentlyDisplayedBuildings(queriedList)
                  },
                  onDismissRequest = { showFiltersDialog = false }
               )
            }
         }
      }
      LazyColumn(
         modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp)
      ) {
         if (!displayBuildingInfo) {
            items(queriedList) { building ->
               VTBBuildingDisplayCard(
                  building = building,
                  onBuildingCardClick = onBuildingCardClick,
                  onInfoDisplay = {
                     changeHeightOnCardClick()
                     displayBuildingInfo = true
                     displayedBuilding = building
                  }
               )
            }
         } else {
            item {
               if (displayedBuilding != null) {
                  VTBBuildingInfo(
                     building = displayedBuilding!!,
                     onCloseClick = {
                        displayBuildingInfo = false
                        displayedBuilding = null
                        onBuildingInfoDismiss()
                     }
                  )
               }
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