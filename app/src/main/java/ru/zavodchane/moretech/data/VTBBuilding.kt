package ru.zavodchane.moretech.data

data class VTBBuilding(
   val salePointName       : String,                     // Наименование
   val address             : String,                     // Адрес
   val status              : String,                     // Статус (открыт/закрыт)
   val openHours           : List<Map<String, String>>,  // Часы работы юр. лица
   val rko                 : String,                     // Наличие РКО
   val openHoursIndividual : List<Map<String, String>>,  // Часы работы физ. лица
   val officeType          : String,                     // Открытый тип офиса (???)
   val salePointFormat     : String,                     // Формат
   val suoAvailability     : String,                     // Наличие СУО
   val hasRamp             : String,                     // Есть пандус или нет (оборудован для инвалидов или нет)
   val latitude            : Double,
   val longitude           : Double,
   val metroStation        : List<String>?,              // Станции метро (при наличии)
   var distance            : Int = 0,                    // Дистанция до отделения от местоположения пользователя
   val kep                 : Boolean?,                   // Признак выдачи КЭП
   val myBranch            : Boolean,                    // Признак "Мое отделение"
   var load                : Float = 0f
)
