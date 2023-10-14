package ru.zavodchane.moretech.presentation.util

import android.Manifest

fun permissions() : List<String> {
   return listOf(
      Manifest.permission.ACCESS_FINE_LOCATION,
      Manifest.permission.ACCESS_COARSE_LOCATION
   )
}