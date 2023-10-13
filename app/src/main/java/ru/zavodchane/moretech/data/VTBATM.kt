package ru.zavodchane.moretech.data

data class VTBATM(
   val address   : String,                                 // Адрес
   val latitude  : Float,
   val longitude : Float,
   val allDay    : String,                                 // Открыт весь день
   val services  : List<Pair<String, Map<String, String>>> // Сервисы банкомата (кринжанул)
)
