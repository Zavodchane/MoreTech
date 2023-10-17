package ru.zavodchane.moretech.presentation.util

import android.Manifest

fun permissions() : Array<String> {
   return arrayOf(
      Manifest.permission.ACCESS_FINE_LOCATION,
      Manifest.permission.ACCESS_COARSE_LOCATION
   )
}