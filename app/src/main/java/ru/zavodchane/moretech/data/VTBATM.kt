package ru.zavodchane.moretech.data

data class VTBATM(
   val address   : String,                                 // Адрес
   val latitude  : Double,
   val longitude : Double,
   val allDay    : Boolean,                                // Открыт весь день
   val services  : List<Pair<String, Map<String, String>>> // Сервисы банкомата (кринжанул)
)
