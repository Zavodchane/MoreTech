package ru.zavodchane.moretech.presentation.bottomsheetcontent

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import ru.zavodchane.moretech.R
import ru.zavodchane.moretech.ui.theme.Pantone228C20
import ru.zavodchane.moretech.ui.theme.ProcessCyan
import ru.zavodchane.moretech.ui.theme.Typography

@Composable
fun SearchResults() {
    LazyColumn(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .padding(dimensionResource(id = R.dimen.padding_medium))
            .fillMaxWidth()
    ) {
        items(4) {
            ResultItem(address = "г. Москва, ул. Нижняя Красносельская, д. 45/17", distance = 1220, modifier = Modifier.padding(bottom = dimensionResource(
                id = R.dimen.padding_small
            )))
        }
    }
}

@Composable
fun DistanceText(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        style = Typography.titleLarge,
        modifier = modifier.padding(bottom = dimensionResource(id = R.dimen.padding_small))
    )
}

@Composable
fun ResultItem(address: String, distance: Int, modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        Text(text = address, style = Typography.bodyLarge, modifier = Modifier.weight(3f))
        Text(
            text = if (distance < 1000) distance.toString()
                .plus(" м") else (distance.toFloat() / 1000).toString().plus(" км"),
            style = Typography.bodyLarge,
            color = ProcessCyan,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.End
        )
    }
}