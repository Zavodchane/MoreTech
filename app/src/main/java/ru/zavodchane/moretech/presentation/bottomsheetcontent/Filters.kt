package ru.zavodchane.moretech.presentation.bottomsheetcontent

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.zavodchane.moretech.R
import ru.zavodchane.moretech.data.FilterCheckboxType
import ru.zavodchane.moretech.presentation.VTBBranchDisplayViewModel
import ru.zavodchane.moretech.ui.theme.Typography

@Composable
fun Filters() {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val viewModel = viewModel<VTBBranchDisplayViewModel>()
        val state = viewModel.physicalClientFiltersState.collectAsState().value

        Column(horizontalAlignment = Alignment.Start) {
            FilterCheckbox(
                text = stringResource(id = R.string.filters_rko),
                isChecked = state.rko,
                onCheckedChange = {
                    viewModel.changeCheckboxState(
                        state = !state.rko,
                        stateType = FilterCheckboxType.RKO
                    )
                }
            )
            FilterCheckbox(
                text = stringResource(id = R.string.filters_has_ramp),
                isChecked = state.hasRamp,
                onCheckedChange = {
                    viewModel.changeCheckboxState(
                        state = !state.hasRamp,
                        stateType = FilterCheckboxType.HAS_RAMP
                    )
                }
            )
            FilterCheckbox(
                text = stringResource(id = R.string.filters_deposit_boxes),
                isChecked = state.depositBoxes,
                onCheckedChange = {
                    viewModel.changeCheckboxState(
                        state = !state.depositBoxes,
                        stateType = FilterCheckboxType.DEPOSIT_BOXES
                    )
                }
            )
            FilterCheckbox(
                text = stringResource(id = R.string.filters_deposit_in_rubles),
                isChecked = state.depositInRubles,
                onCheckedChange = {
                    viewModel.changeCheckboxState(
                        state = !state.depositInRubles,
                        stateType = FilterCheckboxType.DEPOSIT_IN_RUBLES
                    )
                }
            )
            FilterCheckbox(
                text = stringResource(id = R.string.filters_deposit_in_foreign_currency),
                isChecked = state.depositInForeignCurrency,
                onCheckedChange = {
                    viewModel.changeCheckboxState(
                        state = !state.depositInForeignCurrency,
                        stateType = FilterCheckboxType.DEPOSIT_IN_FOREIGN_CURRENCY
                    )
                }
            )
            FilterCheckbox(
                text = stringResource(id = R.string.filters_deposit_in_precious_metals),
                isChecked = state.depositInPreciousMetals,
                onCheckedChange = {
                    viewModel.changeCheckboxState(
                        state = !state.depositInPreciousMetals,
                        stateType = FilterCheckboxType.DEPOSIT_IN_PRECIOUS_METALS
                    )
                }
            )
            FilterCheckbox(
                text = stringResource(id = R.string.filters_operations_with_precious_metals),
                isChecked = state.operationsWithPreciousMetals,
                onCheckedChange = {
                    viewModel.changeCheckboxState(
                        state = !state.operationsWithPreciousMetals,
                        stateType = FilterCheckboxType.OPERATIONS_WITH_PRECIOUS_METALS
                    )
                }
            )
        }
    }
}

@Composable
fun FilterCheckbox(
    text: String,
    modifier: Modifier = Modifier,
    onCheckedChange: (Boolean) -> Unit,
    isChecked: Boolean = false
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .height(dimensionResource(id = R.dimen.filter_default_height))
            .clickable(onClick = {
                onCheckedChange(isChecked)
            })
    ) {
        Text(text = text, style = Typography.bodyLarge)
        Checkbox(checked = isChecked, onCheckedChange = null)
    }
}
