package ru.zavodchane.moretech.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import ru.zavodchane.moretech.R

val VTBGroupFont = FontFamily(
   listOf(
      Font(R.font.vtbgroupui_light),
      Font(R.font.vtbgroupui_medium),
      Font(R.font.vtbgroupui_regular),
      Font(R.font.vtbgroupui_demibold),
      Font(R.font.vtbgroupui_bold)
   )
)

val Typography = Typography(
   bodyLarge = TextStyle(
      fontFamily = VTBGroupFont,
      fontWeight = FontWeight.Normal,
      fontSize = 16.sp,
      lineHeight = 24.sp,
      letterSpacing = 0.5.sp
   ),
   bodyMedium = TextStyle(
      fontFamily = VTBGroupFont,
      fontWeight = FontWeight.Normal,
      fontSize = 14.sp,
      lineHeight = 20.sp
   ),
   headlineLarge = TextStyle(
      fontFamily = VTBGroupFont,
      fontWeight = FontWeight.Normal,
      fontSize = 32.sp,
      lineHeight = 40.sp
   ),
   titleLarge = TextStyle(
      fontFamily = VTBGroupFont,
      fontWeight = FontWeight.SemiBold,
      fontSize = 22.sp,
      lineHeight = 28.sp
   )
)