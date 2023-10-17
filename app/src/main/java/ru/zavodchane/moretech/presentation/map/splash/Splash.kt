package ru.zavodchane.moretech.presentation.map.splash

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import ru.zavodchane.moretech.R

@Composable
fun Splash() {
   Column(
      modifier = Modifier.fillMaxSize(),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center
   ) {
      var isFinished by remember { mutableStateOf(false) }
      val splashAlpha by animateFloatAsState(
         targetValue = if (!isFinished) 1f else 0.2f,
         animationSpec = tween(500, 100),
         label = "splashAlpha",
         finishedListener = { alpha -> isFinished = alpha != 1f }
      )

      Image(
         modifier = Modifier.alpha(splashAlpha),
         imageVector = ImageVector.vectorResource(R.drawable.vtb_splash_logo),
         contentDescription = "splash"
      )
   }
}