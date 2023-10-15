package ru.zavodchane.moretech.presentation.bottomsheetcontent.filtering

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ru.zavodchane.moretech.ui.theme.Pantone228C20
import ru.zavodchane.moretech.ui.theme.defaultVTBColor

@Composable
fun FilterSwitch(
   text: String,
   modifier: Modifier = Modifier,
   onCheckedChange: (Boolean) -> Unit,
   isChecked: Boolean = false
) {
   val interactionSource = remember { MutableInteractionSource() }
   Row(
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically,
      modifier = modifier
         .fillMaxWidth()
         .height(36.dp)
         .clickable(interactionSource = interactionSource, indication = null) {
            onCheckedChange(isChecked)
         }
   ) {
      Text(text = text, /*style = Typography.bodyLarge,*/ color = Color.Black)

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