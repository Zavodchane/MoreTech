package ru.zavodchane.moretech.data

val atmMockList = listOf(
   VTBATM(
      address = "ул. Богородский Вал, д. 6, корп. 1",
      latitude = 55.802432,
      longitude = 37.704547,
      allDay = false,
      services = listOf(),
   ),
   VTBATM(
      address = "ул. Нижняя Красносельская, д. 43",
      latitude = 55.773763,
      longitude = 37.675002,
      allDay = false,
      services = listOf(),
   ),
   VTBATM(
      address = "ул. Нижняя Красносельская, д. 43",
      latitude = 55.686137,
      longitude = 37.849832,
      allDay = false,
      services = listOf(),
   )
)

val buildingMockList = listOf(
   VTBBuilding(
      salePointName = "ДО «Солнечногорский» Филиала № 7701 Банка ВТБ (ПАО)",
      address = "141506, Московская область, г. Солнечногорск, ул. Красная, д. 60",
      status = "открытая",
      openHours = listOf(
         mapOf(
            "days" to "пн",
            "hours" to "09:00-18:00"
         ),
         mapOf(
            "days" to "вт",
            "hours" to "09:00-18:00"
         ),
         mapOf(
            "days" to "ср",
            "hours" to "09:00-18:00"
         ),
         mapOf(
            "days" to "чт",
            "hours" to "09:00-18:00"
         ),
         mapOf(
            "days" to "пт",
            "hours" to "09:00-18:00"
         ),
         mapOf(
            "days" to "сб",
            "hours" to "выходной"
         ),
         mapOf(
            "days" to "вс",
            "hours" to "выходной"
         ),
      ),
      rko = "есть РКО",
      openHoursIndividual = listOf(
         mapOf(
            "days" to "пн",
            "hours" to "09:00-20:00"
         ),
         mapOf(
            "days" to "вт",
            "hours" to "09:00-20:00"
         ),
         mapOf(
            "days" to "ср",
            "hours" to "09:00-20:00"
         ),
         mapOf(
            "days" to "чт",
            "hours" to "09:00-20:00"
         ),
         mapOf(
            "days" to "пт",
            "hours" to "09:00-20:00"
         ),
         mapOf(
            "days" to "сб",
            "hours" to "10:00-17:00"
         ),
         mapOf(
            "days" to "вс",
            "hours" to "выходной"
         ),
      ),
      officeType = "Да (Зона Привилегия)",
      salePointFormat = "Универсальный",
      suoAvailability = "Y",
      hasRamp = "N",
      latitude = 55.762936,
      longitude = 37.628845,
      metroStation = listOf("Юго-западная", "Проспект Вернадского"),
      distance = 62105,
      kep = true,
      myBranch = false,
   ),
   VTBBuilding(
      salePointName = "ДО «Солнечногорский» Филиала № 7701 Банка ВТБ (ПАО)",
      address = "141506, Московская область, г. Солнечногорск, ул. Красная, д. 60",
      status = "открытая",
      openHours = listOf(
         mapOf(
            "days" to "пн",
            "hours" to "09:00-18:00"
         ),
         mapOf(
            "days" to "вт",
            "hours" to "09:00-18:00"
         ),
         mapOf(
            "days" to "ср",
            "hours" to "09:00-18:00"
         ),
         mapOf(
            "days" to "чт",
            "hours" to "09:00-18:00"
         ),
         mapOf(
            "days" to "пт",
            "hours" to "09:00-18:00"
         ),
         mapOf(
            "days" to "сб",
            "hours" to "выходной"
         ),
         mapOf(
            "days" to "вс",
            "hours" to "выходной"
         ),
      ),
      rko = "есть РКО",
      openHoursIndividual = listOf(
         mapOf(
            "days" to "пн",
            "hours" to "09:00-20:00"
         ),
         mapOf(
            "days" to "вт",
            "hours" to "09:00-20:00"
         ),
         mapOf(
            "days" to "ср",
            "hours" to "09:00-20:00"
         ),
         mapOf(
            "days" to "чт",
            "hours" to "09:00-20:00"
         ),
         mapOf(
            "days" to "пт",
            "hours" to "09:00-20:00"
         ),
         mapOf(
            "days" to "сб",
            "hours" to "10:00-17:00"
         ),
         mapOf(
            "days" to "вс",
            "hours" to "выходной"
         ),
      ),
      officeType = "Да",
      salePointFormat = "Универсальный",
      suoAvailability = "Y",
      hasRamp = "N",
      latitude = 55.762398,
      longitude = 37.623951,
      metroStation = null,
      distance = 62105,
      kep = true,
      myBranch = false,
   ),
   VTBBuilding(
      salePointName = "ДО «Солнечногорский» Филиала № 7701 Банка ВТБ (ПАО)",
      address = "141506, Московская область, г. Солнечногорск, ул. Красная, д. 60",
      status = "открытая",
      openHours = listOf(
         mapOf(
            "days" to "пн",
            "hours" to "09:00-18:00"
         ),
         mapOf(
            "days" to "вт",
            "hours" to "09:00-18:00"
         ),
         mapOf(
            "days" to "ср",
            "hours" to "09:00-18:00"
         ),
         mapOf(
            "days" to "чт",
            "hours" to "09:00-18:00"
         ),
         mapOf(
            "days" to "пт",
            "hours" to "09:00-18:00"
         ),
         mapOf(
            "days" to "сб",
            "hours" to "выходной"
         ),
         mapOf(
            "days" to "вс",
            "hours" to "выходной"
         ),
      ),
      rko = "есть РКО",
      openHoursIndividual = listOf(
         mapOf(
            "days" to "пн",
            "hours" to "09:00-20:00"
         ),
         mapOf(
            "days" to "вт",
            "hours" to "09:00-20:00"
         ),
         mapOf(
            "days" to "ср",
            "hours" to "09:00-20:00"
         ),
         mapOf(
            "days" to "чт",
            "hours" to "09:00-20:00"
         ),
         mapOf(
            "days" to "пт",
            "hours" to "09:00-20:00"
         ),
         mapOf(
            "days" to "сб",
            "hours" to "10:00-17:00"
         ),
         mapOf(
            "days" to "вс",
            "hours" to "выходной"
         ),
      ),
      officeType = "Да (Зона Привилегия)",
      salePointFormat = "Универсальный",
      suoAvailability = "Y",
      hasRamp = "N",
      latitude = 55.762272,
      longitude = 37.624821,
      metroStation = null,
      distance = 62105,
      kep = true,
      myBranch = false,
   )
)