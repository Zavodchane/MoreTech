package ru.zavodchane.moretech.presentation.bottomsheetcontent.filtering

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.zavodchane.moretech.data.FilterCheckboxTypes
import ru.zavodchane.moretech.presentation.VTBBranchDisplayViewModel
import ru.zavodchane.moretech.ui.theme.defaultVTBColor

@Composable
fun Filters(onFilterUpdate : () -> Unit) {
   Column(
      modifier = Modifier
         .clip(shape = RoundedCornerShape(10.dp))
         .border(
            1.dp,
            defaultVTBColor,
            shape = RoundedCornerShape(10.dp)
         )
   ) {
      var isFiltersOpen by remember { mutableStateOf(false) }
      Row(
         horizontalArrangement = Arrangement.SpaceBetween,
         verticalAlignment = Alignment.CenterVertically,
         modifier = Modifier
            .fillMaxWidth()
            .background(color = defaultVTBColor)
            .padding(8.dp)
            .clickable(onClick = { isFiltersOpen = !isFiltersOpen })
      ) {
         Text(
            text = "Фильтры",
//            style = Typography.titleLarge,
            color = Color.White
         )
         if (!isFiltersOpen) {
            Icon(
               imageVector = Icons.Rounded.KeyboardArrowDown,
               contentDescription = "Open filters",
               tint = Color.White
            )
         } else {
            Icon(
               imageVector = Icons.Rounded.KeyboardArrowUp,
               contentDescription = "Close filters",
               tint = Color.White
            )
         }
      }

      if (isFiltersOpen) {
         Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(10.dp)
         ) {
            val viewModel = viewModel<VTBBranchDisplayViewModel>()
            val clientFilters = viewModel.clientFilters.collectAsState().value

            Column(horizontalAlignment = Alignment.Start) {
               FilterSwitch(
                  text = "РКО",
                  isChecked = clientFilters.rko,
                  onCheckedChange = {
                     viewModel.updateFilter(
                        value = !clientFilters.rko,
                        filterType = FilterCheckboxTypes.RKO
                     )
                  }
               )
               FilterSwitch(
                  text = "Подходит для инвалидов",
                  isChecked = clientFilters.hasRamp,
                  onCheckedChange = {
                     viewModel.updateFilter(
                        value = !clientFilters.hasRamp,
                        filterType = FilterCheckboxTypes.HAS_RAMP
                     )
                  }
               )
               FilterSwitch(
                  text = "Аренда ячеек",
                  isChecked = clientFilters.depositBoxes,
                  onCheckedChange = {
                     viewModel.updateFilter(
                        value = !clientFilters.depositBoxes,
                        filterType = FilterCheckboxTypes.DEPOSIT_BOXES
                     )
                  }
               )
               FilterSwitch(
                  text = "Депозит в рублях",
                  isChecked = clientFilters.depositInRubles,
                  onCheckedChange = {
                     viewModel.updateFilter(
                        value = !clientFilters.depositInRubles,
                        filterType = FilterCheckboxTypes.DEPOSIT_IN_RUBLES
                     )
                  }
               )
               FilterSwitch(
                  text = "Валютный депозит",
                  isChecked = clientFilters.depositInForeignCurrency,
                  onCheckedChange = {
                     viewModel.updateFilter(
                        value = !clientFilters.depositInForeignCurrency,
                        filterType = FilterCheckboxTypes.DEPOSIT_IN_FOREIGN_CURRENCY
                     )
                  }
               )
               FilterSwitch(
                  text = "Депозит в драг. металлах",
                  isChecked = clientFilters.depositInPreciousMetals,
                  onCheckedChange = {
                     viewModel.updateFilter(
                        value = !clientFilters.depositInPreciousMetals,
                        filterType = FilterCheckboxTypes.DEPOSIT_IN_PRECIOUS_METALS
                     )
                  }
               )
               FilterSwitch(
                  text = "Операции с драг. металлами",
                  isChecked = clientFilters.operationsWithPreciousMetals,
                  onCheckedChange = {
                     viewModel.updateFilter(
                        value = !clientFilters.operationsWithPreciousMetals,
                        filterType = FilterCheckboxTypes.OPERATIONS_WITH_PRECIOUS_METALS
                     )
                  }
               )
               onFilterUpdate()
            }
         }
      }
   }
}