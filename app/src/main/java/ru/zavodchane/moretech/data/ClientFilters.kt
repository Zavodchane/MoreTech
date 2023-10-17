package ru.zavodchane.moretech.data

data class ClientFilters(
   val rko: Boolean = false,
   val hasRamp: Boolean = false,
   val depositBoxes: Boolean = false,
   val depositInRubles: Boolean = false,
   val depositInForeignCurrency: Boolean = false,
   val depositInPreciousMetals: Boolean = false,
   val operationsWithPreciousMetals: Boolean = false
)
