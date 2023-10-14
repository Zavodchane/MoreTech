package ru.zavodchane.moretech.data

data class PhysicalClientFilters(
    val rko: Boolean = false,
    val hasRamp: Boolean = true,
    val depositBoxes: Boolean = false,
    val depositInRubles: Boolean = true,
    val depositInForeignCurrency: Boolean = true,
    val depositInPreciousMetals: Boolean = false,
    val operationsWithPreciousMetals: Boolean = false
)

