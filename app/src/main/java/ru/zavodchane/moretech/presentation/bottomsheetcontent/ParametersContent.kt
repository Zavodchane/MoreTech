package ru.zavodchane.moretech.presentation.bottomsheetcontent

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.compose.ui.zIndex
import ru.zavodchane.moretech.R
import ru.zavodchane.moretech.data.ClientType
import ru.zavodchane.moretech.ui.theme.Typography

@Composable
fun FiltersList(onClientTypeChange: (ClientType) -> Unit, currentClientType: ClientType) {
   var rowSize by remember { mutableStateOf(Size.Zero) }

   Column(modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_medium))) {
      Row(
         modifier = Modifier
            .fillMaxWidth()
            .zIndex(2f)
            .background(Color.Transparent)
            .border(
               dimensionResource(id = R.dimen.filter_default_border_width),
               Color.Black,
               RoundedCornerShape(dimensionResource(id = R.dimen.filter_default_border_corners))
            )
            .onGloballyPositioned { coordinates -> rowSize = coordinates.size.toSize() },
         horizontalArrangement = Arrangement.SpaceBetween
      ) {
         val interactionSource = remember { MutableInteractionSource() }
         Text(
            modifier = Modifier
               .clickable(interactionSource = interactionSource, indication = null) {
                  onClientTypeChange(ClientType.PHYSICAL_ENTITY)
               }
               .width(with(LocalDensity.current) { rowSize.width.toDp() / 2 - (2.5).dp })
               .background(
                  color = if (currentClientType == ClientType.PHYSICAL_ENTITY) Color.LightGray else Color.Transparent,
                  shape = RoundedCornerShape(dimensionResource(id = R.dimen.filter_default_border_corners))
               )
               .padding(vertical = (2.5).dp),
            text = stringResource(id = R.string.client_type_physical),
            textAlign = TextAlign.Center,
            style = Typography.bodyLarge
         )
         Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.padding_small)))
         Text(
            modifier = Modifier
               .clickable(interactionSource = interactionSource, indication = null) {
                  onClientTypeChange(ClientType.LEGAL_ENTITY)
               }
               .width(with(LocalDensity.current) { rowSize.width.toDp() / 2 - (2.5).dp })
               .background(
                  color = if (currentClientType == ClientType.LEGAL_ENTITY) Color.LightGray else Color.Transparent,
                  shape = RoundedCornerShape(5.dp)
               )
               .padding(vertical = (2.5).dp),
            text = stringResource(id = R.string.client_type_legal),
            textAlign = TextAlign.Center,
            style = Typography.bodyLarge
         )
      }
      Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_small)))
      Filters()
   }
}

