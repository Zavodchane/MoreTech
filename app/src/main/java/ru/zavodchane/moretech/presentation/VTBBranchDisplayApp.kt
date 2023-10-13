package ru.zavodchane.moretech.presentation

import androidx.compose.runtime.Composable
import org.osmdroid.views.MapView
import ru.zavodchane.moretech.data.VTBBuilding

val mockList = listOf(
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
      metroStation = null,
      distance = 62105,
      kep = true,
      myBranch = false,
   )
)

@Composable
fun VTBBranchDisplayApp( mv : MapView ) {
   MapViewComposable(places = mockList, mv = mv)
}