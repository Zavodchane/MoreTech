package ru.zavodchane.moretech.data

data class VTBBuilding(
   val salePointName                   : String,                     // Наименование
   val address                         : String,                     // Адрес
   val openHours                       : List<Map<String, String>>,  // Часы работы юр. лица
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
   var ratings                         : Float,
   var deposit_boxes                   : Boolean,
   var biometric_data_collection       : Boolean,
   var wifi                            : Boolean,
   var deposit_in_rubles               : Boolean,
   var deposit_in_foreign_currency     : Boolean,
   var deposits_in_precious_metals     : Boolean,
   var transactions_with_non_cash      : Boolean,
   var cash_transactions               : Boolean,
   var operations_with_precious_metals : Boolean,
   var workload                        : List<Map<String, String>>,
   var workload_online                 : Float = 0f,
   var costumers                       : Int,
   var workers                         : Int,
   var koef_avg_time                   : Float
) {
   fun isMatchingSearchQuery(query: String, filters: ClientFilters) : Boolean {
      val queryList = query.split(" ")
      var newRegex = ""
      queryList.forEach {
         newRegex += "(?=.*\\b${it})"
      }

      val regexPattern = "^$newRegex.*$".toRegex(setOf(RegexOption.IGNORE_CASE, RegexOption.MULTILINE, RegexOption.DOT_MATCHES_ALL, RegexOption.UNIX_LINES))
      val branchParamsString = "$salePointName + $address + $metroStation"
      return regexPattern.containsMatchIn(branchParamsString) and isMatchingFilters(filters)
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
