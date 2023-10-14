package ru.zavodchane.moretech.presentation.bottomsheetcontent

import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import org.osmdroid.util.GeoPoint
import ru.zavodchane.moretech.data.VTBBuilding
import ru.zavodchane.moretech.ui.theme.defaultVTBColor

@Composable
fun BranchesInfoContent(
    buildings: List<VTBBuilding>,
    onBuildingCardClick: (GeoPoint) -> Unit,
    changeHeightOnCardClick: () -> Unit,
    onBuildingInfoDismiss: () -> Unit
) {
    var displayBuildingInfo by remember { mutableStateOf(false) }
    var displayedBuilding by remember { mutableStateOf<VTBBuilding?>(null) }

    var query by remember { mutableStateOf("") }
    var queriedList by remember { mutableStateOf(buildings) }

    val focusManager = LocalFocusManager.current

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        if (!displayBuildingInfo) {
            item {
                BasicTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 5.dp)
                        .background(Color.Gray, RoundedCornerShape(10.dp))
                        .padding(10.dp),
                    value = query,
                    onValueChange = { query = it; queriedList = search(query, buildings) },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
                )
            }
            items(queriedList) { building ->
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
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(defaultVTBColor)
                    ) {
                        Text(
                            text = building.address,
                            color = Color.White
                        )
                    }
                    Column(
                        modifier = Modifier.clickable(interactionSource, null) {
                            onBuildingCardClick(GeoPoint(building.latitude, building.longitude))
                            changeHeightOnCardClick()
                            displayBuildingInfo = true
                            displayedBuilding = building
                        }
                    ) {
                        Text(
                            text = building.address,

                            )
                        if (building.metroStation != null) {
                            Text(text = building.metroStation.joinToString(separator = ", "))
                        }
                    }
                }
            }
        } else {
            item {
                if (displayedBuilding != null) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        IconButton(onClick = {
                            displayBuildingInfo = false
                            displayedBuilding = null
                            onBuildingInfoDismiss()
                        }) {
                            Icon(imageVector = Icons.Rounded.Close, contentDescription = null)
                        }
                    }
                    Text(
                        modifier = Modifier.padding(start = 30.dp, bottom = 20.dp),
                        text = displayedBuilding!!.address
                    )
                    Text(
                        modifier = Modifier.padding(start = 30.dp),
                        text = "Обслуживание"
                    )
                    Button(
                        onClick = { /*TODO*/
                        },
                        colors = ButtonDefaults.buttonColors(defaultVTBColor),
                        modifier = Modifier.size(20.dp)
                    ) {

                        Text(text = "Построить маршрут")
                    }
//                Row (
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .background(defaultVTBColor),
//                    horizontalArrangement = Arrangement.End){
//
//                    Text(
//                        modifier = Modifier.padding(start = 30.dp),
//                        text = "Обслуживание"
//                    )
//                }


                }
            }
        }
    }
}

private fun search(query: String, items: List<VTBBuilding>): List<VTBBuilding> {
    return items.filter { building -> building.isMatchingSearchQuery(query) }
}