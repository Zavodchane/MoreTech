package ru.zavodchane.moretech.presentation.bottomsheetcontent.filtering

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.zavodchane.moretech.data.FilterCheckboxTypes
import ru.zavodchane.moretech.presentation.VTBBranchDisplayViewModel
import ru.zavodchane.moretech.ui.theme.Typography

@Composable
fun FiltersDialog(
//   onClientTypeChange : (ClientType) -> Unit,
//   currentClientType : ClientType,
   onFilterUpdate : () -> Unit,
   onDismissRequest : () -> Unit
) {
   Dialog(onDismissRequest = onDismissRequest){
      Surface(
         modifier = Modifier.fillMaxWidth(),
         shape = RoundedCornerShape(20.dp),
         color = Color.White
      ) {
         Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
               .fillMaxWidth()
               .padding(20.dp)
         ) {
            val viewModel = viewModel<VTBBranchDisplayViewModel>()
            val clientFilters = viewModel.clientFilters.collectAsState().value

            Box(modifier = Modifier.fillMaxWidth()) {
               Text(
                  modifier = Modifier
                     .fillMaxWidth()
                     .align(Alignment.Center),
                  text = "Фильтры",
                  style = Typography.titleLarge,
                  color = Color.Black,
                  textAlign = TextAlign.Center
               )
               IconButton(
                  modifier = Modifier.align(Alignment.CenterEnd),
                  onClick = onDismissRequest
               ) {
                  Icon(
                     imageVector = Icons.Rounded.Close,
                     contentDescription = null
                  )
               }
            }

            Column {
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