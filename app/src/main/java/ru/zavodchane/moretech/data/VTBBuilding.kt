package ru.zavodchane.moretech.data

import org.osmdroid.util.GeoPoint
import ru.zavodchane.moretech.config.AVG_SERVICE_TIME_MINS
import ru.zavodchane.moretech.currentLocationFlow
import ru.zavodchane.moretech.maxNonNormWorkload
import ru.zavodchane.moretech.minNonNormWorkload

data class VTBBuilding(
    val address: String,
    val biometric_data_collection: Boolean,
    val cash_transactions: Boolean,
    val costumers: Int,
    val deposit_boxes: Boolean,
    val deposit_in_foreign_currency: Boolean,
    val deposit_in_rubles: Boolean,
    val deposits_in_precious_metals: Boolean,
    val hasRamp: Boolean,
    val kep: Boolean,
    val koef_avg_time: Double,
    val latitude: Double,
    val longitude: Double,
    val metroStation: String?,
    val myBranch: Boolean,
    val officeType: String,
    val openHours: List<OpenHour>,
    val openHoursIndividual: List<OpenHoursIndividual>,
    val operations_with_precious_metals: Boolean,
    val rating: Boolean,
    val rko: Boolean,
    val salePointFormat: String,
    val salePointName: String,
    val transactions_with_non_cash: Boolean,
    val wi_fi: Boolean,
    val workers: Int,
    val yandexDataWorkload: YandexDataWorkload?
) {
    fun getActualNonNormalizedWorkload() : Double {
        return (koef_avg_time * costumers) / (workers * AVG_SERVICE_TIME_MINS)
    }

    fun getActualNormalizedWorkload() : Double {
        return getActualNonNormalizedWorkload() - (minNonNormWorkload )/ (maxNonNormWorkload - minNonNormWorkload)
    }

    fun getDistanceToUser() : Double {
        val approxCurrentUserLocation = GeoPoint(currentLocationFlow.value)
        val buildingGeoPoint = GeoPoint(latitude, longitude)
        return approxCurrentUserLocation.distanceToAsDouble(buildingGeoPoint)
    }

    fun isMatchingSearchQuery(query: String, filters: ClientFilters) : Boolean {
        val queryList = query.split(" ")
        var newRegex = ""
        queryList.forEach {
            newRegex += "(?=.*\\b${it})"
        }

        val regexPattern = "^$newRegex.*$".toRegex(setOf(RegexOption.IGNORE_CASE, RegexOption.MULTILINE, RegexOption.DOT_MATCHES_ALL, RegexOption.UNIX_LINES))
        val branchParamsString = "$salePointName + $address + $metroStation"
        return regexPattern.containsMatchIn(branchParamsString) && isMatchingFilters(filters)
    }

    private fun isMatchingFilters(filters: ClientFilters) : Boolean {
        if (filters.rko)                          { if (!rko) return false }
        if (filters.hasRamp)                      { if (!hasRamp) return false }
        if (filters.depositBoxes)                 { if (!deposit_boxes) return false }
        if (filters.depositInRubles)              { if (!deposit_in_rubles) return false }
        if (filters.depositInForeignCurrency)     { if (!deposit_in_foreign_currency) return false }
        if (filters.depositInPreciousMetals)      { if (!deposits_in_precious_metals) return false }
        if (filters.operationsWithPreciousMetals) { if (!operations_with_precious_metals) return false }
        return true
    }
}