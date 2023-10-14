package ru.zavodchane.moretech.presentation.util

private const val MEDIAN_SERVICE_TIME_MIN = 5.5F
fun calculateWorkload(
   workers : Int,
   clients : Int,
   currentEstimatedServiceTimeMin : Int
) : Float {
   return (clients * currentEstimatedServiceTimeMin) / (workers * MEDIAN_SERVICE_TIME_MIN)
}