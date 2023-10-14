package ru.zavodchane.moretech.presentation.map.filtering

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.zavodchane.moretech.ui.theme.ProcessCyan
import ru.zavodchane.moretech.ui.theme.ProcessCyan20

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
         .height(36.dp)
         .clickable(onClick = {
            onCheckedChange(isChecked)
         })
   ) {
      Text(text = text, color = ProcessCyan20)
      Checkbox(
         checked = isChecked, onCheckedChange = null, colors = CheckboxDefaults.colors(
            checkedColor = ProcessCyan,
            checkmarkColor = ProcessCyan20,
            uncheckedColor = ProcessCyan
         )
      )
   }
}