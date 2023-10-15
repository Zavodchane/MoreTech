package ru.zavodchane.moretech.presentation.bottomsheetcontent

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.zavodchane.moretech.R
import ru.zavodchane.moretech.data.FilterCheckboxType
import ru.zavodchane.moretech.presentation.VTBBranchDisplayViewModel
import ru.zavodchane.moretech.ui.theme.CoolGrey6
import ru.zavodchane.moretech.ui.theme.Pantone228C20
import ru.zavodchane.moretech.ui.theme.Pantone228C80
import ru.zavodchane.moretech.ui.theme.ProcessCyan
import ru.zavodchane.moretech.ui.theme.ProcessCyan20
import ru.zavodchane.moretech.ui.theme.Typography
import ru.zavodchane.moretech.ui.theme.defaultVTBColor

@Composable
fun Filters() {
    Column(
        modifier = Modifier
            .padding(dimensionResource(id = R.dimen.padding_medium))
            .clip(
                shape = RoundedCornerShape(
                    dimensionResource(id = R.dimen.filter_default_border_corners)
                )
            )
    ) {
        var isFiltersOpen by remember {
            mutableStateOf(false)
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .background(defaultVTBColor)
                .padding(dimensionResource(id = R.dimen.padding_small))
                .clickable(onClick = { isFiltersOpen = !isFiltersOpen })
        ) {
            Text(
                text = stringResource(id = R.string.filters_group_name),
                style = Typography.titleLarge,
                color = ProcessCyan20
            )
            if (!isFiltersOpen) {
                Icon(
                    imageVector = Icons.Rounded.KeyboardArrowDown,
                    contentDescription = stringResource(id = R.string.content_description_arrow_open),
                    tint = ProcessCyan20
                )
            } else {
                Icon(
                    imageVector = Icons.Rounded.KeyboardArrowUp,
                    contentDescription = stringResource(id = R.string.content_description_arrow_close),
                    tint = ProcessCyan20
                )
            }
        }

        if (isFiltersOpen) {
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .background(Color.White)
                    .padding(dimensionResource(id = R.dimen.padding_small))
            ) {
                val viewModel = viewModel<VTBBranchDisplayViewModel>()
                val state = viewModel.physicalClientFiltersState.collectAsState().value

                Column(horizontalAlignment = Alignment.Start) {
                    FilterSwitch(
                        isChecked = state.rko,
                        text = stringResource(id = R.string.filters_rko),
                        onCheckedChange = {
                            viewModel.changeCheckboxState(
                                state = !state.rko,
                                stateType = FilterCheckboxType.RKO
                            )
                        }
                    )

//                    FilterCheckbox(
//                        text = stringResource(id = R.string.filters_rko),
//                        isChecked = state.rko,
//                        onCheckedChange = {
//                            viewModel.changeCheckboxState(
//                                state = !state.rko,
//                                stateType = FilterCheckboxType.RKO
//                            )
//                        }
//                    )
                    FilterSwitch(
                        text = stringResource(id = R.string.filters_has_ramp),
                        isChecked = state.hasRamp,
                        onCheckedChange = {
                            viewModel.changeCheckboxState(
                                state = !state.hasRamp,
                                stateType = FilterCheckboxType.HAS_RAMP
                            )
                        }
                    )
//                    FilterCheckbox(
//                        text = stringResource(id = R.string.filters_has_ramp),
//                        isChecked = state.hasRamp,
//                        onCheckedChange = {
//                            viewModel.changeCheckboxState(
//                                state = !state.hasRamp,
//                                stateType = FilterCheckboxType.HAS_RAMP
//                            )
//                        }
//                    )
                    FilterSwitch(
                        text = stringResource(id = R.string.filters_deposit_boxes),
                        isChecked = state.depositBoxes,
                        onCheckedChange = {
                            viewModel.changeCheckboxState(
                                state = !state.depositBoxes,
                                stateType = FilterCheckboxType.DEPOSIT_BOXES
                            )
                        }
                    )
//                    FilterCheckbox(
//                        text = stringResource(id = R.string.filters_deposit_boxes),
//                        isChecked = state.depositBoxes,
//                        onCheckedChange = {
//                            viewModel.changeCheckboxState(
//                                state = !state.depositBoxes,
//                                stateType = FilterCheckboxType.DEPOSIT_BOXES
//                            )
//                        }
//                    )
                    FilterSwitch(
                        text = stringResource(id = R.string.filters_deposit_in_rubles),
                        isChecked = state.depositInRubles,
                        onCheckedChange = {
                            viewModel.changeCheckboxState(
                                state = !state.depositInRubles,
                                stateType = FilterCheckboxType.DEPOSIT_IN_RUBLES
                            )
                        }
                    )
//                    FilterCheckbox(
//                        text = stringResource(id = R.string.filters_deposit_in_rubles),
//                        isChecked = state.depositInRubles,
//                        onCheckedChange = {
//                            viewModel.changeCheckboxState(
//                                state = !state.depositInRubles,
//                                stateType = FilterCheckboxType.DEPOSIT_IN_RUBLES
//                            )
//                        }
//                    )
                    FilterSwitch(
                        text = stringResource(id = R.string.filters_deposit_in_foreign_currency),
                        isChecked = state.depositInForeignCurrency,
                        onCheckedChange = {
                            viewModel.changeCheckboxState(
                                state = !state.depositInForeignCurrency,
                                stateType = FilterCheckboxType.DEPOSIT_IN_FOREIGN_CURRENCY
                            )
                        }
                    )
//                    FilterCheckbox(
//                        text = stringResource(id = R.string.filters_deposit_in_foreign_currency),
//                        isChecked = state.depositInForeignCurrency,
//                        onCheckedChange = {
//                            viewModel.changeCheckboxState(
//                                state = !state.depositInForeignCurrency,
//                                stateType = FilterCheckboxType.DEPOSIT_IN_FOREIGN_CURRENCY
//                            )
//                        }
//                    )
                    FilterSwitch(
                        text = stringResource(id = R.string.filters_deposit_in_precious_metals),
                        isChecked = state.depositInPreciousMetals,
                        onCheckedChange = {
                            viewModel.changeCheckboxState(
                                state = !state.depositInPreciousMetals,
                                stateType = FilterCheckboxType.DEPOSIT_IN_PRECIOUS_METALS
                            )
                        }
                    )
//                    FilterCheckbox(
//                        text = stringResource(id = R.string.filters_deposit_in_precious_metals),
//                        isChecked = state.depositInPreciousMetals,
//                        onCheckedChange = {
//                            viewModel.changeCheckboxState(
//                                state = !state.depositInPreciousMetals,
//                                stateType = FilterCheckboxType.DEPOSIT_IN_PRECIOUS_METALS
//                            )
//                        }
//                    )
                    FilterSwitch(
                        text = stringResource(id = R.string.filters_operations_with_precious_metals),
                        isChecked = state.operationsWithPreciousMetals,
                        onCheckedChange = {
                            viewModel.changeCheckboxState(
                                state = !state.operationsWithPreciousMetals,
                                stateType = FilterCheckboxType.OPERATIONS_WITH_PRECIOUS_METALS
                            )
                        }
                    )
//                    FilterCheckbox(
//                        text = stringResource(id = R.string.filters_operations_with_precious_metals),
//                        isChecked = state.operationsWithPreciousMetals,
//                        onCheckedChange = {
//                            viewModel.changeCheckboxState(
//                                state = !state.operationsWithPreciousMetals,
//                                stateType = FilterCheckboxType.OPERATIONS_WITH_PRECIOUS_METALS
//                            )
//                        }
//                    )
                }
            }
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
        Text(text = text, style = Typography.bodyLarge, color = Color.Black)
        Checkbox(
            checked = isChecked, onCheckedChange = null, colors = CheckboxDefaults.colors(
                checkedColor = defaultVTBColor,
                checkmarkColor = Color.White,
                uncheckedColor = defaultVTBColor
            )
        )
    }
}

@Composable
fun FilterSwitch(
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
        Text(text = text, style = Typography.bodyLarge, color = Color.Black)

        Switch(
            checked = isChecked,
            onCheckedChange = null,
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.White,
                checkedTrackColor = defaultVTBColor,
                uncheckedThumbColor = Color.White,
                uncheckedTrackColor = Pantone228C20,
            )
        )

    }
}
