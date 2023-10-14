package ru.zavodchane.moretech.data

import android.util.Log

data class VTBBuilding(
   val salePointName                   : String,                     // Наименование
   val address                         : String,                     // Адрес
   val openHours                       : List<Map<String, String?>>,  // Часы работы юр. лица
   val rko                             : Boolean,                     // Наличие РКО
   val openHoursIndividual             : List<Map<String, String>>,  // Часы работы физ. лица
   val officeType                      : String,                     // Открытый тип офиса (???)
   val salePointFormat                 : String,                     // Формат
   val hasRamp                         : Boolean,                     // Есть пандус или нет (оборудован для инвалидов или нет)
   val latitude                        : Double,
   val longitude                       : Double,
   val metroStation                    : List<String>?,              // Станции метро (при наличии)
   var distance                        : Int = 0,                    // Дистанция до отделения от местоположения пользователя
   val kep                             : Boolean,                   // Признак выдачи КЭП
   val myBranch                        : Boolean,                    // Признак "Мое отделение"
   val ratings                         : Float,
   val deposit_boxes                   : Boolean,
   val biometric_data_collection       : Boolean,
   val wifi                            : Boolean,
   val deposit_in_rubles               : Boolean,
   val deposit_in_foreign_currency     : Boolean,
   val deposits_in_precious_metals     : Boolean,
   val transactions_with_non_cash      : Boolean,
   val cash_transactions               : Boolean,
   val operations_with_precious_metals : Boolean,
//   var workload                        : List<Map<String, String>>,
   val workload_online                 : Float = 0f,
   val costumers                       : Int,
   val workers                         : Int,
   val koef_avg_time                   : Float
) {
   fun isMatchingSearchQuery(query: String, filters: ClientFilters) : Boolean {
      val queryList = query.split(" ")
      var newRegex = ""
      queryList.forEach {
         newRegex += "(?=.*\\b${it})"
      }

      val regexPattern = "^$newRegex.*$".toRegex(setOf(RegexOption.IGNORE_CASE, RegexOption.MULTILINE, RegexOption.DOT_MATCHES_ALL, RegexOption.UNIX_LINES))
      val branchParamsString = "$salePointName + $address + $metroStation"
      Log.i("RegexRes", "${regexPattern.containsMatchIn(branchParamsString)}")
      Log.i("FilterRes", "${isMatchingFilters(filters)}")
      return regexPattern.containsMatchIn(branchParamsString) && isMatchingFilters(filters)
   }

   private fun isMatchingFilters(filters: ClientFilters) : Boolean {
      return true
   }
}
