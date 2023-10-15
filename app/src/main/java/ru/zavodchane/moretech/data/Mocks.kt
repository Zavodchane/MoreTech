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
      address = "г. Москва, ул. Шахтеров, д. 69",
      openHours = listOf(
         mapOf(
            "days" to "Не обслуживает ЮЛ",
            "hours" to null
         )
      ),
      rko = true,
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
      hasRamp = true,
      latitude = 55.762936,
      longitude = 37.628845,
      metroStation = listOf("Юго-западная", "Проспект Вернадского"),
      distance = 235,
      kep = true,
      myBranch = false,
      ratings = 4.3f,
      deposit_boxes = true,
      biometric_data_collection=false,
      wifi = true,
      deposit_in_foreign_currency = true,
      deposits_in_precious_metals = false,
      deposit_in_rubles = true,
      transactions_with_non_cash = false,
      cash_transactions = true,
      operations_with_precious_metals = true,
      workload_online = 0.31f,
      costumers = 9,
      workers = 13,
      koef_avg_time = 5.9f
   ),
   VTBBuilding(
      salePointName = "Numb diggers bank",
      address = "г. Москва, ул. Шахтеров, д. 69",
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
      rko = false,
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
      hasRamp = false,
      latitude = 55.762398,
      longitude = 37.623951,
      metroStation = null,
      distance = 455,
      kep = true,
      myBranch = false,
      ratings = 4.3f,
      deposit_boxes = true,
      biometric_data_collection=false,
      wifi = true,
      deposit_in_foreign_currency = true,
      deposits_in_precious_metals = false,
      deposit_in_rubles = true,
      transactions_with_non_cash = false,
      cash_transactions = true,
      operations_with_precious_metals = true,
      workload_online = 0.31f,
      costumers = 3,
      workers = 10,
      koef_avg_time = 1.2f
   ),
   VTBBuilding(
      salePointName = "xd «Солнечногорский» Филиала № 7701 Банка ВТБ (ПАО)",
      address = "г. Солнечногорск, ул. Красная, д. 60",
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
      rko = true,
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
      hasRamp = true,
      latitude = 55.762272,
      longitude = 37.624821,
      metroStation = null,
      distance = 105,
      kep = true,
      myBranch = false,
      ratings = 4.6f,
      deposit_boxes = false,
      biometric_data_collection=false,
      wifi = true,
      deposit_in_foreign_currency = true,
      deposits_in_precious_metals = false,
      deposit_in_rubles = true,
      transactions_with_non_cash = false,
      cash_transactions = true,
      operations_with_precious_metals = true,
      workload_online = 0.44f,
      costumers = 14,
      workers = 13,
      koef_avg_time = 5.47f
   )
)