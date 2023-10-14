package ru.zavodchane.moretech.presentation.map.filtering

import androidx.compose.foundation.background
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.zavodchane.moretech.data.FilterCheckboxTypes
import ru.zavodchane.moretech.presentation.VTBBranchDisplayViewModel
import ru.zavodchane.moretech.ui.theme.ProcessCyan
import ru.zavodchane.moretech.ui.theme.ProcessCyan20

@Composable
fun Filters() {
   Column(
      modifier = Modifier
         .clip(shape = RoundedCornerShape(10.dp))
   ) {
      var isFiltersOpen by remember { mutableStateOf(false) }
      Row(
         horizontalArrangement = Arrangement.SpaceBetween,
         verticalAlignment = Alignment.CenterVertically,
         modifier = Modifier
            .fillMaxWidth()
            .background(ProcessCyan)
            .padding(8.dp)
            .clickable(onClick = { isFiltersOpen = !isFiltersOpen })
      ) {
         Text(
            text = "Фильтры",
            color = ProcessCyan20
         )
         if (!isFiltersOpen) {
            Icon(
               imageVector = Icons.Rounded.KeyboardArrowDown,
               contentDescription = "Open filters",
               tint = ProcessCyan20
            )
         } else {
            Icon(
               imageVector = Icons.Rounded.KeyboardArrowUp,
               contentDescription = "Close filters",
               tint = ProcessCyan20
            )
         }
      }

      if (isFiltersOpen) {
         Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
               .background(ProcessCyan)
               .padding(10.dp)
         ) {
            val viewModel = viewModel<VTBBranchDisplayViewModel>()
            val clientFilters = viewModel.clientFilters.collectAsState().value

            Column(horizontalAlignment = Alignment.Start) {
               FilterCheckbox(
                  text = "РКО",
                  isChecked = clientFilters.rko,
                  onCheckedChange = {
                     viewModel.updateFilter(
                        value = !clientFilters.rko,
                        filterType = FilterCheckboxTypes.RKO
                     )
                  }
               )
               FilterCheckbox(
                  text = "Подходит для инвалидов",
                  isChecked = clientFilters.hasRamp,
                  onCheckedChange = {
                     viewModel.updateFilter(
                        value = !clientFilters.hasRamp,
                        filterType = FilterCheckboxTypes.HAS_RAMP
                     )
                  }
               )
               FilterCheckbox(
                  text = "Аренда ячеек",
                  isChecked = clientFilters.depositBoxes,
                  onCheckedChange = {
                     viewModel.updateFilter(
                        value = !clientFilters.depositBoxes,
                        filterType = FilterCheckboxTypes.DEPOSIT_BOXES
                     )
                  }
               )
               FilterCheckbox(
                  text = "Депозит в рублях",
                  isChecked = clientFilters.depositInRubles,
                  onCheckedChange = {
                     viewModel.updateFilter(
                        value = !clientFilters.depositInRubles,
                        filterType = FilterCheckboxTypes.DEPOSIT_IN_RUBLES
                     )
                  }
               )
               FilterCheckbox(
                  text = "Валютный депозит",
                  isChecked = clientFilters.depositInForeignCurrency,
                  onCheckedChange = {
                     viewModel.updateFilter(
                        value = !clientFilters.depositInForeignCurrency,
                        filterType = FilterCheckboxTypes.DEPOSIT_IN_FOREIGN_CURRENCY
                     )
                  }
               )
               FilterCheckbox(
                  text = "Депозит в драг. металлах",
                  isChecked = clientFilters.depositInPreciousMetals,
                  onCheckedChange = {
                     viewModel.updateFilter(
                        value = !clientFilters.depositInPreciousMetals,
                        filterType = FilterCheckboxTypes.DEPOSIT_IN_PRECIOUS_METALS
                     )
                  }
               )
               FilterCheckbox(
                  text = "Операции с драг. металлами",
                  isChecked = clientFilters.operationsWithPreciousMetals,
                  onCheckedChange = {
                     viewModel.updateFilter(
                        value = !clientFilters.operationsWithPreciousMetals,
                        filterType = FilterCheckboxTypes.OPERATIONS_WITH_PRECIOUS_METALS
                     )
                  }
               )
            }
         }
      }
   }
}